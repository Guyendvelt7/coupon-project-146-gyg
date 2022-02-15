package clients.facade;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.function.Predicate;

public class LoginManager {
    private static LoginManager instance = null;


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

    public static ClientFacade login(String email, String password, ClientType clientType) {
        ClientFacade cl=null;
        String
        Predicate<String> validation = isValidEmailAddress(email).or(isValidPassword(password));
                switch (clientType){
            case ADMINISTRATOR:
                if(validation.test()){

                }

                cl= ;
                break;
            case COMPANY:
                isValidEmailAddress(email);
                isValidPassword(password);
                break;
            case CUSTOMER:
                isValidEmailAddress(email);
                isValidPassword(password);
                break;
        }return
        //todo: switch case to clientype?


        return cl;
    }

    private static Predicate<String> isValidEmailAddress(String email) {
        return  (Predicate<String>) emailAdd-> email.contains("@")
                && email.contains(".com");
//        boolean result = true;
//        InternetAddress emailAddress = null;
//        try {
//            emailAddress = new InternetAddress(email);
//            emailAddress.validate();
//        } catch (AddressException exception) {
//            System.out.println(exception.getMessage());
//            result = false;
//        }
//        return result;
    }
    private static Predicate<String>isValidPassword (String password){
        return (Predicate<String>) pass -> password.length()>4
                && password.length()<10;
    }
}
