package clients.facade;

import clients.CustomExceptions;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;

public abstract class ClientFacade {

    protected CouponsDBDAO couponsDBDAO;
    protected CompaniesDBDAO companiesDBDAO;
    protected CustomersDBDAO customersDBDAO;

    public abstract boolean login(String email, String password) throws CustomExceptions;

}