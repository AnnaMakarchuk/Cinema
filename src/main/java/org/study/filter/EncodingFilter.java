package org.study.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encodingValue;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingValue = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        LOG.info("Set encoding " + encodingValue);
        request.setCharacterEncoding(encodingValue);
        response.setCharacterEncoding(encodingValue);
        response.setContentType("text/html; charset=" + encodingValue);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying of EncodingFilter");
    }
}
