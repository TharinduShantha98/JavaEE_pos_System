package dao.custom.Impl;

import controller.CustomerServlet;
import dao.CrudUtil;
import dao.custom.CustomerDao;
import entitiy.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();

        boolean b = CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?)",connection ,customer.getCustomerId(),
                customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getEmail(),customer.getTelNo());

        connection.close();

        return b;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        boolean b = CrudUtil.executeUpdate("DELETE FROM customer WHERE customerId = ?",connection , s);
        connection.close();
        return b;


    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();

        boolean b = CrudUtil.executeUpdate("UPDATE customer SET firstName = ?, lastName= ?, address= ?, email= ?, TelNo= ? WHERE customerId = ?", connection,
                customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getEmail(), customer.getTelNo(),customer.getCustomerId());
        connection.close();

        return b;
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer WHERE customerId = ?", connection, s);
        if (resultSet.next()){


            Customer customer =   new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));



            connection.close();
            return customer;

        }
        connection.close();

        return null;

    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer", connection);
        ArrayList<Customer> customerArrayList =  new ArrayList<>();

        while (resultSet.next()){
            customerArrayList.add(new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6))
            );
        }
        connection.close();


        return customerArrayList;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT customerId FROM customer", connection);

        ArrayList<String> customerId =  new ArrayList<>();
        while (resultSet.next()){
            customerId.add(resultSet.getString(1));

        }
        connection.close();

        return customerId;


    }

    @Override
    public String getCustomerId() throws SQLException, ClassNotFoundException {
        Connection connection = CustomerServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT customerId FROM customer ORDER BY  customerId  DESC LIMIT 1", connection);

        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            connection.close();
            return  "C-" + tempId;
        }else{
            connection.close();
            return "C-100";
        }



    }
}
