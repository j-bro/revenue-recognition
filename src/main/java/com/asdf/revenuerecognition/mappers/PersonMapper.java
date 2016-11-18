package com.asdf.revenuerecognition.mappers;

import com.asdf.revenuerecognition.models.AbstractModel;
import com.asdf.revenuerecognition.models.PersonBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonMapper extends AbstractMapper {

	private final static String findStatementString = "select * from people where id=?";
	private final static String insertStatementString = "INSERT INTO people VALUES (?,?,?,?)";
	private final static String lastIDStatement = "SELECT MAX(id) FROM people";
	private String connectionString = "jdbc:mysql://localhost:3306/test?"
            + "user=root&password=taoci960";
	private String findLastNameStatement = "SELECT * from people where UPPER(lastname) "+
             "like UPPER(?) ORDER BY lastname";

	public PersonBean find(long id){
		return find(new Long(id));
		
	}
	
	public PersonBean find(Long id){
		return (PersonBean) abstractFind(id);
	}
	
	public List<AbstractModel> findbyLastName(String name){
		return abstractFindAll(name);
	}
	
	
	public Long insertPerson(PersonBean p){
		return insert(p);
	}
	
	@Override
	protected AbstractModel doLoad(Long id, ResultSet rs) throws SQLException {
		String lastName = rs.getString(2);
		String firstName = rs.getString(3);
		int numDependents = rs.getInt(4);
		PersonBean result = new PersonBean(id, lastName, firstName, numDependents);
		//Registry.addPerson(result);
		return result;
	}
	
	@Override
	protected void doInsert(AbstractModel p, PreparedStatement stmt) throws SQLException{
		stmt.setString(2, ((PersonBean) p).getLastName());
		stmt.setString(3, ((PersonBean) p).getFirstName());
		stmt.setInt(4, ((PersonBean) p).getNumberOfDependents());
		//System.out.println("in Person.insert"+ stmt.toString());
	}
	
	@Override
	protected String findStatement() {
		return findStatementString;
	}

	@Override
	protected String insertStatement() {
		return insertStatementString;
	}

	@Override
	protected String lastIDStatement() {
		return lastIDStatement;
	}

	@Override
	protected String connectionString() {
		return connectionString;
	}

	@Override
	protected String findAllStatement() {
		return findLastNameStatement;
	}

}
