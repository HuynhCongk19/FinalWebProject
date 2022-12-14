package com.ute.FinalProject.models;

import com.ute.FinalProject.beans.Product;
import com.ute.FinalProject.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;


public class ProductModel {
    public static List<Product> findAll () {
        final String query = "select * from products";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByCatId (int catId) {
        final String query = "select * from products where CatID = :CatID";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("CatID",catId)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByUserId (int id) {
        final String query = "select * from products where UserID = :UserID";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("UserID",id)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByTextSearch (String txtSr) {
        final String query = "SELECT * FROM products WHERE MATCH(ProName) AGAINST(:txtSr)";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("txtSr",txtSr)
                    .executeAndFetch(Product.class);
        }
    }
    public static Product findById (int id) {
        final String query = "select * from products where ProID = :ProID";
        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("ProID",id)
                    .executeAndFetch(Product.class);
            if (list.size() == 0) {
                return null;
            }
            return list.get(0);
        }
    }

    public static List<Product> findTop5HighestPrice () {
        final String query = "SELECT * FROM products order by Price desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)

                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findTop5HighestPriceByCat (int catId) {
        final String query = "SELECT * FROM products where CatID = :CatID order by Price desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("CatID",catId)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findTop5HighestPriceByUser (int UserID) {
        final String query = "SELECT * FROM products where UserID = :UserID order by Price desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("UserID",UserID)
                    .executeAndFetch(Product.class);
        }
    }

    public static void add(Product p) {
        String Sql = " INSERT INTO products (ProID , ProName, TinyDes, FullDes, Price, PriceDifference, CatID,UserID,StartDay,EndDay,CurrentPrice,Status) VALUES (:ProID,:ProName,:TinyDes,:FullDes,:Price,:PriceDifference,:CatID,:UserID,:StartDay,:EndDay,:CurrentPrice,:Status)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProID", p.getProID())
                    .addParameter("ProName", p.getProName())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .addParameter("Price", p.getPrice())
                    .addParameter("PriceDifference", p.getPriceDifference())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("UserID", p.getUserID())
                    .addParameter("StartDay", p.getStartDay())
                    .addParameter("EndDay", p.getEndDay())
                    .addParameter("CurrentPrice", p.getCurrentPrice())
                    .addParameter("Status", p.getStatus())
                    .executeUpdate();
        }
    }

    public static void update(Product p) {
        String sql = "update products set  ProName = :ProName, TinyDes = :TinyDes, FullDes = :FullDes, Price = :Price, PriceDifference = :PriceDifference, CatID = :CatID ,StartDay = :StartDay,EndDay = :EndDay,CurrentPrice = :CurrentPrice,Status = :Status WHERE ProID = :ProID  ";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("ProName", p.getProName())
                    .addParameter("TinyDes", p.getTinyDes())
                    .addParameter("FullDes", p.getFullDes())
                    .addParameter("Price", p.getPrice())
                    .addParameter("PriceDifference", p.getPriceDifference())
                    .addParameter("CatID", p.getCatID())
                    .addParameter("ProID",p.getProID())
                    .addParameter("StartDay",p.getStartDay())
                    .addParameter("EndDay",p.getEndDay())
                    .addParameter("CurrentPrice",p.getCurrentPrice())
                    .addParameter("Status",p.getStatus())
                    .executeUpdate();
        }
    }

    public static void updateStatus(Product p) {
        String sql = "update products set  Status = :Status WHERE ProID = :ProID  ";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("ProID",p.getProID())
                    .addParameter("Status",p.getStatus())
                    .executeUpdate();
        }
    }

    public static void delete(int id) {
        String sql = "delete from products where ProID = :ProID  ";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("ProID",id)
                    .executeUpdate();
        }
    }

}





