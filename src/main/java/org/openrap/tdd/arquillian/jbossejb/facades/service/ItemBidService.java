package org.openrap.tdd.arquillian.jbossejb.facades.service;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;
import org.openrap.tdd.arquillian.jbossejb.facades.DAO;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;

@Stateless
@Path("itembid")
public class ItemBidService {

    @EJB
    private DAO dao;
    @Inject
    private Validator validator;

    @RolesAllowed({Roles.AUCTIONPARTICIPANT})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors(ItemBidInterceptor.class)
    @POST
    public void placeBid(AuctionItem item) {
        dao.create(item);
    }

    @RolesAllowed({Roles.AUCTIONMANAGER})
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    @Interceptors(ItemBidInterceptor.class)
    @DELETE
    public void deleteBid(AuctionItem item) {
        dao.delete(AuctionItem.class, item.getId());
    }

    @RolesAllowed({Roles.AUCTIONMANAGER})
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Interceptors(ItemBidInterceptor.class)
    @GET
    public List<AuctionItem> getAll() {
        return dao.findAll(AuctionItem.class);
    }
}
