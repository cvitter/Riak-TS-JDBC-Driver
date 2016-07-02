/** 
 * Copyright (C) 2016 Craig Vitter - https://github.com/cvitter
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basho.riakts.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.timeseries.Query;
import com.basho.riak.client.core.query.timeseries.QueryResult;

public class Statement implements java.sql.Statement {
	
	private RiakClient _client;
	private ResultSet _resultSet;
	private boolean _isClosed;
	
	
	Statement(RiakClient client, int type, int concurrency, int holdability) {
		if ( type != 0 || concurrency != 0 || holdability != 0 )
            throw new UnsupportedOperationException(  );
		_client = client;
		_isClosed = false;
	}
	
	public int executeUpdate(String sql) throws SQLException {
		Query query = new Query.Builder(sql).build();
		try {
			QueryResult queryResult = _client.execute(query);
			return queryResult.getRowsCount();
		}
		catch (Exception e) {
			throw new SQLException(e);
		}
	} // Tested

	public ResultSet executeQuery(String sql) throws SQLException {
		try {
			_resultSet = Utility.query(_client, sql);
			return _resultSet;
		} 
		catch (Exception e) {
			throw new SQLException(e);
		}
	} // Tested
	
	public boolean execute(String sql) throws SQLException {
		try {
			_resultSet = Utility.query(_client, sql);
			return true;
		} 
		catch (Exception e) {
			throw new SQLException();
		}
	} // Tested

	public ResultSet getResultSet() throws SQLException {
		return _resultSet;
	} // Tested
	
	public void close() throws SQLException {
		_resultSet = null;
		_client = null;
		_isClosed = true;
	} // Tested
	
	public boolean isClosed() throws SQLException {
		return _isClosed;
	} // Tested
	
	
	
	
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	
	
	
	
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getMaxFieldSize() throws SQLException {
		return 0;
	}

	public void setMaxFieldSize(int max) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public int getMaxRows() throws SQLException {
		return 0;
	}

	public void setMaxRows(int max) throws SQLException {
		
	}

	public void setEscapeProcessing(boolean enable) throws SQLException {
		
	}

	public int getQueryTimeout() throws SQLException {
		return 0;
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		
	}

	public void cancel() throws SQLException {
		
	}

	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public void clearWarnings() throws SQLException {
		
	}

	public void setCursorName(String name) throws SQLException {
		
	}

	public void setFetchDirection(int direction) throws SQLException {
		
	}

	public int getFetchDirection() throws SQLException {
		return 0;
	}

	public void setFetchSize(int rows) throws SQLException {
		
	}

	public int getFetchSize() throws SQLException {
		return 0;
	}

	public int getResultSetConcurrency() throws SQLException {
		return 0;
	}

	public int getResultSetType() throws SQLException {
		return 0;
	}

	public void addBatch(String sql) throws SQLException {
		
	}

	public void clearBatch() throws SQLException {
		
	}

	public int[] executeBatch() throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public boolean getMoreResults(int current) throws SQLException {
		return false;
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		throw new UnsupportedOperationException(  );
	}


	public int getResultSetHoldability() throws SQLException {
		return 0;
	}

	public void setPoolable(boolean poolable) throws SQLException {
		
	}

	public boolean isPoolable() throws SQLException {
		return false;
	}

	public void closeOnCompletion() throws SQLException {
		
	}

	public boolean isCloseOnCompletion() throws SQLException {
		return false;
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException(  );
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException(  );
	}
	
	public int getUpdateCount() throws SQLException {
		return 0;
	}

	public boolean getMoreResults() throws SQLException {
		return false;
	}

}
