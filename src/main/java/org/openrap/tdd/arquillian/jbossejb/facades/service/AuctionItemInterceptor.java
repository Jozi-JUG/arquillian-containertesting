package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AuctionItemInterceptor {

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        // Log Add
        return context.proceed();
    }
}
