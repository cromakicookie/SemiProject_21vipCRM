package com.web.config.jwt;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.web.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	
//
//    private final AuthenticationManager authenticationManager;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        log.info("========= attemptAuthentication : 로그인 시도중 =========");
//
//        try {
//        
//            ObjectMapper objectMapper = new ObjectMapper();
//            Member member = objectMapper.readValue(request.getInputStream(), Member.class);
//
//            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
//            Authentication authenticate = authenticationManager.authenticate(userToken);            
//
//            return authenticate;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info("successfulAuthentication 인증 성공됨 ");
//        PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();
//
//        // RSA방식은 아니고 Hash암호 방식
//        // HAMC > 시크릿값을 가지고 있어야함
//        String jwtToken = JWT.create()
//                .withSubject("cos토큰")
//                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 30) ))// 만료시간 (30분) : 현재시간 + 사용시간
//                .withClaim("username", principal.getUsername())
//                .withClaim("memberRole", principal.getAuthorities().toString())
//                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
//
//        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
//    }
//}