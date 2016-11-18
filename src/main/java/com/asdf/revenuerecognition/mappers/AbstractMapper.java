package com.asdf.revenuerecognition.mappers;

import com.asdf.revenuerecognition.beans.AbstractBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * This class is the super class for all data mappers. 
 * This class contains common behavior of data mappers. 
 * THis class follows the pattern Layer Supertype from [PoEAA, p475]
 * 
 * @author Yuhong Yan
 * @author Jeremy Brown
 * Adapted from the AbstractMapper class given in the class samples.
 *
 */

public abstract class AbstractMapper<T extends AbstractBean> {
	
	/**
	 * loadedMap implements the pattern IdentityMap from [PoEAA, p195]
	 * If to find a domain object, first check if this object is in memory, ie.
	 * in the loadedMap, before pulling the data from database 
	 */
	protected Map<Serializable, T> loadedMap = new HashMap<>();
	
	private Connection db;

	/**
	 * the return string is domain specific
	 */
	protected abstract String findStatement();

	protected abstract String connectionString();

    protected abstract String lastIDStatement();

	protected abstract String insertStatement();

	protected abstract String findAllStatement();

    protected abstract String createTableStatement();

	public abstract T find(Long id);

    public abstract List<T> findAll();
	
	/**
	 * need to give this task to domain specific mapper
	 * @param p
	 * @param stmt
	 * @throws SQLException
	 */
	protected abstract void doInsert(T p, PreparedStatement stmt) throws SQLException ;
	
	/**
	 * need to give this task to domain specific mapper
	 * @param id
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract T doLoad(Long id, ResultSet rs) throws SQLException;

    /**
     *
     * @param id
     * @return
     */
	protected T abstractFind(Long id){
		if(db == null) {
            setConnection();
        }

		T result = loadedMap.get(id);
		if (result != null) {
            return result;
        }

		PreparedStatement stmt = null;
		try {
			stmt = db.prepareStatement(findStatement());
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result = load(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;		
	}

    /**
     *
     * @return
     */
	protected List<T> abstractFindAll() {
		if (db == null) {
            setConnection();
        }

		List<T> result = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = db.prepareStatement(findAllStatement());
			ResultSet rs = stmt.executeQuery();
			return loadAll(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;
	}

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
	private List<T> loadAll(ResultSet rs) throws SQLException{
		List<T> result = new ArrayList<>();
		while(rs.next()) {
			result.add(load(rs));
		}
		
		return result;
	}

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
	private T load(ResultSet rs) throws SQLException {
		Long id = rs.getLong(1);
		if(loadedMap.containsKey(id)) {
            return loadedMap.get(id);
        }

		T result = doLoad(id, rs);
		loadedMap.put(id, result);
		return result;
	}

    /**
     *
     * @return
     */
	protected boolean createTable() {
        if (db == null) {
            setConnection();
        }

        PreparedStatement stmt = null;
        int statementResult = 1;
        try {
            stmt = db.prepareStatement(createTableStatement());
            statementResult = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return statementResult == 0;
    }

    /**
     *
     * @param model
     * @return
     */
	public Long insert(T model) {
		if (db == null) {
            setConnection();
        }

		PreparedStatement stmt = null;
		try {
		    stmt = db.prepareStatement(insertStatement());
			model.setId(findNextDatabaseId());
			stmt.setInt(1, model.getId().intValue());
			doInsert(model, stmt);
			stmt.executeUpdate();
			loadedMap.put(model.getClass(), model);
		} catch(SQLException e) {
            e.printStackTrace();
		}

		return model.getId();
	}

    /**
     *
     * @return
     * @throws SQLException
     */
	private Long findNextDatabaseId() throws SQLException {	
		PreparedStatement stmt = db.prepareStatement(lastIDStatement());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return (long) (rs.getInt(1)+1);
	}

    /**
     *
     */
	private void setConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        // Setup the connection with the DB
	        db = DriverManager.getConnection(connectionString());
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
	}

}
