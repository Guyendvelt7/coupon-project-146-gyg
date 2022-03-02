import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.db.DBManager;
import clients.db.DBTools;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import clients.facade.ClientFacade;
import clients.facade.ClientType;
import clients.facade.CustomerFacade;
import clients.facade.LoginManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerTests {
private static CustomersDBDAO customersDBDAO;
private static CouponsDBDAO couponsDBDAO;
private static Customer customer;
private static LoginManager loginManager;
    @BeforeClass
    public static void message() {
        System.out.println("start test and initialize db-dao");
        customer = new Customer(5, "yoav", "hacmon", "yoavs-email", "yoavs-password", null);
        customersDBDAO = new CustomersDBDAO();
        couponsDBDAO = new CouponsDBDAO();
        loginManager = LoginManager.getInstance();
    }

    @Test
    public void customerLogin(){
        ClientType customerType = ClientType.CUSTOMER;
        try {
            ClientFacade customerFacade =  loginManager.login("golans-email","golans-password",customerType);
            Assert.assertTrue(customerFacade instanceof CustomerFacade);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void addCustomer() throws CustomExceptions {
        customersDBDAO.addCustomer(customer);

    }

    @Test
    public void customerDoesNotExist() throws CustomExceptions {
        Assert.assertFalse(customersDBDAO.isCustomerExist("customer123", "password123"));
    }

    @Test
    public void customerExist() throws CustomExceptions {
        Assert.assertTrue(customersDBDAO.isCustomerExist("geris-email", "geris-password"));
    }

    @Test
    public void updateCustomer() {
        customer.setEmail("yoyo123-email");
        try {
            customersDBDAO.updateCustomer(customer);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteCustomer() {
        try {
            customersDBDAO.deleteCustomer(8);
        } catch (CustomExceptions e) {
            System.out.println(e.getMessage());
        }
    }

   @Test
   public void getAllCustomers(){
        List<Customer> customers = customersDBDAO.getAllCustomers();
        customers.forEach(System.out::println);

       }

       //customersDBDAO.getAllCustomers();
   @Test
   public void getOneCustomer(){
       System.out.println(customersDBDAO.getOneCustomer(4));
   }

    @Test
    public void getOneCoupons() throws CustomExceptions {
        Coupon coupon = couponsDBDAO.getOneCoupon(1);
        System.out.println(coupon);
    }

    @Test
        public void getCouponsByCustomerId() throws CustomExceptions {
            List<Coupon> coupons = couponsDBDAO.getCouponsByCustomerId(3);
            System.out.println(coupons);
    }

    @Test
    public void addCouponToCustomer(){
        customersDBDAO.addCouponToCustomer(1,3);
    }


}
