arquillian-containertesting for the Jozi JUG http://www.meetup.com/Jozi-JUG/
===========================

Inversion of control (IoC) is a great feature, but it is really a by the way feature of Java EE 6 and 7.

The real power of Java EE is add feature easily without boiler code like
- Role based access control
- Transactions they way it is needed
- Log the input and output of calls with interceptors
- And many others

@RolesAllowed({"Employee", "Manager"})
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(ItemBidInterceptor.class)
public void add(AuctionItem item) {
    dao.create(item);
}

This can now be tested with

@RunWith(Arquillian.class)
public class TestAuctionItemService {

    @Deployment
    public static Archive<?> createDeployment() throws Exception {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "a.war")
    }
    @EJB
    private AuctionItemService auctionItemService;
   
    @Test
    public void testAdd(){
        auctionItemService.add(new AuctionItem("testitem-AuctionManager"));
    }
}

To run in JBoss 7.1.1 container :
mvn -Parquillian-jbossas-managed clean install



