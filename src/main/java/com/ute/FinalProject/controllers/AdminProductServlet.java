package com.ute.FinalProject.controllers;


import com.ute.FinalProject.beans.Category;
import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.models.CategoryModel;
import com.ute.FinalProject.models.ProductModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/admin/product/*")
public class  AdminProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/index";
        }

        switch (path) {
            case "/index":
                List<Product> list = ProductModel.findAll();
                request.setAttribute("products", list);
                ServletUtils.forward("/views/product/index.jsp", request, response);
                break;

             case "/add":
               ServletUtils.forward("/views/product/add.jsp", request, response);
               break;

             case "/edit":
               int id = 0;
               try {
                 id = Integer.parseInt(request.getParameter("id"));
               } catch (NumberFormatException e) {
               }

               Category c = CategoryModel.findById(id);
               if (c != null) {
                 request.setAttribute("product", c);
                 ServletUtils.forward("/views/product/edit.jsp", request, response);
               } else {
                 ServletUtils.redirect("/admin/product", request, response);
                 // ServletUtils.forward("/views/204.jsp", request, response);
               }
               break;

            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String path = request.getPathInfo();
         switch (path) {
           case "/add":
             addCategory(request, response);
             break;

           case "/delete":
             deleteCategory(request, response);
             break;

           case "/update":
             updateCategory(request, response);
             break;

           default:
             ServletUtils.forward("/views/404.jsp", request, response);
             break;
         }
    }

     private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("CatName");
       Category c = new Category(name);
       CategoryModel.add(c);
       ServletUtils.forward("/views/product/add.jsp", request, response);
     }

     private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("CatID"));
       String name = request.getParameter("CatName");
       Category c = new Category(id, name);
       CategoryModel.update(c);
       ServletUtils.redirect("/admin/product", request, response);
     }

     private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("CatID"));
       CategoryModel.delete(id);
       ServletUtils.redirect("/admin/product", request, response);
     }
}
