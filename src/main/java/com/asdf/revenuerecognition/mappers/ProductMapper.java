package com.asdf.revenuerecognition.mappers;


import com.asdf.revenuerecognition.beans.ProductBean;
import com.asdf.revenuerecognition.strategies.CompleteRecognitionStategy;
import com.asdf.revenuerecognition.strategies.RecoginitionStrategy;
import com.asdf.revenuerecognition.strategies.ThreeWayRecognitionStategy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ProductMapper extends AbstractMapper<ProductBean> {

    private static final String tableName = "product";

    private static final String findByIdStatementString = "select * FROM " + tableName + " WHERE id=?";
    private static final String insertStatementString = "INSERT INTO " +  tableName + " VALUES (?,?,?,?,?)";
    private static final String lastIdStatement = "SELECT MAX(id) FROM " + tableName;
    private static final String findAllStatementString = "SELECT * FROM " + tableName;

    private static final String connectionString = "jdbc:mysql://mariadb:3306/revenuerecognition?user=root&password=hello";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ProductBean find(Long id) {
        return abstractFind(id);
    }

    @Override
    public List<ProductBean> findAll() {
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
    protected void doInsert(ProductBean p, PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, p.getId());
        stmt.setString(2, p.getName());
        if (p.getRecognitionStrategy() != null) {
            stmt.setString(3, p.getRecognitionStrategy().getName());
            if (p.getRecognitionStrategy() instanceof ThreeWayRecognitionStategy) {
                stmt.setLong(4, ((ThreeWayRecognitionStategy) p.getRecognitionStrategy()).getFirstRecognitionOffset());
                stmt.setLong(5, ((ThreeWayRecognitionStategy) p.getRecognitionStrategy()).getSecondRecognitionOffset());
            }
            else {
                stmt.setString(4, null);
                stmt.setString(5, null);
            }
        }
        else {
            stmt.setString(3, null);
            stmt.setString(4, null);
            stmt.setString(5, null);
        }
    }

    @Override
    protected ProductBean doLoad(Long id, ResultSet rs) throws SQLException {
        ProductBean product = new ProductBean();
        product.setId(rs.getLong(1));
        product.setName(rs.getString(2));

        String strategyName = rs.getString(3);
        if (strategyName != null) {
            int offset1 = rs.getInt(4);
            int offset2 = rs.getInt(5);
            if (offset1 != 0 && offset2 != 0) {
                product.setRecognitionStrategy(new ThreeWayRecognitionStategy(offset1, offset2));
            } else {
                product.setRecognitionStrategy(new CompleteRecognitionStategy());
            }
        }

        return product;
    }

}
