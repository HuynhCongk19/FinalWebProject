package com.ute.FinalProject.utils;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DbUtils {
    static Sql2o sql2o = new Sql2o("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_38e763204511b18", "bf323452cf6e6f" , "72d56662");
    //static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/huynh", "root" , "");
    public static Connection getConnection(){
        return sql2o.open();
    }
}


