import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.db.DBManager;
import clients.db.DBTools;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerTests {
private static CustomersDBDAO customersDBDAO;
private static Customer customer;

    @BeforeClass
    public static void message() {
        System.out.println("start test and initialize db-dao");
        customer = new Customer(5, "yoav", "hacmon", "yoavs-email", "yoavs-password", null);
        customersDBDAO = new CustomersDBDAO();



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
        customersDBDAO.getAllCustomers();

       }

       //customersDBDAO.getAllCustomers();
   @Test
   public void getOneCustomer(){
       //ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_CUSTOMERS);
       customersDBDAO.getOneCustomer(3);
   }

    @Test
    public void getOneCoupons() throws CustomExceptions {
        Coupon coupon = CouponsDBDAO.getOneCouponStatic(1);
        System.out.println(coupon);
    }

    @Test
        public void getCouponsByCustomerId() throws CustomExceptions {
            List<Coupon> coupons = CouponsDBDAO.getCouponsByCustomerId(3);
            System.out.println(coupons);
    }

    @Test
    public void addCouponToCustomer(){
        customersDBDAO.addCouponToCustomer(3,1);
    }









   /*
    @Test
    public void addCompanyAndGetOneCompany() throws CustomExceptions {
        Company company = new Company(4,"company","emails1","passwords1",null);
        companiesDBDAO.addCompany(company);
        Assert.assertEquals(company.getName(),companiesDBDAO.getOneCompany(company.getId()).getName());
    }
*/
}
