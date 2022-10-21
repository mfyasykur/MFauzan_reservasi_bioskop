package edu.Binar.challenge.CinemaTicketReservation.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletResponse.getHeader("origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
        httpServletResponse.setHeader("Access-Control-Max-Age", String.valueOf(180));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
