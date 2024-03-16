package top.haidong556.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.haidong556.response.ExceptionResponse;

import java.util.logging.Logger;

@ControllerAdvice
public class AllExceptionHandler {
    //private final Logger logger;

//    public AllExceptionHandler(Logger logger) {
//        this.logger = logger;
//    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ExceptionResponse handleSomeException(BaseException e){
        //logger
        return new ExceptionResponse(23,"df",e);
    }
}
