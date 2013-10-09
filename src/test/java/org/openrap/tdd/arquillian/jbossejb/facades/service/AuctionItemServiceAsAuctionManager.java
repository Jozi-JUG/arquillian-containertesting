package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.annotation.security.RunAs;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.Callable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;
import org.openrap.tdd.arquillian.jbossejb.facades.service.AuctionItemService;
import org.openrap.tdd.arquillian.jbossejb.facades.service.IAuctionItemService;

@Stateless
@RunAs(Roles.AUCTIONMANAGER)
public class AuctionItemServiceAsAuctionManager  {

    @EJB
    AuctionItemService auctionItemService;
    
    //@Override
    public void add(AuctionItem item) {
        auctionItemService.add(item);
    }

    //@Override
    public void delete(AuctionItem item) {
        auctionItemService.delete(item);
    }

    //@Override
    public List<AuctionItem> getAll() {
        return auctionItemService.getAll();
    }

}