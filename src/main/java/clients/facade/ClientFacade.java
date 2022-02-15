package clients.facade;

import clients.CustomExceptions;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;

public abstract class ClientFacade {

    protected CouponsDBDAO myCoupons;
    protected CompaniesDBDAO myCompanies;
    protected CustomersDBDAO myCustomers;

    public abstract boolean login(String email, String password) throws CustomExceptions;

}