package batch_create_ETL_JOB;

import java.sql.*;

public class dbConn {

	public static Connection get_conn(String SourcServer, String port, String instanceName, String SourceDb,
			String SourceUser, String SourcePwd) {
		String dbURL = new String();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		if (port != null) {
			dbURL = "jdbc:sqlserver://" + SourcServer + ":" + port + ";DatabaseName=" + SourceDb;
		} else if (instanceName != null) {
			dbURL = "jdbc:sqlserver://" + SourcServer + ";instanceName=" + instanceName + ";DatabaseName=" + SourceDb;
		}
		String userName = SourceUser;
		String userPwd = SourcePwd;

		try {
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("连接数据库成功");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("连接失败");
		}
		return null;

	}

}
