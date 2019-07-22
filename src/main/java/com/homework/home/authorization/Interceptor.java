package com.homework.home.authorization;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
@Configuration
public class Interceptor extends HandlerInterceptorAdapter {


  @Value("auth.jws.code")
  private String SECRET_KEY;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpServletRequest requestCacheWrapperObject
        = new ContentCachingRequestWrapper(request);

    log.info("Request was handled :{}",
        requestCacheWrapperObject); //тут уже надо исходить что конкретно нас интересует в запросе

    return true;
  }


}
