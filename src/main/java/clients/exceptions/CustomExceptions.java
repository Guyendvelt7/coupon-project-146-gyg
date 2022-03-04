package clients.exceptions;

public class CustomExceptions extends Exception{

    public CustomExceptions(EnumExceptions enumExceptions){
        super(enumExceptions.getMessage());
    }

}
