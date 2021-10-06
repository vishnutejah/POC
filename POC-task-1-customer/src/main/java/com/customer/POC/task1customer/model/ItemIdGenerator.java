package com.customer.POC.task1customer.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ItemIdGenerator implements IdentifierGenerator {
    private final String DEFAULT_SEQUENCE_NAME = "item_id__sequence";

    /*
    * This method will generate custom id based on String followed by id
    * e.g. ITM-1
    * */
    @Override
   	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException{
        Serializable result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String prefix = "ITM-";
        try {
            connection = session.connection();
            statement = connection.createStatement();
            try {
                /*
                * MySql does not support sequence, instead there is AUTO INCREMENT
                */
                statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
                resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
            } catch (Exception e) {

                System.out.println("In catch, cause : Table is not available.");
                // if sequence is not found then creating the sequence
                statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
                statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
                //==> LAST_INSERT_ID(next_val+1)  -> this is inbuilt function of MySql so by using this we can achieve our custom sequence like auto increment
                statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
                resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
                //e.printStackTrace();
            }
            if (resultSet.next()) {

                int nextValue = resultSet.getInt(1);
                String suffix = String.format("%01d", nextValue);
                result = prefix.concat(suffix);
                System.out.println("Custom generated sequence is : " + result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
}
