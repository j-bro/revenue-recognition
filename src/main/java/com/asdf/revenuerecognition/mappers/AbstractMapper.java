package com.asdf.revenuerecognition.mappers;

import com.asdf.revenuerecognition.models.AbstractModel;

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
 * This class is the super class for all data mappers. 
 * This class contains common behavior of data mappers. 
 * THis class follows the pattern Layer Supertype from [PoEAA, p475]
 * 
 * @author Yuhong Yan 
 *
 */

public abstract class AbstractMapper {
	
	/**
	 * loadedMap implements the pattern IdentityMap from [PoEAA, p195]
	 * If to find a domain object, first check if this object is in memory, ie.
	 * in the loadedMap, before pulling the data from database 
	 */
	protected Map<Serializable, AbstractModel> loadedMap = new HashMap<Serializable, AbstractModel>();
	
	private Connection db;

    private String createProductsTableString =
            "CREATE TABLE products (ID int primary key, name varchar, type varchar);";
    private String createContractsTableString =
            "CREATE TABLE contracts (ID int primary key, product int, revenue decimal, dateSigned date)";
    private String createRevenueRecognitionsTableString = "CREATE TABLE revenueRecognitions (contract int, " +
            "amount decimal, recognizedOn date, PRIMARY KEY (contract, recognizedOn))";

	/**
	 * the return string is domain specific
	 */
	protected abstract String findStatement();

	protected abstract String connectionString();

    protected abstract String lastIDStatement();

	protected abstract String insertStatement();

	protected abstract String findAllStatement();
	
	/**
	 * need to give this task to domain specific mapper
	 * @param p
	 * @param stmt
	 * @throws SQLException
	 */
	protected abstract void doInsert(AbstractModel p, PreparedStatement stmt) throws SQLException ;
	
	/**
	 * need to give this task to domain specific mapper
	 * @param id
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract AbstractModel doLoad(Long id, ResultSet rs) throws SQLException;

	
	protected AbstractModel abstractFind(Long id){
		if(db==null) setConnection();
		AbstractModel result = (AbstractModel) loadedMap.get(id);
		if(result != null) return result;		
		PreparedStatement stmt = null;
		try {
			stmt = db.prepareStatement(findStatement());
			stmt.setLong(1, id.longValue());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result = load(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;		
	}
	
	protected List<AbstractModel> abstractFindAll(String name){
		if(db==null) setConnection();
		List<AbstractModel> result = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = db.prepareStatement(findAllStatement());
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			return loadAll(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	private List<AbstractModel> loadAll(ResultSet rs) throws SQLException{
		List<AbstractModel> result = new ArrayList<AbstractModel>();
		while(rs.next()){
			result.add(load(rs));
		}
		
		return result;
	}
	
	private AbstractModel load(ResultSet rs) throws SQLException {
		Long id = new Long(rs.getLong(1));
		if(loadedMap.containsKey(id))
			return (AbstractModel) loadedMap.get(id);
		AbstractModel result = doLoad(id, rs);
		loadedMap.put(id, result);
		return result;
	}
	
	protected Long insert(AbstractModel p){
		if(db == null) setConnection();
		PreparedStatement stmt = null;
		try{
		    stmt = db.prepareStatement(insertStatement());
			p.setID(findNextDatabaseId());
			stmt.setInt(1, p.getID().intValue());
			doInsert(p, stmt);
			//System.out.println("in Person.insert"+ stmt.toString());
			stmt.executeUpdate();
			loadedMap.put(p.getClass(), p);
		}catch(SQLException e){
			System.out.println(e);
		}
		return p.getID();
	}

	private Long findNextDatabaseId() throws SQLException {	
		PreparedStatement stmt = db.prepareStatement(lastIDStatement());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		//System.out.println("in Person.findNextDatabaseId"+ rs.getInt(1));
		return (long) (rs.getInt(1)+1);
	}

	private void setConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
	      // Setup the connection with the DB
	    db = DriverManager
	          .getConnection(connectionString());
		} catch (ClassNotFoundException | SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
