package m.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {
	Properties prop;
	private static ConnectionFactory instance;
	
	public ConnectionFactory() {
		prop=new Properties();
		
		try {
			prop.load(new FileReader("resources/driver.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static ConnectionFactory getConnection() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(instance==null)
			instance=new ConnectionFactory();
		return instance;
	}
	
	public Connection createConnection() throws SQLException {
		Connection conn=null;
		String url=prop.getProperty("url");
		String user=prop.getProperty("user");
		String password=prop.getProperty("password");
		conn=DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
	public static void close(Connection conn) throws SQLException {
		if(conn!=null&&!conn.isClosed())
			conn.close();
	}
	
	public static void close(Statement stmt) throws SQLException {
		if(stmt!=null&&!stmt.isClosed())
			stmt.close();
	}
	
	public static void close(ResultSet rset) throws SQLException {
		if(rset!=null&&!rset.isClosed())
			rset.close();
	}

	
	public void commit(Connection conn) throws SQLException {
		if(conn!=null&&!conn.isClosed())
			conn.commit();
	}
	
	public void rollback(Connection conn) throws SQLException {
		if(conn!=null&&!conn.isClosed())
			conn.rollback();
	}
}
