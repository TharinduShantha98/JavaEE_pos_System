package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.OrderDao;
import entitiy.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("INSERT INTO `order` VALUES (?,?,?,?,?)",
                order.getOrderId(), order.getCustomerId(), order.getTotalSale(), order.getProfit(), order.getData_time());


        return b;

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `order` ORDER BY DESC LIMIT 1");

        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            return  "O-" + tempId;
        }else{
            return "O-100";
        }



    }
}
