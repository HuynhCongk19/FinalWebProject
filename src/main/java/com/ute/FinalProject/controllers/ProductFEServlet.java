package com.ute.FinalProject.controllers;


import com.ute.FinalProject.beans.Bidding;
import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.beans.User;
import com.ute.FinalProject.models.BiddingModel;
import com.ute.FinalProject.models.ProductModel;
import com.ute.FinalProject.models.UserModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductFEServlet", value = "/product/*")
public class ProductFEServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/bycat":
                List<User> users = UserModel.findAll();
                request.setAttribute("user",users);
                int catId = Integer.parseInt(request.getParameter("id"));
                List<Product> list = ProductModel.findByCatId(catId);
                request.setAttribute("product",list);
                List<Product> HighestPrice = ProductModel.findTop5HighestPriceByCat(catId);
                request.setAttribute("HighestPriceByCat",HighestPrice);
                List<Bidding> HighestBidding = BiddingModel.findByHighestBidCountByCat(catId);
                request.setAttribute("HighestBidCountByCat",HighestBidding);
                List<Bidding> bidding = BiddingModel.findAll();
                request.setAttribute("bidding",bidding);
                ServletUtils.forward("/views/product/bycat.jsp",request,response);
                break;
            case "/byUserID":
                List<User> users2 = UserModel.findAll();
                request.setAttribute("user",users2);
                List<Bidding> bidding2 = BiddingModel.findAll();
                request.setAttribute("bidding",bidding2);
                int userid = Integer.parseInt(request.getParameter("userid"));
                List<Product> listuser = ProductModel.findByUserId(userid);
                request.setAttribute("product",listuser);
                ServletUtils.forward("/views/product/byUserID.jsp",request,response);
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
