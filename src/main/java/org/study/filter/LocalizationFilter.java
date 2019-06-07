package org.study.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LocalizationFilter.class);

    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    private String defaultBundle;
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {
        locale = filterConfig.getInitParameter(LOCALE);
        defaultBundle = filterConfig.getInitParameter(BUNDLE);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeParameter = httpRequest.getParameter(LOCALE);
        LOG.info("Locale Parameter is " + localeParameter);
        if (localeParameter != null && !localeParameter.isEmpty()) {
            httpRequest.getSession().setAttribute(LOCALE, localeParameter);
        } else httpRequest.getSession().setAttribute(LOCALE, locale);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying of LocalizationFilter");
    }
}
