package clients.facade;

import clients.CustomExceptions;
import clients.dao.CompaniesDAO;
import clients.dao.CouponsDAO;
import clients.dao.CustomersDAO;


public abstract class ClientFacade {

    protected CouponsDAO couponsDAO;
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;

    public abstract boolean login(String email, String password) throws CustomExceptions;

}