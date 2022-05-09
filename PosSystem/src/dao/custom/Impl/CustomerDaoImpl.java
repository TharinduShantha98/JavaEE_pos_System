package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entitiy.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public Customer search(String s) {
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
}
