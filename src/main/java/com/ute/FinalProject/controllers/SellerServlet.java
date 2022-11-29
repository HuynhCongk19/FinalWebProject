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


@WebServlet(name = "SellerServlet", value = "/seller/*")
public class SellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/byUserID":
                List<User> users2 = UserModel.findAll();
                request.setAttribute("user",users2);
                List<Bidding> bidding2 = BiddingModel.findAll();
                request.setAttribute("bidding",bidding2);
                int userid = Integer.parseInt(request.getParameter("userid"));
                List<Product> listuser = ProductModel.findByUserId(userid);
                request.setAttribute("product",listuser);

                ServletUtils.forward("/views/product/sellerbought.jsp",request,response);
                break;
            case "/byUserid":
                List<User> users = UserModel.findAll();
                request.setAttribute("user",users);
                List<Bidding> bidding = BiddingModel.findAll();
                request.setAttribute("bidding",bidding);
                int userid1 = Integer.parseInt(request.getParameter("userid"));
                List<Product> listuser1 = ProductModel.findByUserId(userid1);
                request.setAttribute("product",listuser1);

                ServletUtils.forward("/views/product/ProductEnd.jsp",request,response);
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
