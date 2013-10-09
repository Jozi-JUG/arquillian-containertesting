package org.openrap.tdd.arquillian.jbossejb.domain;

import javax.inject.Inject;
import javax.validation.Validator;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestAuctionItem {

    @Deployment
    public static Archive<?> createDeployment() throws Exception {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "a.war")
                .addClasses(ItemBid.class,AuctionItem.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");


        System.out.println(war.toString(true));

        return war;
    }
    @Inject
    private Validator validator;

    @Before
    public void preconditions(){
        Assert.assertNotNull(validator);
    }

    @Test
    public void testInvalidNameIsNullAmountIsZero() {
        Assert.assertFalse(validator.validate(new AuctionItem()).isEmpty());
    }
    @Test
    public void testInvalidNameIsNull() {
        Assert.assertFalse(validator.validate(new AuctionItem(null,1)).isEmpty());
    }
    @Test
    public void testInvalidAmountIsZero() {
        Assert.assertFalse(validator.validate(new AuctionItem("",0)).isEmpty());
    }
    @Test
    public void testValid() {
        Assert.assertTrue(validator.validate(new AuctionItem("BB",1)).isEmpty());
    }
}
