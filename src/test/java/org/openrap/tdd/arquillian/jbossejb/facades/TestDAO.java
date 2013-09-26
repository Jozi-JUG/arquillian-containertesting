package org.openrap.tdd.arquillian.jbossejb.facades;

import java.util.List;
import javax.ejb.EJB;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openrap.tdd.arquillian.jbossejb.domain.AuctionItem;


@RunWith(Arquillian.class)
public class TestDAO {
    @Deployment
    public static Archive<?> createDeployment() throws Exception {
//MavenDependencyResolver resolver = DependencyResolvers.use(
//MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap.create(WebArchive.class, "a.war")
                .addClasses(DAO.class)
                .addPackage("org.openrap.tdd.arquillian.jbossejb.domain")
                //...
                //.addAsLibraries(libs)
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsResource(
                "META-INF/test-persistence.xml",
                "META-INF/persistence.xml")
                .addAsWebInfResource(
                EmptyAsset.INSTANCE,
                ArchivePaths.create("beans.xml"));

        System.out.println(war.toString(true));

        return war;
    }
    @EJB
    private DAO dao;
    
   @Test
    public void testCreate() {
        String name = "testcustomer" + System.currentTimeMillis();
        dao.create(new AuctionItem(name));
    }

    @Test
    public void edit() {
        String name = "testcustomer" + System.currentTimeMillis();
        AuctionItem databaseCustomer = dao.create(new AuctionItem(name));

        databaseCustomer.setName(name + name);
    }

    @Test
    public void remove() {
        String name = "testcustomer" + System.currentTimeMillis();
        int initialCount = dao.count(AuctionItem.class);
        AuctionItem databaseCustomer = dao.create(new AuctionItem(name));
        int afterCreateCount = dao.count(AuctionItem.class);
        dao.delete(AuctionItem.class,databaseCustomer.getId());
        int afterRemove = dao.count(AuctionItem.class);

        Assert.assertEquals("it seems the record was not created", initialCount + 1, afterCreateCount);
        Assert.assertEquals("it seems the record was not deleted", initialCount, afterRemove);
    }

    @Test
    public void testFind() {
        String name = "testcustomer" + System.currentTimeMillis();
        AuctionItem databaseCustomer = dao.create(new AuctionItem(name));
        Assert.assertNotNull(dao.find(AuctionItem.class,databaseCustomer.getId()));
    }
    @Test
    public void testFindAll() {
        List<AuctionItem> customers = dao.findAll(AuctionItem.class);
    }

    @Test
    public void testfindRange() {
        List<AuctionItem> customers = dao.findRange(AuctionItem.class,0,1);
    }

    @Test
    public void testCount() {
        dao.count(AuctionItem.class);
    }
}
