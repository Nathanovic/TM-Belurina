package restservice.exception;

import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.exception;

public class ExceptionController {

    public ExceptionController(){
        exception(BadRequestException.class, (exception, request, response) -> {
            response.status(HttpStatus.BAD_REQUEST_400);
            response.body(exception.getMessage());
        });
        exception(ConflictException.class, (exception, request, response) -> {
            response.status(HttpStatus.CONFLICT_409);
            response.body(exception.getMessage());
        });
    }

}
