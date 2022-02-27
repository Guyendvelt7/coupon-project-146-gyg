import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.Company;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;

import java.time.LocalDate;

public class RegularTest {
    public static void main(String[] args) throws CustomExceptions {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        Company company = new Company(1,"company","email","password",null);
        companiesDBDAO.addCompany(company);


    }
}
