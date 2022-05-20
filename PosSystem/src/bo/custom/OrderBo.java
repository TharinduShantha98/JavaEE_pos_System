package bo.custom;

import bo.SuperBo;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {

    boolean addOrder(OrderDTO orderDTO);
    boolean addOrderDetail(ArrayList<OrderDetailDTO> orderDetailDTOS, String orderId) throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> getQAllOrders() throws SQLException, ClassNotFoundException;


}
