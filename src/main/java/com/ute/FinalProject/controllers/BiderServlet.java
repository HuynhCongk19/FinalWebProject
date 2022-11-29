package com.ute.FinalProject.controllers;

import com.ute.FinalProject.beans.*;
import com.ute.FinalProject.models.*;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BiderServlet", value = "/bider/*")
public class BiderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/byUserID":
                List<User> users2 = UserModel.findAll();
                request.setAttribute("user",users2);
                List<Bidding> bidding2 = BiddingModel.findAll();
                request.setAttribute("bidding",bidding2);
                List<Product> products = ProductModel.findAll();
                request.setAttribute("product",products);
                int userid = Integer.parseInt(request.getParameter("userid"));
                List<Product> PriceByUser = ProductModel.findTop5HighestPriceByUser(userid);
                ServletUtils.forward("/views/product/bidinglist.jsp",request,response);
                break;
            case "/byuserID":
                List<User> users = UserModel.findAll();
                request.setAttribute("user",users);
                List<Bidding> bidding = BiddingModel.findAll();
                request.setAttribute("bidding",bidding);
                List<Product> product = ProductModel.findAll();
                request.setAttribute("product",product);
                ServletUtils.forward("/views/product/wonlist.jsp",request,response);
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
