package com.ute.FinalProject.models;

import com.ute.FinalProject.beans.Bidding;
import com.ute.FinalProject.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class BiddingModel {
    public static void addBid(Bidding b) {
        String Sql = " INSERT INTO auctionhistory (ProID, Price, UserID , SellerID, Day) VALUES (:ProID,:Price,:UserID,:SellerID,:Day)";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(Sql)
                    .addParameter("ProID", b.getProID())
                    .addParameter("UserID",b.getUserID())
                    .addParameter("Price", b.getPrice())
                    .addParameter("SellerID", b.getSellerID())
                    .addParameter("Day", b.getDay())
                    .executeUpdate();
        }
    }

    public static List<Bidding> findAll () {
        final String query = "select * from auctionhistory ";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Bidding.class);
        }
    }
    public static List<Bidding> findByHighestBidCount () {
        final String query = "select * FROM auctionhistory group by ProID order by count(ProID) desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Bidding.class);
        }
    }
    public static List<Bidding> findtop3byProID (int proID) {
        final String query = "select * from auctionhistory where ProID = :ProID order by Price desc limit 3";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("ProID",proID)
                    .executeAndFetch(Bidding.class);
        }
    }
    public static List<Bidding> findByHighestBidCountByCat (int id ) {
        final String query = "select a.* FROM auctionhistory as a,products as b where a.ProID = b.ProID and b.CatID = :CatID group by a.ProID order by count(a.ProID) desc limit 5\n";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("CatID",id)
                    .executeAndFetch(Bidding.class);
        }
    }
    public static List<Bidding> findByHighestBidCountByUser (int id ) {
        final String query = "select a.* FROM auctionhistory as a,products as b where a.ProID = b.ProID and b.UserID = :UserID group by a.ProID order by count(a.ProID) desc limit 5";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("UserID",id)
                    .executeAndFetch(Bidding.class);
        }
    }

    public static List<Bidding> findAllBiddingProductByUser (int id ) {
        final String query = "select distinct ProID  from auctionhistory where UserID = :UserID";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("UserID",id)
                    .executeAndFetch(Bidding.class);
        }
    }
    public static List<Bidding> findByProIDandUserID (int userid, int pro ) {
        final String query = "select * from auctionhistory where ProID = :ProID and UserID = :UserID and Price = (select max(Price) from auctionhistory where UserID = :UserID and ProID = :ProID) limit 1";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("ProID",pro)
                    .addParameter("UserID",userid)
                    .executeAndFetch(Bidding.class);
        }
    }
}