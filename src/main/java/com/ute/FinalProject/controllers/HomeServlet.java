package com.ute.FinalProject.controllers;

import com.ute.FinalProject.beans.Bidding;
import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.models.BiddingModel;
import com.ute.FinalProject.models.ProductModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "HomeServlet", value = "/home/*")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/index":
                List<Product> products = ProductModel.findTop5HighestPrice();
                request.setAttribute("Top5HighestPrice",products);
                List<Bidding> bidcount = BiddingModel.findByHighestBidCount();
                request.setAttribute("HighestBidCount",bidcount);
                ServletUtils.forward("/views/home/index.jsp",request,response);
                break;
            case "/product":
                ServletUtils.forward("/views/product/index.jsp",request,response);
                break;

//            case "/category":
//               List<Category> list = CategoryModel.findAll();
//               request.setAttribute("categories",list);
//                ServletUtils.forward("/views/category/index.jsp",request,response);
//                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
