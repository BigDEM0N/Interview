package com.avgkin.tacocloudplusserver.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //开启Spring Security的功能
@EnableGlobalAuthentication
public class WebSecurityConfig{

    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private JwtFilter jwtFilter;
    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder,JwtFilter jwtFilter){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
//                .formLogin(formLogin->formLogin.loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/user/login")
//                        .defaultSuccessUrl("/home").failureUrl("/login?error=true"))
//                .logout(logout->logout.logoutUrl("/logout").permitAll())
//                .sessionManagement(sessionManagement->sessionManagement.invalidSessionUrl("/login").maximumSessions(1).maxSessionsPreventsLogin(true))
//                .csrf(csrf->csrf.disable())
//                .build();
        return http.formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request.requestMatchers(HttpMethod.POST,"/user/login","/user/register").permitAll().anyRequest().hasRole("USER"))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
