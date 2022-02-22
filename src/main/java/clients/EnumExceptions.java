package clients;

public enum EnumExceptions {

    NAME_EXIST("Cannot add existing name."),
    EMAIL_EXIST("Cannot add existing email."),
    INVALID_EMAIL("Invalid email."),
    ID_NOT_EXIST("Cannot update/remove, invalid ID."),
    INVALID_PASSWORD("Invalid password."),
    COUPON_TITLE_EXIST("Cannot add coupon, title already exist."),
    COUPON_PURCHASED("Cannot purchase this item, already in your list."),
    EMPTY_RESULT_SET("no data in result set, the result set doesnt have next"),
    RESULT_SET_DATA_PROBLEM("problem in the result set data, sql context"),
    NO_COMPANIES("there are no companies in the system")
    ;


    private String message;

    private EnumExceptions(String message){
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}