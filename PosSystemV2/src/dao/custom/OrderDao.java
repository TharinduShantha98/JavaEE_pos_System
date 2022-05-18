package dao.custom;

import dao.CrudDao;
import entitiy.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudDao<Order , String> {
       String getOrderId() throws SQLException, ClassNotFoundException;


}
