import clients.exceptions.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import clients.facade.ClientType;
import clients.facade.CustomerFacade;
import clients.facade.LoginManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerFacadeTests {
    private static CustomersDBDAO customersDBDAO;
    private static Customer customer;
    private static CouponsDBDAO couponsDBDAO;
    private static CustomerFacade customerFacade;
    private static LoginManager loginManager;
    private static Coupon coupon;


    @BeforeClass
    public static void initializing() {
        System.out.println("starting initialize");
        customersDBDAO = new CustomersDBDAO();
        loginManager = LoginManager.getInstance();
        couponsDBDAO = new CouponsDBDAO();
        try {
            customer = customersDBDAO.getOneCustomer(3);
            System.out.println(customer);
            coupon = couponsDBDAO.getOneCoupon(7);
            System.out.println(coupon);
            customerFacade = (CustomerFacade) loginManager.login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void goodLogin() throws CustomExceptions {
        System.out.println(customer.getEmail());
        System.out.println(customer.getPassword());
        Assert.assertTrue((loginManager.login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER) instanceof CustomerFacade));
    }

    @Test
    public void Badlogin() {
        try {
            Assert.assertFalse(loginManager.login("zeev-email", "zeev-password", ClientType.CUSTOMER) instanceof CustomerFacade);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test(expected = CustomExceptions.class)
    public void exceptionLogin() throws CustomExceptions {
        loginManager.login("zeev-email", "zeev-password", ClientType.CUSTOMER);
    }

    @Test
    public void purchaseCoupon() throws CustomExceptions {
        int beforeAmount = coupon.getAmount();
        customerFacade.purchaseCoupon(coupon);
        coupon = couponsDBDAO.getOneCoupon(7);
        int afterAmount = coupon.getAmount();
        Assert.assertEquals(beforeAmount, afterAmount + 1);
        Assert.assertEquals(customerFacade.getCustomerCoupons().get(4).getId(), couponsDBDAO.getOneCoupon(7).getId());
    }

    @Test
    public void getCustomerCoupons() throws CustomExceptions {
        customerFacade.getCustomerCoupons().forEach(System.out::println);
        Assert.assertEquals(customerFacade.getCustomerCoupons().get(0).getId(), 1);
        Assert.assertEquals(customerFacade.getCustomerCoupons().get(1).getId(), 2);
        Assert.assertEquals(customerFacade.getCustomerCoupons().get(2).getId(), 5);
        Assert.assertEquals(customerFacade.getCustomerCoupons().get(3).getId(), 6);

    }

    @Test
    public void getCustomerCouponsByCategory() throws CustomExceptions {
        customerFacade.getCustomerCoupons(Category.FOOD).forEach(System.out::println);
        Assert.assertEquals(customerFacade.getCustomerCoupons(Category.FOOD).get(0).getId(), 1);
        Assert.assertEquals(customerFacade.getCustomerCoupons(Category.ENTERTAINMENT).get(0).getId(), 5);
    }

    @Test
    public void getCustomerCouponsByPrice() throws CustomExceptions {
        customerFacade.getCustomerCoupons(50).forEach(System.out::println);
        Assert.assertEquals(customerFacade.getCustomerCoupons(50).get(0).getId(), 1);
    }

    @Test
    public void getCustomerDetails() {
        Customer customerDetails = customerFacade.getCustomerDetails();
        System.out.println(customerDetails);
        Assert.assertEquals(customer.getId(), customerDetails.getId());
    }

}
