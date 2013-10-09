package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import lombok.extern.log4j.Log4j;

@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
@Log4j
public class ItemBidInterceptor {
    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        log.info("add item"+context.getParameters());
        return context.proceed();
    }
}
