package clients.facade;

import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LoginManager {
    private static LoginManager instance = null;
    private static AdminFacade adminFacade;
    private static CustomerFacade customerFacade;
    private static CompanyFacade companyFacade;
    private static CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    private static CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    private static CustomersDBDAO customersDBDAO = new CustomersDBDAO();


    static Predicate<String> isEmailValid = email -> email.contains("@")
            && email.contains(".com");
    static Predicate<String> isPasswordValid = pass -> pass.length() > 4
            && pass.length() < 10 && pass.contains("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");

    private LoginManager() {

    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public static ClientFacade login(String email, String password, ClientType clientType) throws CustomExceptions {
        isEmailValid.test(email);
        isPasswordValid.test(password);
        switch (clientType) {
            case ADMINISTRATOR:
                if (adminFacade.login(email, password)) {
                    System.out.println("admin connected");
                    return new AdminFacade();
                } else {
                    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }
            case COMPANY:
                if (companiesDBDAO.isCompanyExists(email, password)) {
                    Company company = companyFacade.companiesDBDAO.getAllCompanies().stream()
                            .filter(item -> Objects.equals(item.getPassword(), password))
                            .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList()).get(0);
                    System.out.println(company.getName() + " connected");
                    return new CompanyFacade(company.getId());
                } else {
                    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }
            case CUSTOMER:
                if (customersDBDAO.isCustomerExist(email, password)) {
                    Customer customer = customersDBDAO.getAllCustomers().stream()
                            .filter(item -> Objects.equals(item.getPassword(), password))
                            .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList()).get(0);
                    System.out.println(customer.getFirstName() + " connected");
                    return new CustomerFacade(customer.getId());
                } else {
                    System.out.println("customer doesnt exists");
                    //throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }
            default:
                System.out.println("wrong client type");
                return null;
        }
    }


}
