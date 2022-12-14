package com.ute.FinalProject.filters;

import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession Session = request.getSession();
        boolean Auth = (boolean) Session.getAttribute("Auth");
        if(!Auth) {
            Session.setAttribute("retUrl",request.getRequestURI());
            ServletUtils.redirect("/account/login",request,(HttpServletResponse) res);
            return;
        }

        chain.doFilter(req, res);
    }
}

