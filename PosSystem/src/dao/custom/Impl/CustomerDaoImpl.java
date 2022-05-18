package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entitiy.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?)",customer.getCustomerId(),
                customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getEmail(),customer.getTelNo());

        return b;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("DELETE FROM customer WHERE customerId = ?", s);
        return b;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        boolean b = CrudUtil.executeUpdate("UPDATE customer SET firstName = ?, lastName= ?, address= ?, email= ?, TelNo= ? WHERE customerId = ?",
                customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getEmail(), customer.getTelNo(),customer.getCustomerId());

        return b;
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer WHERE customerId = ?", s);
        while (resultSet.next()){
            return  new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));

        }

        return null;

    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer");
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


        return customerArrayList;

    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT customerId FROM customer");

        ArrayList<String> customerId =  new ArrayList<>();
        while (resultSet.next()){
            customerId.add(resultSet.getString(1));


        }

        return customerId;


    }

    @Override
    public String getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT customerId FROM customer ORDER BY  customerId  DESC LIMIT 1");

        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            return  "C-" + tempId;
        }else{
            return "C-100";
        }



    }
}
