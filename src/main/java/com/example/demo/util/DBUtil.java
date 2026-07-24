package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL =
			"jdbc:postgresql://localhost:5432/shopping";
	private static final String USER = "postgres";

	private static final String PASSWORD = "psql";
	
	private DBUtil() {}

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
}

//テストデータ
//管理者
//ID: admin01
//パスワード: adminpass
//admin: true
//
//一般ユーザー
//ID: user01
//パスワード: userpass
//admin: false
