package clients;

public class CustomExceptions extends Exception{

    public CustomExceptions(EnumExceptions enumExceptions){
        super(enumExceptions.getMessage());
    }

}
