package clients.facade;

import clients.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;

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
        //Predicate<String> validation = isValidEmailAddress(email).or(isValidPassword(password));
        switch (clientType) {
            case ADMINISTRATOR:
                try {
                    if (adminFacade.login(email, password)) {
                        System.out.println("admin connected");
                        return new AdminFacade();
                    } else {
                        return null;
                    }
                } catch (CustomExceptions e) {
                    e.printStackTrace();
                }
            case COMPANY:
                if (companyFacade.login(email, password)) {
                    List<Company> companies = null;
                    try {
                        companies = companyFacade.companiesDBDAO.getAllCompanies().stream()
                                .filter(item -> Objects.equals(item.getPassword(), password))
                                .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList());
                    } catch (CustomExceptions e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(companies.get(0).getName()+" connected");
                    return new CompanyFacade(companies.get(0).getId());
                } else {
                    return null;
                }
            case CUSTOMER:
                if (customerFacade.login(email, password)) {
                    List<Customer> customers = customerFacade.customersDBDAO.getAllCustomers().stream()
                            .filter(item -> Objects.equals(item.getPassword(), password))
                            .filter(item -> Objects.equals(item.getEmail(), email)).collect(Collectors.toList());
                    System.out.println(customers.get(0).getFirstName()+" connected");
                    return new CustomerFacade(customers.get(0).getId());
                } else {
                    return null;
                }
            default:
                System.out.println("wrong client type");
                return null;
        }
    }

    private static Predicate<String> isValidEmailAddress(String email) {
        return (Predicate<String>) emailAdd -> email.contains("@")
                && email.contains(".com");
    }

    private static Predicate<String> isValidPassword(String password) {
        return (Predicate<String>) pass -> password.length() > 4
                && password.length() < 10;
    }
}
