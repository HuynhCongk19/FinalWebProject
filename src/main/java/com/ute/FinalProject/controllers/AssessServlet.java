package com.ute.FinalProject.controllers;

import com.ute.FinalProject.beans.Score;
import com.ute.FinalProject.beans.User;
import com.ute.FinalProject.models.ScoreModel;
import com.ute.FinalProject.models.UserModel;
import com.ute.FinalProject.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AssessServlet", value = "/assess/*")
public class AssessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/index":
                int UserId = 0;
                try {
                    UserId = Integer.parseInt(request.getParameter("UserId"));
                } catch (NumberFormatException e) {
                }
                List<User> users = UserModel.findAll();
                request.setAttribute("listuser",users);
                List<Score> scores = ScoreModel.findAll();
                request.setAttribute("score",scores);
                User m = UserModel.findById(UserId);
                if ( m != null) {
                    request.setAttribute("user", m);
                    ServletUtils.forward("/views/assess/index.jsp", request, response);
                } else {
                    ServletUtils.redirect("/views/product/vwAll.jsp", request, response);
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
