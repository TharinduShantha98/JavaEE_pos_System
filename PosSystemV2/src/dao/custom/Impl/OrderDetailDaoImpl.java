package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDao;
import entitiy.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl  implements OrderDetailDao {
    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("INSERT INTO `order detail` VALUES (?,?,?,?,?)", orderDetail.getOrderId(),
                orderDetail.getItemCode(), orderDetail.getSaleQty(), orderDetail.getSaleItemPrice(), orderDetail.getProfit());


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
