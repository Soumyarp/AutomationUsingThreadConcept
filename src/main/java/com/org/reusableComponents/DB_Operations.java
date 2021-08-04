package com.org.reusableComponents;
import java.sql.*;
import java.util.HashMap;
/**
 * Created by Soumya
 */
public class DB_Operations {
    public synchronized HashMap<String, String> getSqlResult(String sql) {
        HashMap<String, String> map = new HashMap<String, String>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://securtime.cmh5emqvmt1u.ap-south-1.rds.amazonaws.com:3306/securtime", "support", "SuPaSsWoRd");

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    map.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
                }
            }
            System.out.println(map);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }
}
