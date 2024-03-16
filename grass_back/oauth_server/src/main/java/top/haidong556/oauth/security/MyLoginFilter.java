package top.haidong556.oauth.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.haidong556.oauth.service.impl.UserServiceImpl;

@Component
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    public MyLoginFilter(AuthenticationManager myAuthenticationManager,MyAuthenticationHandler myAuthenticationHandler){
        super(myAuthenticationManager);
        this.setAuthenticationSuccessHandler(myAuthenticationHandler);
        this.setAuthenticationFailureHandler(myAuthenticationHandler);
        this.setFilterProcessesUrl("/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username=obtainUsername(request);
        String password=obtainPassword(request);
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return  request.getParameter("password");
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("username");
    }


}
