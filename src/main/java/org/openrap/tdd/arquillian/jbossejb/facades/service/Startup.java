package org.openrap.tdd.arquillian.jbossejb.facades.service;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Singleton;
import lombok.extern.log4j.Log4j;
import org.openrap.tdd.arquillian.jbossejb.facades.Roles;

@Singleton
@javax.ejb.Startup
@DeclareRoles({Roles.AUCTIONMANAGER, Roles.AUCTIONPARTICIPANT})
@Log4j
public class Startup {

    @PostConstruct
    public void initApp(){
        log.info("Start up");
    }

}
