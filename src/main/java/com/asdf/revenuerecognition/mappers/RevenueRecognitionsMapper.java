package com.asdf.revenuerecognition.mappers;

import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.beans.RevenueRecognitionBean;
import com.asdf.revenuerecognition.util.Money;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class RevenueRecognitionsMapper extends AbstractMapper<RevenueRecognitionBean> {

    private static final String tableName = "revenuerecognition";

    private static final String findByIdStatementString = "select * from " + tableName + " where id=?";
    private static final String insertStatementString = "INSERT INTO " + tableName + " VALUES (?,?,?,?)";
    private static final String lastIdStatement = "SELECT MAX(id) FROM " + tableName;
    private static final String findAllStatementString = "SELECT * from " + tableName;
    private static final String findAllByContractStatementString = "SELECT * from " + tableName + " WHERE contract=%s";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public RevenueRecognitionBean find(Long id) {
        return abstractFind(id);
    }

    @Override
    public List<RevenueRecognitionBean> findAll() {
        return abstractFindAll();
    }

    @Override
    protected String findStatement() {
        return findByIdStatementString;
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

    /**
     *
     * @return
     */
    public List<RevenueRecognitionBean> findAllByContract(ContractBean contract) {
        String filledQuery = String.format(findAllByContractStatementString, contract.getId());
        return abstractFindAllByQuery(filledQuery);
    }

    @Override
    protected void doInsert(RevenueRecognitionBean r, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, r.getId());
        stmt.setLong(2, r.getContractId());
        stmt.setBigDecimal(3, r.getAmount().amount());
        stmt.setString(4, format.format(r.getDate().getTime()));
    }

    @Override
    protected RevenueRecognitionBean doLoad(Long id, ResultSet rs) throws SQLException {
        RevenueRecognitionBean revenueRecognition = new RevenueRecognitionBean();
        revenueRecognition.setId(rs.getLong(1));

        revenueRecognition.setContractId(rs.getLong(2));

        revenueRecognition.setAmount(Money.dollars(rs.getDouble(3)));

        Date date = null;
        try {
            date = format.parse(rs.getString(4)); // mysql datetime format
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            revenueRecognition.setDate(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return revenueRecognition;
    }
}
