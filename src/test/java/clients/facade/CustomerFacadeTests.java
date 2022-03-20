package clients.facade;

import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import clients.exceptions.CustomExceptions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Hacmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * Customer facade tests
 */
public class CustomerFacadeTests {
    private static CustomersDBDAO customersDBDAO;
    private static Customer customer;
    private static CouponsDBDAO couponsDBDAO;
    private static CustomerFacade customerFacade;;
    private static LoginManager loginManager;
    private static Coupon coupon;


    @BeforeClass
    public static void init() throws CustomExceptions {
        System.out.println("Starting tests for customer facade");
        customerFacade = new CustomerFacade();
        customersDBDAO = new CustomersDBDAO();
//        AdminFacade adminFacade = new AdminFacade();
//        customer = customersDBDAO.getOneCustomer(1);
//        customer=new Customer(6, "Oren", "Levi", "oren@gmail.com", "8521", new ArrayList<>());
        loginManager = LoginManager.getInstance();
        couponsDBDAO = new CouponsDBDAO();
        try {
//            adminFacade.addCustomer(customer);
        coupon = couponsDBDAO.getOneCoupon(1);
            customerFacade = (CustomerFacade) loginManager.login("taltul@gmail.com", "101010", ClientType.CUSTOMER);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void loginPass() {
        try {
            Assert.assertTrue((loginManager.login(customer.getEmail(), customer.getPassword(), ClientType.CUSTOMER) instanceof CustomerFacade));
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void loginFail() {
        try {
            Assert.assertNull(loginManager.login("zeev-email", "zeev-password", ClientType.CUSTOMER));
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
        System.out.println(customerFacade.getCustomerID());
//        customer = customersDBDAO.getOneCustomer(1);
        //int beforeAmount = coupon.getAmount();
//        coupon = couponsDBDAO.getOneCoupon(1);
        customerFacade.purchaseCoupon(coupon);
        //int afterAmount = coupon.getAmount();
        //Assert.assertEquals(beforeAmount, afterAmount + 1);
    }

    @Test
    public void getCustomerCoupons() throws CustomExceptions {
        customerFacade.getCustomerCoupons().forEach(System.out::println);
//        Assert.assertEquals(customerFacade.getCustomerCoupons().get(0).getId(), 1);
//        Assert.assertEquals(customerFacade.getCustomerCoupons().get(1).getId(), 2);
//        Assert.assertEquals(customerFacade.getCustomerCoupons().get(2).getId(), 5);
//        Assert.assertEquals(customerFacade.getCustomerCoupons().get(3).getId(), 6);
    }

    @Test
    public void getEmptyCustomerCoupons() throws CustomExceptions {
        List<Coupon> couponList = customerFacade.getCustomerCoupons();
        System.out.println(couponList);
    }

    @Test
    public void getCustomerCouponsByCategory() {
        try {
            customerFacade.getCustomerCoupons(Category.ELECTRICITY).forEach(System.out::println);
        Assert.assertEquals(customerFacade.getCustomerCoupons(Category.FOOD).get(0).getId(), 1);
        Assert.assertEquals(customerFacade.getCustomerCoupons(Category.ENTERTAINMENT).get(0).getId(), 5);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getCustomerCouponsByPrice() throws CustomExceptions {
        customerFacade.getCustomerCoupons(50).forEach(System.out::println);
        Assert.assertEquals(customerFacade.getCustomerCoupons(50).get(0).getId(), 1);
    }

    @Test(expected = CustomExceptions.class)
    public void getEmptyCustomerCouponsByPrice() throws CustomExceptions {
        customerFacade.getCustomerCoupons(10).forEach(System.out::println);
    }

    @Test
    public void getCustomerDetails() {
        Customer customerDetails = null;
        try {
            customerDetails = customerFacade.getCustomerDetails();
        System.out.println(customerDetails);
        System.out.println(customer);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }
}
