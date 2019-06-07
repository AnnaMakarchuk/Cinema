package org.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import org.study.dto.RegisteredUserDto;
import org.study.models.enums.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        RegisteredUserDto registeredUserDTO = (RegisteredUserDto) httpRequest.getSession().getAttribute("user");
        if (registeredUserDTO == null || registeredUserDTO.getUserRole() != UserRole.ADMINISTRATOR) {
            LOG.info("User not an administrator. Access denied");
            httpRequest.getRequestDispatcher("jsp/errors/403.jsp").forward(request, response);
        } else {
            LOG.info("User is an administrator");
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        LOG.info("Destroying of AuthorizationFilter");
    }
}
