package top.haidong556.exception;



public enum EnumException implements ExceptionAssert {
    UnauthorizedAccessException(340,"hjioa",new BaseException());
    private final int code;
    private final String msg;
    private final BaseException exception;
    private EnumException(int c,String m,BaseException e){
        this.code=c;
        this.msg=m;
        this.exception=e;
    }

    @Override
    public <E> void assertEquals(E expected, E actual) throws BaseException {
        if(expected != actual){
            throw exception;
        }
    }
    @Override
    public void assertTrue(boolean condition) throws BaseException {
        if(condition!=true){
            throw exception;
        }
    }
    @Override
    public <E> void assertNotNull(E object) throws BaseException {
        if(object==null){
            throw exception;
        }
    }




}
