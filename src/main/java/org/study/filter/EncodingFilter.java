package org.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

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
        LOG.info("Destroying of EncodingFilter");
    }
}
