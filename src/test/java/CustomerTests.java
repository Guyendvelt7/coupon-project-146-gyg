import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CustomersDBDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CustomerTests {
    private static CustomersDBDAO customersDBDAO;


    @BeforeClass
    public static void message() {
        System.out.println("start test and initialize db-dao");
        customersDBDAO = new CustomersDBDAO();

    }
    @Test
    public void addCustomer() throws CustomExceptions {
        Customer customer = new Customer( 3,"yoav","hacmon","yoavs-email","yoavs-password",null);
        customersDBDAO.addCustomer(customer);
        Assert.assertEquals(customer,customersDBDAO.getOneCustomer(3));
    }

    @Test
    public void customerDoesNotExist() throws CustomExceptions {
        Assert.assertFalse(customersDBDAO.isCustomerExist("customer123", "password123"));
   }
    @Test
    public void customerExist() throws CustomExceptions {
        Assert.assertTrue(customersDBDAO.isCustomerExist("yoavs-email", "yoavs-password"));
    }
    @Test
    public void updateCustomer(){
        Customer customer = new Customer(1,"guy","endvelt","guys-email","guys-password",null);
        customersDBDAO.updateCustomer(customer);
        Assert.assertEquals(customer,customersDBDAO.getOneCustomer(1));
    }
    @Test
    public void deleteCustomer(){
        customersDBDAO.deleteCustomer(1);
    }
    @Test
    public void getAllCustomers(){
        List<Customer> customerList = customersDBDAO.getAllCustomers()

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
