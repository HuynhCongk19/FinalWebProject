package com.ute.FinalProject.models;

import com.ute.FinalProject.beans.AuctionNotify;
import com.ute.FinalProject.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class NotificationModel {
    public static void addNotify(AuctionNotify t) {
        String Sql = "INSERT INTO auctionnotify (SellerID,UserID,ProID,Status,Confirm,Day) VALUES (:SellerID,:UserID,:ProID,:Status,:Confirm,:Day)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProID", t.getProID())
                    .addParameter("UserID", t.getUserID())
                    .addParameter("SellerID",t.getSellerID())
                    .addParameter("Status",t.getStatus())
                    .addParameter("Confirm",t.getConfirm())
                    .addParameter("Day",t.getDay())
                    .executeUpdate();
        }
    }

    public static void updateNot(AuctionNotify p) {
        String sql = "update auctionnotify set SellerID = :SellerID , UserID = :UserID , ProID = :ProID , Status = :Status , Confirm = :Confirm , Day = :Day WHERE ID = :ID  ";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("ID", p.getID())
                    .addParameter("SellerID", p.getSellerID())
                    .addParameter("UserID", p.getUserID())
                    .addParameter("ProID", p.getProID())
                    .addParameter("Status", p.getStatus())
                    .addParameter("Confirm", p.getConfirm())
                    .addParameter("Day", p.getDay())
                    .executeUpdate();
        }
    }

    public static List<AuctionNotify> findByUserId (int id) {
        final String query = "select * from auctionnotify where UserID = :UserID order by Day desc";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("UserID",id)
                    .executeAndFetch(AuctionNotify.class);
        }
    }

    public static List<AuctionNotify> findBySellerId (int id) {
        final String query = "select * from auctionnotify where SellerID = :SellerID order by Day desc";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("SellerID",id)
                    .executeAndFetch(AuctionNotify.class);
        }
    }

    public static List<AuctionNotify> findByID (int id) {
        final String query = "select * from auctionnotify where ID = :ID order by Day desc";
        try (Connection con = DbUtils.getConnection()) {
            return  con.createQuery(query)
                    .addParameter("ID",id)
                    .executeAndFetch(AuctionNotify.class);
        }
    }
}