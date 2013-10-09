package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import lombok.extern.log4j.Log4j;

@Log4j
public class AuctionItemInterceptor {

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        log.info("add item"+context.getParameters());
        return context.proceed();
    }
}
