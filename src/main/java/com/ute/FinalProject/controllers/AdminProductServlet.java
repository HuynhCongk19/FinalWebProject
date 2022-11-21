package com.ute.FinalProject.controllers;


import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.models.ProductModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/home/product/*")
public class AdminProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/index";
        }
        switch (path) {

            case "/index":
                List<Product> list = ProductModel.findAll();
                request.setAttribute("products",list);
                ServletUtils.forward("/views/product/index.jsp",request,response);
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

                Product p = ProductModel.findById(id);
                if (p != null) {
                    request.setAttribute("product", p);
                    ServletUtils.forward("/views/product/edit.jsp", request, response);
                } else {
                    ServletUtils.redirect("/views/product/index.jsp", request, response);
                }
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/add":
                addProduct(request, response);
                break;
            case "/Delete":
                deleteProduct(request,response);
                break;
            case "/Update":
                updateProduct(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("ProName");
        String tiny = request.getParameter("TinyDes");
        String full = request.getParameter("FullDes");
        int price = Integer.parseInt(request.getParameter("Price"));
        int priceDifference = Integer.parseInt(request.getParameter("PriceDifference"));
        int catID = Integer.parseInt(request.getParameter("CatID"));
        Product p = new Product(name,tiny,full,price,priceDifference,catID);
        ProductModel.add(p);
        ServletUtils.redirect("/home/product/index", request, response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ProID"));
        int price = Integer.parseInt(request.getParameter("Price"));
        int priceDifference = Integer.parseInt(request.getParameter("PriceDifference"));
        int catID = Integer.parseInt(request.getParameter("CatID"));
        String name = request.getParameter("ProName");
        String tiny = request.getParameter("TinyDes");
        String full = request.getParameter("FullDes");
        Product p = new Product(id,name,tiny,full,price,priceDifference,catID);
        ProductModel.update(p);
        ServletUtils.redirect("/home/product/index", request, response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ProID"));
        ProductModel.delete(id);
        ServletUtils.redirect("/home/product/index", request, response);
    }
}

