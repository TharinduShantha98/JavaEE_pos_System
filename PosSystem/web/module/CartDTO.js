
function CartDTO() {
    var _itemCode ;
    var _customerCode;
    var _itemName;
    var _unitPrice;
    var _saleQty;
    var _price;


    this.setItemCode =  function (itemCode) {
        _itemCode = itemCode;
    }
    this.setCustomerCode = function (customerCode) {
        _customerCode = customerCode;
    }
    this.setItemName =  function (itemName) {
        _itemName = itemName;
    }
    this.setUnitPrice  = function (unitPrice) {
        _unitPrice = unitPrice;
    }
    this.setSaleQty  =  function (saleQty) {
        _saleQty = saleQty;
    }
    this.setPrice  =  function (price) {
        _price = price
    }




    this.getItemCode = function () {
        return _itemCode;
    }
    this.getCustomerCode =  function () {
        return _customerCode;
    }
    this.getItemName = function () {
        return _itemName;
    }
    this.getUnitPrice  =  function () {
        return _unitPrice;
    }
    this.getSaleQty =  function () {
        return _saleQty;

    }
    this.getPrice  = function () {
        return _price;
    }

    
}