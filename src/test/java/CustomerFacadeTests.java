import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import clients.facade.ClientFacade;
import clients.facade.ClientType;
import clients.facade.CustomerFacade;
import clients.facade.LoginManager;
import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerFacadeTests {
private static Customer customer;
private static CustomersDBDAO customersDBDAO;
private static CouponsDBDAO couponsDBDAO;
private static LoginManager loginManager;
private static CustomerFacade customerFacade;
private static Coupon coupon;

@BeforeClass
public static void InitAndMessage(){
    System.out.println("start test and initialize db-dao");
    customer = new Customer(5, "yoav", "hacmon", "yoavs-email", "yoavs-password", null);
    customersDBDAO = new CustomersDBDAO();
    couponsDBDAO = new CouponsDBDAO();
    loginManager = LoginManager.getInstance();
    try {
        coupon = couponsDBDAO.getOneCoupon(1);
    } catch (CustomExceptions e) {
        e.printStackTrace();
    }

    try {
        customerFacade =  (CustomerFacade) loginManager.login("golans-email","golans-password",ClientType.CUSTOMER);
    } catch (CustomExceptions e) {
        e.printStackTrace();
    }
}
    @Test
    public void getCustomerId(){
    Assert.assertEquals(customerFacade.getCustomerID(),4);
    }
    @Test
    public void Goodlogin(){
        Assert.assertTrue(customerFacade.login("golans-email","golans-password"));
    }
    @Test
    public void Badlogin(){
        Assert.assertFalse(customerFacade.login("zeev-email","zeev-password"));
    }
    @Test
    public void purchaseCoupon(){
    int beforeAmount = coupon.getAmount();
    customerFacade.purchaseCoupon(coupon);
    int afterAmount = coupon.getAmount();
    Assert.assertEquals(beforeAmount,afterAmount+1);
    }

    @Test
    public void cantPurchaseCoupon(){
    coupon.setAmount(0);
        try {
            Assert.assertFalse(customerFacade.canPurchaseCoupon(coupon));
        } catch (CustomExceptions e) {
            e.printStackTrace();
        }
    }




}
