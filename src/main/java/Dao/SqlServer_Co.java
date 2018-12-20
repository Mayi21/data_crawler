package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
public class SqlServer_Co {
	public static Connection AD(int a){
		Connection connection = getConnection();
		if (a == 0){
			releaseConnection(connection);
		}
		return connection;
	}
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*TODO 改变连接的数据库*/
			String connectionURL = "jdbc:mysql://localhost:3306/XXXX?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
			/* TODO 改变账户名字和密码 */
			Connection connect = DriverManager.getConnection(connectionURL,"XXXX","XXXXX");
			return connect;
		}catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
		return null;
	}
	public static void releaseConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
