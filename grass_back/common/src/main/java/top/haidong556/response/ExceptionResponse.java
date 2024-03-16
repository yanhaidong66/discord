package top.haidong556.response;

public class ExceptionResponse<E extends Exception> extends BaseResponse<E>{

    public ExceptionResponse(int code, String msg, E e) {
        super(code, msg, e);
    }
}
