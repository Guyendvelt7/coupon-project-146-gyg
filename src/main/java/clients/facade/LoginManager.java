package clients.facade;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

public class LoginManager {
    private static LoginManager instance = null;
    private static AdminFacade adminFacade;
    private static CustomerFacade customerFacade;
    private static CompanyFacade companyFacade;
    private static CustomersDBDAO customersDBDAO= new CustomersDBDAO();
    private static CompaniesDBDAO companiesDBDAO= new CompaniesDBDAO();
    private static CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    //todo: use the predicates in the login
    static Predicate<String> isEmailValid = email -> email.contains("@")
            && email.contains(".com");
    static Predicate<String> isPasswordValid = pass -> pass.length() > 4
            && pass.length() < 10 && pass.contains("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
                    instance = new LoginManager();
            }
        return instance;
        }

    public ClientFacade login(String email, String password, ClientType clientType) throws CustomExceptions {
        isEmailValid.test(email);
        isPasswordValid.test(password);
        switch (clientType) {
            case ADMINISTRATOR:
                adminFacade = new AdminFacade();
                if (adminFacade.login(email, password)) {
                    System.out.println("admin connected");
                    return adminFacade;
                } else {
                    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }

            case COMPANY:
                companiesDBDAO = new CompaniesDBDAO();
                if (companiesDBDAO.isCompanyExists(email, password)) {
                    Company company = companiesDBDAO.getAllCompanies().stream()
                            .filter(item -> Objects.equals(item.getPassword(), password))
                            .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList()).get(0);
                    System.out.println(company.getName() + " connected");
                    return new CompanyFacade(company.getId());
                } else {
                    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }
            case CUSTOMER:
                customersDBDAO = new CustomersDBDAO();
                if (customersDBDAO.isCustomerExist(email, password)) {
                    Customer customer = customersDBDAO.getAllCustomers().stream()
                            .filter(item -> Objects.equals(item.getPassword(), password))
                            .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList()).get(0);
                    System.out.println(customer.getFirstName() + " connected");
                    return new CustomerFacade(customer.getId());
                } else {
                    throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
                }

            default:
                System.out.println("wrong client type");
                return null;
        }
    }
}
