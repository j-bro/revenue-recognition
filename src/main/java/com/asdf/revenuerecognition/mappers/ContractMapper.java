package com.asdf.revenuerecognition.mappers;


import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.beans.ProductBean;
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
public class ContractMapper extends AbstractMapper<ContractBean> {

    private static final String tableName = "contract";

    private static final String findByIdStatementString = "select * from " + tableName + " where id=?";
    private static final String insertStatementString = "INSERT INTO " + tableName + " VALUES (?,?,?,?)";
    private static final String lastIdStatement = "SELECT MAX(id) FROM " + tableName;
    private static final String findAllStatementString = "SELECT * from " + tableName;
    private static final String deleteStatementString = "DELETE from " + tableName + " where id=?";

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
    public boolean delete(Long id) {
        return abstractDelete(id);
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

    @Override
    protected String deleteStatement() {
        return deleteStatementString;
    }

    @Override
    protected void doInsert(ContractBean c, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, c.getId());
        stmt.setLong(2, c.getProduct().getId());
        stmt.setLong(3, c.getRevenue().amount().longValue());
        stmt.setString(4, format.format(c.getWhenSigned().getTime()));
    }

    /**
     *
     * @param c
     */
    public void insertRevenueRecognitions(ContractBean c) {
        RevenueRecognitionsMapper revenueRecognitionsMapper = new RevenueRecognitionsMapper();
        c.getRecognitions().forEach(revenueRecognitionsMapper::insert);
    }

    @Override
    public Long insert(ContractBean model) {
        Long contractId = super.insert(model);
        model.getRecognitions().forEach(r -> r.setContractId(contractId));
        insertRevenueRecognitions(model);
        return contractId;
    }

    @Override
    protected ContractBean doLoad(Long id, ResultSet rs) throws SQLException {
        ContractBean contract = new ContractBean();
        contract.setId(rs.getLong(1));

        ProductBean p = new ProductMapper().find(rs.getLong(2));
        contract.setProduct(p);

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

        RevenueRecognitionsMapper revenueRecognitionsMapper = new RevenueRecognitionsMapper();
        revenueRecognitionsMapper.findAllByContract(contract).forEach(contract::addRevenueRecognition);

        return contract;
    }
}
