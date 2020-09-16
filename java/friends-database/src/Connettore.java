
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	public class Connettore {
		
		private static Connection cn;
		
		public static ResultSet getData(String database,String sql) throws SQLException{
			
			Statement st;
			ResultSet rs;
			
			getConnection(database);

			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			return rs;
		}
		
		public static void updateData(String database,String sql) throws SQLException{
			
			Statement st;
			
			getConnection(database);

			st = cn.createStatement();
			st.executeUpdate(sql);
			
		} 
		
		public static Connection getConnection(String database) throws SQLException{
			if(cn == null){
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("ClassNotFoundException: ");
					System.err.println(e.getMessage());
				}
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"?user=root&password=");
				return cn;
			}
			return cn;
		}
}
