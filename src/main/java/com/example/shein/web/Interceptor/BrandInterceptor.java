package com.example.shein.web.Interceptor;

import com.example.shein.service.StatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BrandInterceptor implements HandlerInterceptor {

    private final StatsService statsService;

    public BrandInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        statsService.brandRequests();
        return true;
    }
}
