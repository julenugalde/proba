package eus.julenugalde.sandbox.gui;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DataSourceWorld implements DataSource {
	private PrintWriter logWriter;
	private int loginTimeout;
	private Connection connection;
	
	public DataSourceWorld() {
		logWriter = new PrintWriter(System.out);
		loginTimeout = 0;
		connection = null;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return logWriter;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return loginTimeout;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		logWriter = out;

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		loginTimeout = seconds;

	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");    //Registra el driver
			String url = "jdbc:mysql://localhost:3306/world";
			connection = DriverManager.getConnection(
				url+"?verifyServerCertificate=false&useSSL=true", 	//usa SSL sin verificación	
				username, password);									//del certificado
			return connection;

		} catch (ClassNotFoundException cnfex) {	
			System.err.println("Error SQL: " + cnfex.getLocalizedMessage());			
			return null;
		} 
	}
}
