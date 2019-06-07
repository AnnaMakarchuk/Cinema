package org.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LocalizationFilter.class);

    private static final String LOCALE = "locale";
    private String defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        defaultLocale = filterConfig.getInitParameter(LOCALE);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeParameter = request.getParameter(LOCALE);
        LOG.info("Locale Parameter is " + localeParameter);
        if (isNotBlank(localeParameter)) {
            httpRequest.getSession().setAttribute(LOCALE, localeParameter);
        } else {
            String sessionLocale = (String) httpRequest.getSession().getAttribute(LOCALE);
            if (isBlank(sessionLocale)) {
                httpRequest.getSession().setAttribute(LOCALE, defaultLocale);
            }
        }
        httpRequest.getSession().getAttribute(LOCALE);
        filterChain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        LOG.info("Destroying of LocalizationFilter");
    }

    private boolean isBlank(String locale) {
        return locale == null || locale.isEmpty();
    }

    private boolean isNotBlank(String locale) {
        return !isBlank(locale);
    }
}
