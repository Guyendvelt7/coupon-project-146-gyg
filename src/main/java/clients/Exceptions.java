package clients;

public class Exceptions extends Exception{

    public Exceptions (EnumExceptions enumExceptions){
        super(enumExceptions.getMessage());
    }

}
