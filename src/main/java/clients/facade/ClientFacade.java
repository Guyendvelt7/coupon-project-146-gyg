package clients.facade;

import clients.CustomExceptions;
import clients.dao.CompaniesDAO;
import clients.dao.CouponsDAO;
import clients.dao.CustomersDAO;


public abstract class ClientFacade {

    protected CouponsDAO myCoupons;
    protected CompaniesDAO myCompanies;
    protected CustomersDAO myCustomers;

    public abstract boolean login(String email, String password) throws CustomExceptions;

}