package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description: Provide Basic Connection with a database
 * @author: Kid_A
 * @date: 2024.6.28
 */
public class DBConnector {
    private static volatile DBConnector instance = null;
    private Connection connection;
    private Statement statement;

    /**
     * 加载mysql驱动
     */
    private DBConnector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("not found mysql driver");
            e.printStackTrace();
        }
    }

    /*private static class instanceHolder{
        private static final DBConnector instance = new DBConnector();
    }*/

    /**
     * 线程安全的单例模式
     * @return 数据库连接实例
     */
    public static DBConnector getInstance(){
        if(instance == null){
            synchronized (DBConnector.class){
                if (instance == null)
                    instance = new DBConnector();
            }
        }
        return instance;
        //return instanceHolder.instance;
    }

    /**
     * 建立数据库连接
     * @param hostName 主机名
     * @param port 端口号
     * @param dbName 数据库名
     * @param user 用户名
     * @param passwd 密码
     */
    public void connectDB(String hostName, int port, String dbName, String user, String passwd){
        String url = "jdbc:mysql://"+hostName+":"+port+"/"+dbName+"?autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url,user,passwd);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            System.out.println("cannot connect to database");
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     */
    public void closeConnect(){
        try{
            connection.close();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
