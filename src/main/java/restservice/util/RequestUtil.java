package restservice.util;

import restservice.exception.badrequest.BadRequestException;
import spark.Request;

public class RequestUtil {

    public static int getIntFromRequest(String paramName, Request request) throws BadRequestException {
        try {
            return Integer.parseInt(request.params(paramName));
        } catch(NumberFormatException e) {
            throw new BadRequestException();
        }
    }
}
