package com.kingsware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author cwm
 * @description 启动类
 * @date 2021/3/19 14:15
 */
public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("输入ip：");
        String ip = input.nextLine();

        System.out.println("输入端口：");
        String port = input.nextLine();

        System.out.println("输入用户名：");
        String username = input.nextLine();

        System.out.println("输入密码：");
        String password = input.nextLine();

        System.out.println("输入数据库：");
        String dbname = input.nextLine();

        // 创建一个数据库连接
        Connection con = null;
        // 创建预编译语句对象，一般都是用这个而不用Statement
        PreparedStatement pre = null;
        // 创建一个结果集对象
        ResultSet result = null;
        try {
            // 加载Oracle驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?characterEncoding=utf-8&serverTimezone=UTC";
            con = DriverManager.getConnection(url, username, password);
            System.out.println("[" + url + "]连接成功！");
            String sql = "select version()";
            pre = con.prepareStatement(sql);
            result = pre.executeQuery();
            while (result.next()) {
                String s = result.getString(1);
                System.out.println(s);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
                System.out.println("数据库连接已关闭！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}