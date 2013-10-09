package org.openrap.tdd.arquillian.jbossejb.facades.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import lombok.extern.log4j.Log4j;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;
import org.openrap.tdd.arquillian.jbossejb.facades.DAO;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;

@Stateless
@Log4j
@Path("auctionitem")
public class AuctionItemService {

    @EJB
    private DAO dao;

    @RolesAllowed({ Roles.AUCTIONMANAGER})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors(ItemBidInterceptor.class)
    @POST
    public void add(AuctionItem item) {
        log.info("About to add "+item.toString());
        dao.create(item);
    }

    @RolesAllowed({Roles.AUCTIONMANAGER})
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Interceptors(ItemBidInterceptor.class)
    @DELETE
    public void delete(AuctionItem item) {
        dao.delete(AuctionItem.class, item.getId());
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Interceptors(ItemBidInterceptor.class)
    @GET
    public List<AuctionItem> getAll() {
        return dao.findAll(AuctionItem.class);
    }
}
