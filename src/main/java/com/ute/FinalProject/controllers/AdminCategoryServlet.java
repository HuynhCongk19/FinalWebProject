package com.ute.FinalProject.controllers;


import com.ute.FinalProject.beans.Category;
import com.ute.FinalProject.models.CategoryModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", value = "/admin/category/*")
public class AdminCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/index";
        }
        switch (path) {
//
            case "/index":
                List<Category> list = CategoryModel.findAll();
                request.setAttribute("categories",list);
                ServletUtils.forward("/views/category/index.jsp",request,response);
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
