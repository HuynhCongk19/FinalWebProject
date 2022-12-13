package com.ute.FinalProject.utils;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DbUtils {
   // static Sql2o sql2o = new Sql2o("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_29b86e1e5c4bc7b", "b982cea641f865" , "8185de48");
    static Sql2o sql2o = new Sql2o("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_d0a67e5f63ddb70", "bcc520efaabc8a" , "dc876429");
    //static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/huynh", "root" , "");
    public static Connection getConnection(){
        return sql2o.open();
    }
}


