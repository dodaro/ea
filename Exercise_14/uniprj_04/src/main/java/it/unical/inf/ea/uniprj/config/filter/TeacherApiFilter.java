package it.unical.inf.ea.uniprj.config.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TeacherApiFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Filtro attivo su: " + httpRequest.getRequestURI());

        chain.doFilter(request, response);

        //nothing
    }
}