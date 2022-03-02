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
    customersDBDAO = new CustomersDBDAO();
    couponsDBDAO = new CouponsDBDAO();
    customer = new Customer(5, "geri", "glazer", "geris-email", "geris-password", couponsDBDAO.getCouponsByCustomerId(3));
    loginManager = LoginManager.getInstance();
    try {
        coupon = couponsDBDAO.getOneCoupon(1);
    } catch (CustomExceptions e) {
        e.printStackTrace();
    }

    try {
        customerFacade =  (CustomerFacade) loginManager.login(customer.getEmail(), customer.getPassword(),ClientType.CUSTOMER);
    } catch (CustomExceptions e) {
        e.printStackTrace();
    }
}
    @Test
    public void getCustomerId(){
    Assert.assertEquals(customerFacade.getCustomerID(),3);
    }
    @Test
    public void Goodlogin(){
        Assert.assertTrue(customerFacade.login("geris-email","geris-password"));
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
        System.out.println(beforeAmount);
        System.out.println(afterAmount);
    //Assert.assertEquals(beforeAmount,afterAmount+1);
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
    @Test
    public void getCustomerCoupons(){
        try {
            customerFacade.getCustomerCoupons();
        } catch (CustomExceptions e) {
            e.printStackTrace();
        }

    }




}
