package com.sio.poveacl.acl.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class PoveSecurityConfig {

    private final
    AuthUserDetailsService authUserDetailsService;

    private final
    JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(authUserDetailsService);
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    private static final String[] IGNORED_URL = new String[]{
            "/auth/**", "/public" ,"/v3/api-docs/**","/swagger-ui/**"
    };

    void ignoredPrivileges(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers(IGNORED_URL).permitAll());
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin();
        httpSecurity.logout().logoutUrl("/logout");

        this.ignoredPrivileges(httpSecurity);
        this.userPrivileges(httpSecurity); //user authority

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(new JwtAuthorizationFilter(jwtService, authUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeHttpRequests(http -> http.anyRequest().authenticated());

        httpSecurity.exceptionHandling(
                exception -> exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                    request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                })
        );

        return httpSecurity.build();
    }

    void userPrivileges(HttpSecurity httpSecurity) throws Exception {
        for (var authRecord : ResourceUrl.getUserFeatureList()) {
            httpSecurity.authorizeHttpRequests(http -> http.requestMatchers(authRecord.url()).hasAuthority(authRecord.name()));
        }
    }
}
