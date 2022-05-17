getAllCustomer();





function getAllCustomer() {
    console.log("get all cutomer")

    $("#customerTable").empty();

    $.ajax({
        url: "customer?option=GETALL",
        method: "GET",

        success: function (resp) {
            for (const customers of resp.data ){
                console.log(resp.data);

                let newRow = `<tr><td>${customers.id}</td></td><td>${customers.firstName}</td><td>${customers.lastName}
                                </td><td>${customers.address}</td><td>${customers.email}</td><td>${customers.TelNo}</td></tr>`;
                $("#customerTable").append(newRow);


            }
            customerTableRowClick();

        }
    })




}


$("#btnCustomerSearch").click(function () {
    let cusId = $("#customerSearch").val();

    $.ajax({
        url:"customer?option=SEARCH&customerId="+ cusId,
        method: "GET",

        success: function (resp) {
            if(resp.status == 200){
                $("#customerId").val(resp.data.itemCode);
                $("#customerFName").val(resp.data.itemFName);
                $("#customerLName").val(resp.data.itemLName);
                $("#customerAddress").val(resp.data.address);
                $("#customerEmail").val(resp.data.email);
                $("#customerTelNum").val(resp.data.telNo);

            }

        }




    })


})









$("#addCustomer").click(function () {







    let data = $("#customerForm").serialize();


    $.ajax({
        url: "customer",
        method: "POST",
        data: data,
        success: function (resp) {

            if(resp.status == 200){
                alert(resp.message);
                getAllCustomer();

            }else if(resp.status == 400){
                alert(resp.message);
            }


        },
        error: function () {

        }


    })



})


function customerTableRowClick(){

    $("#customerTable>tr").click(function (){

        console.log("click table row")
        if(confirm("Are you sure, you want to see this row")){
            let id  =  $(this).children().eq(0).text();
            let fName =  $(this).children().eq(1).text();
            let lName =  $(this).children().eq(2).text();
            let address =  $(this).children().eq(3).text();
            let email =  $(this).children().eq(4).text();
            let telNo =  $(this).children().eq(5).text();



            $("#customerId").val(id);
            $("#customerFName").val(fName);
            $("#customerLName").val(lName);
            $("#customerAddress").val(address);
            $("#customerEmail").val(email);
            $("#customerTelNum").val(telNo);

        }else {

        }



    });




}




$("#updateCustomer").click(function () {
    let customerId =  $("#customerId").val();
    let customerFName  =  $("#customerFName").val();
    let customerLName  =  $("#customerLName").val();
    let customerAddress  =  $("#customerAddress").val();
    let customerEmail =  $("#customerEmail").val();
    let customerTelNo =  $("#customerTelNum").val();



    console.log(customerId)

    let jsonData  =  JSON.stringify({
        "customerId": customerId,
        "customerFName": customerFName,
        "customerLName": customerLName,
        "customerAddress": customerAddress,
        "customerEmail": customerEmail,
        "customerTelNo": customerTelNo
    })



    $.ajax({
        url:"customer",
        method: "PUT",
        data: jsonData,
        success: function (reap) {

            if(reap.status ==  200){
                alert(reap.message);
                console.log(reap.message);
                getAllCustomer();
            }else if(reap.status == 400){
                alert(reap.message);
            }else if(reap.status){
                alert(reap.message);
            }

        }




    });

})



$("#deleteCustomer").click(function () {

    let customerId = $("#customerId").val();


    $.ajax({
        url: "customer?cusId=" + customerId,
        method: "DELETE",


        success: function (resp) {
            if(resp.status == 200){
                alert(resp.message);
                getAllCustomer();
            }else if(resp.status == 500){
                alert(resp.message);
            }

        }
    })



})




/*=================================item============================*/

getAllItem();



function getAllItem() {
    $("#itemTable").empty();

    let getAll =  "GETALL";

    $.ajax({
        url: "item?option="+ getAll,
        method: "GET",


        success: function (resp) {

            for (const item of resp.data){


                let row = `<tr><td>${item.itemCode}</td><td>${item.itemName}</td><td>${item.unitPrice}</td><td>${item.packSize}</td><td>${item.buyingPrice}</td>
                <td>${item.quantity}</td></tr>`;
                $("#itemTable").append(row);

            }


            itemTableRowClick();
        }
    })

}



$("#btnItemSearch").click(function () {
    let itemCode = $("#itemSearch").val();

    let search = "SEARCH";

    $.ajax({
        url: "item?option="+ search+"&itemCode="+itemCode,
        method: "GET",
        success: function (resp) {
            if(resp.status == 200){
                let itemName  = resp.data.itemName;
                $("#itemCode").val(resp.data.itemcode);
                $("#itemName").val(resp.data.itemName);
                $("#itemUnitPrice").val(resp.data.unitPrice);
                $("#itemBuyingPrice").val(resp.data.buyingPrice);
                $("#itemPackSize").val(resp.data.packSize);
                $("#itemQty").val(resp.data.quantity);

            }

        }




    })


})




$("#addItem").click(function () {

    let data = $("#itemForm").serialize();

    $.ajax({
        url: "item",
        method: "POST",
        data: data,
        success: function (resp) {
            if(resp.status == 200){
                alert(resp.message);
                getAllItem();
                getItemCode();
            }else if(resp.status == 500){
                alert(resp.message);

            }else if (resp.status == 400){
                alert(resp.message);

            }

        }

    })

})




$("#updateItem").click(function () {
    let itemCode = $("#itemCode").val();
    let itemName  = $("#itemName").val();
    let unitPrice = $("#itemUnitPrice").val();
    let buyingPrice  = $("#itemBuyingPrice").val();
    let packSize  = $("#itemPackSize").val();
    let itemQty  = $("#itemQty").val();

    let data = JSON.stringify({
        "itemCode": itemCode,
        "itemName": itemName,
        "unitPrice": unitPrice,
        "buyingPrice": buyingPrice,
        "packSize": packSize,
        "Qty": itemQty
    });




    $.ajax({
        url: "item",
        method: "PUT",
        data: data,
        contentType: "application/json",
        success: function (resp) {

            if(resp.status == 200){
                alert(resp.message);
                getAllItem();
            }else if(resp.status == 400){
                alert(resp.message);
            }else if(resp.status == 500){
                alert(resp.message);
            }


        }




    })


})

function itemTableRowClick(){
    $("#itemTable>tr").click(function () {

        console.log("item")

        let itemCode   = $(this).children().eq(0).text();
        let itemName   = $(this).children().eq(1).text();
        let unitPrice   = $(this).children().eq(2).text();
        let packSize   = $(this).children().eq(3).text();
        let buyingPrice   = $(this).children().eq(4).text();
        let quantity   = $(this).children().eq(5).text();


        console.log(itemCode);



        $("#itemCode").val(itemCode);
        $("#itemName").val(itemName);
        $("#itemBuyingPrice").val(buyingPrice);
        $("#itemUnitPrice").val(unitPrice);
        $("#itemPackSize").val(packSize);
        $("#itemQty").val(quantity);



    });







}








$("#deleteItem").click(function () {
    let itemCode = $("#itemCode").val();

    $.ajax({
        url:"item?itemCode="+ itemCode ,
        method: "DELETE",

        success:function (resp) {
            if(resp.status == 200){
                alert(resp.message);
                getAllItem();
            }else if(resp.status == 500){
                alert(resp.message);

            }else if(resp.status == 400){
                alert(resp.message);
            }

        }





    })


})
