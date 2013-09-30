package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.annotation.security.RunAs;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;

//@Metatype
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RunAs(Roles.AUCTIONMANAGER)
public @interface RunAsAuctionManager {
}
