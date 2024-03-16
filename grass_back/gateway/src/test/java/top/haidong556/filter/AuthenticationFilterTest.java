package top.haidong556.filter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationFilterTest {
    static AuthenticationFilter authenticationFilter;
    @BeforeAll
    static void init(){
        authenticationFilter=new AuthenticationFilter();
    }

    @Test
    void filter() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void verify() {


    }
}