package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
public class ItemBidInterceptor {
    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        // Log Delete
        return context.proceed();
    }
}
