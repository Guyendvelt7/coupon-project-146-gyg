package clients.facade;

import clients.Exceptions;

public class AdminFacade extends ClientFacade{
    private final String email;
    private final String password;

    public AdminFacade() {
        this.email = "admin@admin.com";
        this.password = "admin";
    }

    @Override
    public boolean login(String email, String password) throws Exceptions {

        return false;
    }
}
