
function customerDTO(id,fName,lName, address, email,telNum) {
    var _id = id;
    var _fName = fName;
    var _lName = lName;
    var _address = address;
    var _email = email;
    var _telNum = telNum;


    this.getId = function() {
        return _id;

    }

    this.setId = function(id) {
        _id = id;

    }

    this.getFName = function () {
        return _fName;
    }

    this.setFName = function (fName) {
        _fName = fName;

    }


    this.getLName = function () {
        return _lName;
    }

    this.setLName = function (lName) {
        _lName = lName;

    }


    this.getAddress = function () {
            return _address;
    }
    this.setAddress = function (address) {
        _address = address

    }


    this.getEmail = function () {
        return _email;

    }
    this.setEmail = function (email) {
        _email = email;
    }


    this.getTelNum = function () {
        return _telNum;
    }
    this.setTelNum = function (telNumber) {
        _telNum = telNumber;
    }


}