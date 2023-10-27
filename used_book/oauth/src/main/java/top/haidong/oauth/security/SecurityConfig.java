package top.haidong.oauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.haidong.oauth.service.impl.UserServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   MyLoginFilter loginFilter,
                                                   MyAuthenticationHandler authenticationHandler
                                                   ) throws Exception
    {


        http.authorizeHttpRequests(auth->{
            auth.anyRequest().authenticated();
        });

        http.formLogin(auth->{
            auth.loginPage("/login");
        });



        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout(auth->{
            auth.logoutSuccessHandler(authenticationHandler);
        });


        http.csrf(auth->{
            auth.disable();
        });


        http.sessionManagement(auth->{
            auth.disable();
                });

        http.rememberMe(auth->{
            auth.disable();
        });

        http.exceptionHandling(auth->{
            auth.authenticationEntryPoint(authenticationHandler)
                    .accessDeniedHandler(authenticationHandler)
                    .authenticationEntryPoint(authenticationHandler);
        });

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserServiceImpl userService) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setUserDetailsPasswordService(userService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }











}
