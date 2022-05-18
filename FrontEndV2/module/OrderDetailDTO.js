
function OrderDetailDTO() {
        var _orderId;
        var _itemCode;
        var _unitPrice;
        var _qty;
        var _sale;
        var _itemProfit;



        this.setOrderId =  function (orderId) {
                _orderId = orderId;
        }
        this.setItemCode = function (itemCode) {
                _itemCode = itemCode;
        }
        this. setUnitPrice = function (unitPrice) {
                _unitPrice = unitPrice;
        }
        this. setQty = function (qty) {
                 _qty = qty;
        }
        this.setSale = function (sale) {
                _sale = sale;
        }
        this.setItemProfit = function (itemProfit) {
                _itemProfit = itemProfit;
        }


        this.getOrderId =  function () {
            return _orderId;
        }
        this.getItemCode = function () {
           return  _itemCode;
        }
        this.getUnitPrice = function () {
            return _unitPrice;
        }
        this. getQty = function () {
            return _qty;
        }
        this.getSale = function () {
             return _sale;
        }
        this.getItemProfit = function () {
            return _itemProfit;
        }







}