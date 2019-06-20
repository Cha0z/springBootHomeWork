package com.homework.home.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

@Slf4j
@Configuration
public class SecureInterceptor extends HandlerInterceptorAdapter {


    @Value("auth.jws.code")
    private String SECRET_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean isSuccess = false;
        try {
            validateActivity(request);
            isSuccess = true;
        } catch (SignatureException authenticationException) {
            JSONObject json = new JSONObject();
            log.info("Authentication Exception: {}", authenticationException.getMessage());

            responseObject(authenticationException.getMessage(), HttpServletResponse.SC_UNAUTHORIZED, false, json,
                    response, request);

        }
        return isSuccess;
    }


    public boolean validateActivity(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String from = request.getHeader(HttpHeaders.AUTHORIZATION);
        return decodeJWT(authorization).getSubject().equals(from);
    }

    private Claims decodeJWT(String authorization) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(authorization).getBody();
    }

    boolean responseObject(String message, Integer status, boolean error, JSONObject json,
                           HttpServletResponse httpResponse, HttpServletRequest httpRequest) {
        json.put("message", message);
        json.put("status", status);
        json.put("isSuccess", error);
        httpResponse.setStatus(status);
        return setHttpRequestResponse(httpRequest, httpResponse, json);
    }

    boolean setHttpRequestResponse(HttpServletRequest httpRequest, HttpServletResponse httpResponse, JSONObject json) {
        httpResponse.addHeader("Content-Type", "application/json");
        try {
            httpResponse.getWriter().write(json.toString());
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
