package com.avgkin.tacocloudplusserver.config.security;


import com.avgkin.tacocloudplusserver.service.UserService;
import com.avgkin.tacocloudplusserver.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String jwtToken = request.getHeader("token");//从请求头中获取token
        if (jwtToken != null && !jwtToken.isEmpty() && jwtUtil.checkToken(jwtToken)){
            try {//token可用
                Claims claims = jwtUtil.getTokenBody(jwtToken);
                String userName = (String) claims.get("userName");
                UserDetails user = userDetailsService.loadUserByUsername(userName);
                if (user != null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }else {
            log.warn("token is null or empty or out of time, probably user is not log in !");
        }
        filterChain.doFilter(request, response);//继续过滤
    }
}