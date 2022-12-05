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

@WebServlet(name = "DefaultServlet", value = "/")
public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ServletUtils.forward("/views/404.jsp",request,response);
        List<Product> products = ProductModel.findTop5HighestPrice();
        request.setAttribute("Top5HighestPrice",products);
        List<Bidding> bidcount = BiddingModel.findByHighestBidCount();
        request.setAttribute("HighestBidCount",bidcount);
        ServletUtils.forward("/views/home/index.jsp",request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletUtils.forward("/views/404.jsp",request,response);
        List<Product> products = ProductModel.findTop5HighestPrice();
        request.setAttribute("Top5HighestPrice",products);
        List<Bidding> bidcount = BiddingModel.findByHighestBidCount();
        request.setAttribute("HighestBidCount",bidcount);
        ServletUtils.forward("/views/home/index.jsp",request,response);


    }
}
