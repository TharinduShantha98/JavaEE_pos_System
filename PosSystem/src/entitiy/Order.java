package entitiy;

import java.math.BigDecimal;

public class Order {

    private String orderId;
    private String customerId;
    private BigDecimal totalSale;
    private BigDecimal profit;
    private String data_time;


    public Order(String orderId, String customerId, BigDecimal totalSale, BigDecimal profit, String data_time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalSale = totalSale;
        this.profit = profit;
        this.data_time = data_time;
    }

    public Order() {
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

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
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
