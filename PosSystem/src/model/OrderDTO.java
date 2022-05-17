package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderDTO {


    private String orderId;
    private String customerId;
    private BigDecimal sale;
    private  BigDecimal profit;
    private String data_time;
    private ArrayList<OrderDetailDTO> orderDetailDTOS;


    public OrderDTO(String orderId, String customerId, BigDecimal sale, BigDecimal profit, String data_time) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.sale = sale;
        this.profit = profit;
        this.data_time = data_time;
    }

    public OrderDTO(String orderId, String customerId, BigDecimal sale, BigDecimal profit, String data_time, ArrayList<OrderDetailDTO> orderDetailDTOS) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.sale = sale;
        this.profit = profit;
        this.data_time = data_time;
        this.orderDetailDTOS = orderDetailDTOS;
    }


    public ArrayList<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(ArrayList<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public OrderDTO() {
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getData_time() {
        return data_time;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }
}
