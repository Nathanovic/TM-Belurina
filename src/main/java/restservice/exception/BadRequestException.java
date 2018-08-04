package restservice.exception;

public class BadRequestException extends Exception {
    public BadRequestException(){
        super("Bad request.");
    }
}