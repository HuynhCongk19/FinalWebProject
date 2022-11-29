package com.ute.FinalProject.controllers;

import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.models.ProductModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BidderProductServlet", value = "/user/product/*")
public class BidderProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/vwAllPro";
        }
        switch (path) {
            case "/vwAllPro":
                List<Product> list = ProductModel.findAll();
                request.setAttribute("product",list);
                ServletUtils.forward("/views/product/vwAllPro.jsp",request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
