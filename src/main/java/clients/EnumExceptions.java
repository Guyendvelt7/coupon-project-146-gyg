package clients;

public enum EnumExceptions {

    NAME_EXIST("Cannot add existing name."),
    EMAIL_EXIST("Cannot add existing email."),
    INVALID_EMAIL("Invalid email."),
    ID_NOT_EXIST("Cannot update/remove, invalid ID."),
    INVALID_PASSWORD("Invalid password."),
    ;

    private String message;

    EnumExceptions(String message){
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
