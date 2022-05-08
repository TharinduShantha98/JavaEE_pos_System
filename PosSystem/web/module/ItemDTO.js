
function ItemDTO(id,itemName, unitPrice, packSize, buyingPrice, quantity) {

    var _id =  id;
    var _itemName = itemName;
    var _unitPrice = unitPrice;
    var _packSize = packSize;
    var _buyingPrice = buyingPrice;
    var _quantity = quantity;

    this.getId = function () {
        return _id;
    }

    this.setId = function (id) {
        _id = id;
    }


    this.setItemName = function (itemName) {
        _itemName = itemName;
    }
    this.getItemName = function () {
            return _itemName;
    }

    this.setUnitPrice = function (unitPrice) {
        _unitPrice = unitPrice;
    }
    this.getUnitPrice = function () {
        return _unitPrice;
    }


    this.setPackSize = function (packSize) {
        _packSize = packSize;
    }
    this.getPackSize  = function () {
        return _packSize;
    }


    this.setBuyingPrice = function (buyingPrice) {
        _buyingPrice = buyingPrice;
    }
    this.getBuyingPrice = function () {
            return _buyingPrice;
    }



    this.setQuantity = function (quantity) {
        _quantity = quantity;
    }
    this.getQuantity = function () {
        return _quantity;
    }

}