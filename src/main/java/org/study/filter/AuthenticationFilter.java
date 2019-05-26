package org.study.filter;

import org.apache.log4j.*;
import org.study.DTO.RegisteredUserDTO;
import org.study.models.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        RegisteredUserDTO registeredUserDTO = (RegisteredUserDTO) httpRequest.getSession().getAttribute("user");
        if (registeredUserDTO == null || registeredUserDTO.getUserRole() != UserRole.ADMINISTRATOR) {
            LOG.info("User not an administrator. Access denied");
            httpRequest.getRequestDispatcher("jsp/403.jsp").forward(request, response);
        } else {
            LOG.info("User is an administrator");
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroying of AuthenticationAdministratorFilter");
    }
}
