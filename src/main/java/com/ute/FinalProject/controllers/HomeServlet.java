package com.ute.FinalProject.controllers;


import com.ute.FinalProject.models.CategoryModel;
import com.ute.FinalProject.utils.ServletUtils;

import com.ute.FinalProject.beans.Category;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home/*")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/index";
        }
        switch (path) {
            case "/index":
                ServletUtils.forward("/views/home/index.jsp",request,response);
                break;
            case "/product":
                ServletUtils.forward("/views/product/index.jsp",request,response);
                break;
            case "/category":
                ServletUtils.forward("/views/category/index.jsp",request,response);
                List<Category> list = CategoryModel.findAll();
                request.setAttribute("categories",list);
                break;
            case "/login":
                ServletUtils.forward("/views/login/login.jsp",request,response);
                break;
            case "/register":
                ServletUtils.forward("/views/login/register.jsp",request,response);
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
