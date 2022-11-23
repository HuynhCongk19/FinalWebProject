package com.ute.FinalProject.controllers;


import com.ute.FinalProject.models.CategoryModel;
import com.ute.FinalProject.utils.ServletUtils;

import com.ute.FinalProject.beans.Category;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/Home/*")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":
                ServletUtils.forward("/views/vwHome/Index.jsp",request,response);
                break;
            case "/Login":
                ServletUtils.forward("/views/vwAccount/Login.jsp",request,response);
                break;
            case "/Register":
                ServletUtils.forward("/views/vwAccount/Register.jsp",request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
