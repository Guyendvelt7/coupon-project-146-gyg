package clients.facade;

import clients.EnumExceptions;
import clients.CustomExceptions;

public class AdminFacade extends ClientFacade {
    private final String email;
    private final String password;
    private boolean isLoginCorrect;

    public AdminFacade() {
        this.email = "admin@admin.com";
        this.password = "admin";

    }


    @Override
    public boolean login(String email, String password) throws CustomExceptions {
        if (!this.email.equals(email)) {
            isLoginCorrect = isIncorrect();
            throw new CustomExceptions(EnumExceptions.INVALID_EMAIL);
        }
        if (!this.password.equals(password)) {
            isLoginCorrect = isIncorrect();
            throw new CustomExceptions(EnumExceptions.INVALID_PASSWORD);
        }
        return true;
    }

    public boolean isIncorrect() {
        return isLoginCorrect = false;
    }
}
