package top.haidong556.exception;

public interface ExceptionAssert {

    public <E> void assertEquals(E expected,E actual) throws BaseException;
    public void assertTrue(boolean condition) throws BaseException;
    public <E> void assertNotNull(E object) throws BaseException;


}
