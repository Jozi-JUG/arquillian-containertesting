package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.annotation.security.RunAs;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;
import org.openrap.tdd.arquillian.jbossejb.facades.service.AuctionItemService;

@Stateless
@RunAs(Roles.AUCTIONMANAGER)
public class AuctionItemServiceAsAuctionManager  {

    @EJB
    AuctionItemService auctionItemService;

    public void add(AuctionItem item) {
        auctionItemService.add(item);
    }

    public void delete(AuctionItem item) {
        auctionItemService.delete(item);
    }

    public List<AuctionItem> getAll() {
        return auctionItemService.getAll();
    }

}