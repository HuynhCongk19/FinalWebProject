package com.ute.FinalProject.models;

import com.ute.FinalProject.beans.Category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryModel {
    public static List<Category> findAll () {
 /*       return new ArrayList<>(
                Arrays.asList(
                        new Category(1, "Sách"),
                        new Category(2, "Quần áo"),
                        new Category(3, "Điện thoại"),
                        new Category(4, "Laptop"),
                        new Category(5, "Loa")
                )
        );*/

         Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/huynh", "root" , "");
        final String query = "select * from categories";
        try (Connection con = sql2o.open()) {
            return con.createQuery(query)
                    .executeAndFetch(Category.class);
        }
    }

}