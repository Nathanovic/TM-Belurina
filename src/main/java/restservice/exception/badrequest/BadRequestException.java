package restservice.exception.badrequest;

public class BadRequestException extends Exception {
    public BadRequestException(){
        super("Bad request.");
    }
}