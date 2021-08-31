package org.sid.service.filter;

import org.sid.constantes.Constantes;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UrlAllowFrontFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        String requestUrL =  httpServletRequest.getRequestURL().substring(0,22);
            if(requestUrL.contains(Constantes.MEDYEDEK_URL)||requestUrL.contains(Constantes.MEDYEDEK_REAL_URL))
            {

                httpServletResponse.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
                httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, X-Auth-Token, Content-Type");
             }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
