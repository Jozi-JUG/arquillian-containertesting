package org.openrap.tdd.arquillian.jbossejb.facades.service;

import java.util.concurrent.Callable;

public interface Caller {

    public <V> V call(Callable<V> callable) throws Exception;
}
