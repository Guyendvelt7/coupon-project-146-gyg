import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CustomerTests {
    private static CustomersDBDAO customersDBDAO;
    private static CouponsDBDAO couponsDBDAO;


    @BeforeClass
    public static void message() {
        System.out.println("start test and initialize db-dao");
        customersDBDAO = new CustomersDBDAO();
        couponsDBDAO = new CouponsDBDAO();

    }
    @Test
    public void addCustomer() throws CustomExceptions {
        Customer customer = new Customer( 3,"geri","glazer","geris-email","geris-password",null);
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
    public void updateCustomer(){
        Customer customer = new Customer(1,"guy","endvelt","guys-email","guys-password",null);
        customersDBDAO.updateCustomer(customer);
    }
    @Test
    public void deleteCustomer() {
        customersDBDAO.deleteCustomer(1);
    }
    @Test
    public void getAllCustomers(){
        List<Customer> customerList = customersDBDAO.getAllCustomers();
        customerList.forEach(System.out::println);

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
