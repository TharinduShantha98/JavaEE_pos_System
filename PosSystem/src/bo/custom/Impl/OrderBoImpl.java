package bo.custom.Impl;

import bo.custom.ItemBo;
import bo.custom.OrderBo;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.Impl.OrderDaoImpl;
import dao.custom.Impl.OrderDetailDaoImpl;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.OrderDetailDao;
import db.DbConnection;
import entitiy.Order;
import entitiy.OrderDetail;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {


    OrderDao orderDao = new OrderDaoImpl();
    OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    ItemDao itemDao = new ItemDaoImpl();





    @Override
    public boolean addOrder(OrderDTO orderDTO) {

        Connection con  =  null;

        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);


            boolean add = orderDao.add(new Order(orderDTO.getOrderId(),
                    orderDTO.getCustomerId(), orderDTO.getSale(), orderDTO.getProfit(), orderDTO.getData_time()));


            if(add){
                if(addOrderDetail(orderDTO.getOrderDetailDTOS(),orderDTO.getOrderId())){
                    con.commit();
                    con.setAutoCommit(true);



                }else {
                    con.rollback();
                    return false;
                }




            }else{
                con.rollback();
                return false;

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean addOrderDetail(ArrayList<OrderDetailDTO> orderDetailDTOS, String orderId) throws SQLException, ClassNotFoundException {

        for (OrderDetailDTO orderDetailDTO :orderDetailDTOS
                ) {

            boolean add = orderDetailDao.add(new OrderDetail(orderId, orderDetailDTO.getItemCode(),
                    orderDetailDTO.getSaleQty(), orderDetailDTO.getSaleItemPrice(), orderDetailDTO.getProfit()));



            if(add){
                boolean b = updateItemQty(orderDetailDTO.getItemCode(), orderDetailDTO.getSaleQty());
                if(b){
                    return true;

                }else{
                    return false;
                }


            }else{
                return false;
            }


        }



        return true;
    }



    private boolean updateItemQty(String itemCode , double qty ) throws SQLException, ClassNotFoundException {

        boolean b = itemDao.updateItemQty(itemCode, qty);
        return b;


    }


}
