package entitiy;

public class OrderDetail {
    private String orderId;
    private  String itemCode;
    private  double saleQty;
    private  double saleItemPrice;
    private  double profit;


    public OrderDetail(String orderId, String itemCode, double saleQty, double saleItemPrice, double profit) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.saleQty = saleQty;
        this.saleItemPrice = saleItemPrice;
        this.profit = profit;
    }

    public OrderDetail() {
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(double saleQty) {
        this.saleQty = saleQty;
    }

    public double getSaleItemPrice() {
        return saleItemPrice;
    }

    public void setSaleItemPrice(double saleItemPrice) {
        this.saleItemPrice = saleItemPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
