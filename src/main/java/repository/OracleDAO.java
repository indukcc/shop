package repository;

import java.sql.*;

public abstract class OracleDAO implements DAO {

	Connection conn = null;
   
	public Connection getConnection() {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		//String jdbcUrl = "jdbc:oracle:thin:@210.102.226.242:8080:XE";
		String dbUser = "system";
		String dbPass = "cometrue";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}
   
   	public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs){
	   	if(rs!= null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	if(stmt!= null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
	   if(pstmt!= null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	   if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   }

}