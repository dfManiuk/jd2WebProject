package by.htp.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class ConnectionPool  {
	
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());	
	
	private final static ConnectionPool instance = new ConnectionPool();
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	private BlockingQueue<Connection> connectingQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	
	private String driveName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	private ConnectionPool()   {
		DBResoursManager dbResoursManager = DBResoursManager.getInstance();
		this.driveName = dbResoursManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResoursManager.getValue(DBParameter.DB_URL);
		this.user = dbResoursManager.getValue(DBParameter.DB_USER);
		this.password = dbResoursManager.getValue(DBParameter.DB_PASSWORD);
		
		try {
			this.poolSize = Integer.parseInt(dbResoursManager.getValue(DBParameter.DB_POLL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 5;
		}
		try {
			initPoolData();
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
	public void initPoolData() throws ConnectionPoolException {
		try {
			Class.forName(driveName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectingQueue = new ArrayBlockingQueue<Connection>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url,user,password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectingQueue.add(pooledConnection);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new ConnectionPoolException("SQLExeption in ConnectionPool", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		}
	}
	public void dispose() throws ConnectionPoolException {
		try {
			clearConnectionQueue();
		} catch (ConnectionPoolException e) {
			throw new ConnectionPoolException("Error connecting to the database sourse -> Queue clear error. ", e);

		}
	}
	private void clearConnectionQueue() throws ConnectionPoolException{
		try {
			closeConnectionQueue(givenAwayConQueue);
			closeConnectionQueue(connectingQueue);
	} catch (SQLException e) {
		throw new ConnectionPoolException("Error connecting to the database sourse -> Queue error. ", e);
	}
}
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectingQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the database sourse. ", e);
		}
		return connection;
	}
	public void relise(Connection con) throws ConnectionPoolException {
		try {
			if (con!=null) {
				con.setAutoCommit(true);
				connectingQueue.add(con);
				givenAwayConQueue.remove(con);
			}else {
				//log
			}
			
		} catch (SQLException e) {
			throw new ConnectionPoolException("Error connecting to the database sourse. ", e);
		}
	}
	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			con.close(); //TODO что то не так!
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO "ResultSet isn't closed")
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO "Statement isn't closed")
		}
	}
	public void closeConnection(Connection con, Statement st) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO  "Connection isn't return to the pool")
		} 
		try {
			st.close();
		} catch (Exception e) {
			// TODO  "Statement isn't closed")
		}
	}	
	public void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException{
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}
	
private class PooledConnection implements Connection{
	private Connection connection;
		
	public PooledConnection(Connection c) throws SQLException {
			this.connection = c;
			this.connection.setAutoCommit(true);
		}
		
	public void reallyClose() throws SQLException {
			connection.close();
		}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

	@Override
	public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

	@Override
	public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

	@Override
	public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

	@Override
	public void commit() throws SQLException {
			connection.commit();
		}

	@Override
	public void rollback() throws SQLException {
			connection.rollback();
		}

	@Override
	public void close() throws SQLException {
		if (connection.isClosed()) {
				throw new SQLException("Attempting to close closed connection.");
			}
		if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
		if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the given away connection pool");
			}
		if (!connectingQueue.offer(this)) {
				throw new SQLException("Error allocation connection in the pool.");
			}
		}
		
	
	@Override
	public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

	@Override
	public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

	@Override
	public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

	@Override
	public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);	
		}

	@Override
	public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

	@Override
	public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

	@Override
	public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		connection.setTypeMap(map);
		}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		connection.setHoldability(holdability);
		}

	@Override
	public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

	@Override
	public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback();
		}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);
		}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

	@Override
	public Clob createClob() throws SQLException {
			return connection.createClob();
		}

	@Override
	public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

	@Override
	public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

	@Override
	public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

	@Override
	public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);
		}

	@Override
	public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

	@Override
	public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

	@Override
	public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

	@Override
	public String getSchema() throws SQLException {
			return connection.getSchema();
		}

	@Override
	public void abort(Executor executor) throws SQLException {
			connection.abort(executor);
			
		}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
			
		}

	@Override
	public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}
		
	}
}
