package com.asdf.revenuerecognition.mappers;


import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.util.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ContractMapper extends AbstractMapper<ContractBean> {

    private static final String createContractsTableStatementString =
            "CREATE TABLE contract (ID int primary key, product int, revenue decimal, dateSigned date)";
    private static final String findByIdStatementString = "select * from contract where id=?";
    private static final String insertStatementString = "INSERT INTO contract VALUES (?,?,?,?)";
    private static final String lastIdStatement = "SELECT MAX(id) FROM contract";
    private static final String findAllStatementString = "SELECT * from contract";

    private static final String connectionString = "jdbc:mysql://mariadb:3306/revenuerecognition?user=root&password=hello";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ContractBean find(Long id) {
        return abstractFind(id);
    }

    @Override
    public List<ContractBean> findAll() {
        return abstractFindAll();
    }

    @Override
    protected String findStatement() {
        return findByIdStatementString;
    }

    @Override
    protected String connectionString() {
        return connectionString;
    }

    @Override
    protected String lastIDStatement() {
        return lastIdStatement;
    }

    @Override
    protected String insertStatement() {
        return insertStatementString;
    }

    @Override
    protected String findAllStatement() {
        return findAllStatementString;
    }

    @Override
    protected String createTableStatement() {
        return createContractsTableStatementString;
    }

    @Override
    protected void doInsert(ContractBean c, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, c.getId());
        stmt.setLong(2, c.getProduct().getId());
        stmt.setLong(3, c.getRevenue().amount().longValue());
        stmt.setString(4, format.format(c.getWhenSigned().getTime()));
    }

    @Override
    protected ContractBean doLoad(Long id, ResultSet rs) throws SQLException {
        ContractBean contract = new ContractBean();
        contract.setId(rs.getLong(1));
        // TODO set product
//        contract.setProduct();
        contract.setRevenue(Money.dollars(rs.getLong(3)));
        Date date = null;
        try {
            date = format.parse(rs.getString(4)); // mysql datetime format
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        contract.setWhenSigned(calendar);
        return contract;
    }
}
