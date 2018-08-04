package restservice.util;

import restservice.exception.BadRequestException;
import spark.Request;

public class RequestUtil {

    public static String getParamValueFromRequest(String paramName, Request request) throws BadRequestException {
        if(!request.params().containsKey(paramName))
            throw new BadRequestException();
        return request.params(paramName);
    }

    public static int getIntFromRequest(String paramName, Request request) throws BadRequestException {
        try {
            return Integer.parseInt(request.params(paramName));
        } catch(NumberFormatException e) {
            throw new BadRequestException();
        }
    }
}
