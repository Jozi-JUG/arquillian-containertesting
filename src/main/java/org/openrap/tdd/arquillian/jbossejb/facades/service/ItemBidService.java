package org.openrap.tdd.arquillian.jbossejb.facades.service;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.Validator;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;
import org.openrap.tdd.arquillian.jbossejb.facades.DAO;

@Stateless
public class ItemBidService {

    @EJB
    private DAO dao;
    @Inject
    private Validator validator;

    @RolesAllowed({"Employee", "Manager"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Interceptors(ItemBidInterceptor.class)
    public void placeBid(AuctionItem item) {
        dao.create(item);
    }

    @RolesAllowed({"Manager"})
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    @Interceptors(ItemBidInterceptor.class)
    public void deleteBid(AuctionItem item) {
        dao.delete(AuctionItem.class, item.getId());
    }

    @RolesAllowed({"Manager"})
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Interceptors(ItemBidInterceptor.class)
    public List<AuctionItem> getAll() {
        return dao.findAll(AuctionItem.class);
    }
}
