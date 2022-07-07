package com.example.secureGroupQLResource.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

    /**
     * All graphQLResolver methods can be called only by authenticated user.
     * @Unsecured annotated methods are excluded
     */
    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured()")
    public void doSecurityCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new RuntimeException("User not authenticated");
        }
    }

    /**
     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver}
     * note: {@code GraphQLMutationResolver}, {@code GraphQLQueryResolver} etc
     * extend base GraphQLResolver interface
     */
    @Pointcut("target(graphql.kickstart.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {
    }

    /**
     * Matches all beans in com.mi3o.springgraphqlsecurity package
     * resolvers must be in this package (subpackages)
     */
    @Pointcut("within(com.example.secureGroupQLResource..*)")
    private void isDefinedInApplication() {
    }

    /**
     * Any method annotated with @Unsecured
     */
    @Pointcut("@annotation(com.example.secureGroupQLResource.security.Unsecured)")
    private void isMethodAnnotatedAsUnsecured() {
    }
}