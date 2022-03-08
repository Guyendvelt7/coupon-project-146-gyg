package clients.facade;

import clients.exceptions.CustomExceptions;
import clients.beans.Company;
import clients.beans.Customer;
import clients.exceptions.EnumExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to an administrator
 */
public class AdminFacade extends ClientFacade {
    private String id = "0";
    private final String uniqueId = UUID.randomUUID().toString();


    /**
     * Empty administrator constructor
     */
    public AdminFacade() {
    }

    /**
     * login verification
     *
     * @param email    input by user
     * @param password input by user
     * @return verification result
     */
    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            this.id = uniqueId;
            return true;
        }
        return false;
    }

    public void addCompany(Company company) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.companiesDBDAO.addCompany(company);
            } catch (CustomExceptions e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public void updateCompany(Company company) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.companiesDBDAO.updateCompany(company);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public void deleteCompany(int companyID) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.companiesDBDAO.deleteCompany(companyID);
            } catch (CustomExceptions e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public List<Company> getAllCompanies() throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                return this.companiesDBDAO.getAllCompanies();
            } catch (CustomExceptions e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public Company getOneCompany(int companyID) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            Company comp = null;
            try {
                comp = this.companiesDBDAO.getOneCompany(companyID);

                assert comp != null;
                comp.setCoupons(this.couponsDBDAO.getCouponsByCompanyId(companyID));
            } catch (CustomExceptions e) {
                System.out.println(e.getMessage());
            }
            return comp;
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public void addCustomer(Customer customer) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.customersDBDAO.addCustomer(customer);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public void updateCustomer(Customer customer) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.customersDBDAO.updateCustomer(customer);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public void deleteCustomer(int customerID) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            try {
                this.customersDBDAO.deleteCustomer(customerID);
            } catch (CustomExceptions customExceptions) {
                System.out.println(customExceptions.getMessage());
            }
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public List<Customer> getAllCustomers() throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            List<Customer> customerList = new ArrayList<>();
            customerList = this.customersDBDAO.getAllCustomers();
            return customerList;
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }

    public Customer getOneCustomer(int customerID) throws CustomExceptions {
        if (Objects.equals(this.id, uniqueId)) {
            Customer customer = null;
            customer = this.customersDBDAO.getOneCustomer(customerID);
            return customer;
        } else {
            throw new CustomExceptions(EnumExceptions.NOT_ADMIN);
        }
    }
}
