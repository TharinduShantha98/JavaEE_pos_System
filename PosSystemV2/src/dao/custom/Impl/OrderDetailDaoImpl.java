package dao.custom.Impl;

import controller.OrderServlet;
import dao.CrudUtil;
import dao.custom.OrderDetailDao;
import entitiy.Order;
import entitiy.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl  implements OrderDetailDao {
    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        Connection connection = OrderServlet.dataSource.getConnection();

        boolean b = CrudUtil.executeUpdate("INSERT INTO `order detail` VALUES (?,?,?,?,?)",connection, orderDetail.getOrderId(),
                orderDetail.getItemCode(), orderDetail.getSaleQty(), orderDetail.getSaleItemPrice(), orderDetail.getProfit());

        connection.close();
        return b;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
