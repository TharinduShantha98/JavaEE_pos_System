package dao.custom.Impl;

import controller.OrderServlet;
import dao.CrudUtil;
import dao.custom.OrderDao;
import entitiy.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = OrderServlet.dataSource.getConnection();


        boolean b = CrudUtil.executeUpdate("INSERT INTO `order` VALUES (?,?,?,?,?)",connection,
                order.getOrderId(), order.getCustomerId(), order.getTotalSale(), order.getProfit(), order.getData_time());

        connection.close();
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

        ArrayList<Order> orderArrayList = new ArrayList<>();

        Connection connection = OrderServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM `order`",connection);
        while (resultSet.next()){
            orderArrayList.add(new Order(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getBigDecimal(4),
                    resultSet.getString(5)));
        }
        connection.close();

        return orderArrayList;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = OrderServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `order` ORDER BY  orderId  DESC LIMIT 1",connection);

        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            connection.close();
            return  "O-" + tempId;
        }else{
            connection.close();
            return "O-100";
        }



    }
}
