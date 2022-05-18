
function OrderDTO() {
        var _orderId;
        var _customerId;
        var _sale;
        var _profit;
        var _dateTime;


        this.setOrderId =  function (orderId) {
                _orderId = orderId;
        }
        this.setCustomerId =  function (customerId) {
                _customerId =  customerId;
        }
        this.setSale =  function (sale) {
                _sale = sale;
        }
        this.setProfit =  function (profit) {
                _profit = profit;
        }
        this.setDateTime =  function (dateTime) {
                _dateTime = dateTime;
        }

        this.getOrderId = function () {
                return _orderId;
        }
        this.getCustomerId = function () {
                return _customerId;
        }
        this.getSale =  function () {
                return _sale;
        }
        this.getProfit =  function () {
                return _profit;
        }
        this.getDateAndTime = function () {
                return _dateTime;
        }


}