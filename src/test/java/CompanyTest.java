import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.dbDao.CompaniesDBDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CompanyTest {
    private static CompaniesDBDAO companiesDBDAO;
    private static Company company;


    @BeforeClass
    public static void init() {
        companiesDBDAO = new CompaniesDBDAO();
        company = new Company(4, "Bug", "bug@oketz.com", "53d7jh",
                List.of(new Coupon(), new Coupon(), new Coupon()));
    }

    @Test
    public void addCompany() {
        try {
            companiesDBDAO.addCompany(company);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void companyDoesNotExist() {
        Assert.assertFalse(companiesDBDAO.isCompanyExists("skfj@ggg.com", "dfg5yh"));
    }

    @Test
    public void companyExist() throws CustomExceptions {
        Assert.assertTrue(companiesDBDAO.isCompanyExists("bug@oketz.com", "53d7jh"));
    }

    @Test
    public void updateCompany() {
        //todo: not working
        company.setPassword("1234");
        try {
            companiesDBDAO.updateCompany(company);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void deleteCompany() {
        try {
            companiesDBDAO.deleteCompany(company.getId());
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getAllCompanies() {
        try {
            List<Company> companyList = companiesDBDAO.getAllCompanies();
            companyList.forEach(System.out::println);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }

    @Test
    public void getOneCompany() {
        try {
            System.out.println(companiesDBDAO.getOneCompany(2));
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
        }
    }
}
