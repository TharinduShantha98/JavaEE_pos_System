$("#addItem").prop('disabled',true);
$("#updateItem").prop('disabled',true);
$("#deleteItem").prop('disabled',true);


getAllItem();



function getAllItem() {
    $("#itemTable").empty();

    let getAll =  "GETALL";

    $.ajax({
        url: "http://localhost:8080/posSystemV2/item?option="+ getAll,
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
        url: "http://localhost:8080/posSystemV2/item?option="+ search+"&itemCode="+itemCode,
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
        url: "http://localhost:8080/posSystemV2/item",
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
        url: "http://localhost:8080/posSystemV2/item",
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
        url:"http://localhost:8080/posSystemV2/item?itemCode="+ itemCode ,
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


getItemCode();

function getItemCode() {
    $.ajax({
        url: "http://localhost:8080/posSystemV2/item?option=GET_ITEM_CODE",
        method: "GET",

        success:function (resp) {
            if(resp.status == 200){
                console.log(resp.orderId)
                $("#itemCode").val(resp.itemCode);
            }

        }



    })

}




$("#clearText").click(function () {
    clearTextField();

})


function clearTextField(){
    //$("#itemCode").val("");
    $("#itemName").val("");
    $("#itemUnitPrice").val("");
    $("#itemPackSize").val("");
    $("#itemBuyingPrice").val("");
    $("#itemQty").val("");



}







/*-------------text field focus  and validation part of item ------------*/
let itemIdRegx = /^(I)[-][0-9]{3,9}$/;
let itemNameRegx = /^[A-z]{3,15}$/;
let itemUnitPriceRegx = /^[0-9]{1,9}$/;
let itemBuyingPriceRegx = /^[0-9]{1,9}$/;
let itemPackSizeRegx = /^[0-9]+(l|g|kg|ml)$/;
let itemQtyRegx = /^[0-9]{1,9}$/;


$('#itemCode,#itemName,#itemUnitPrice,#itemPackSize,#itemBuyingPrice,#itemQty').keydown(function (eventOb) {
    if (eventOb.key == "Tab") {
        eventOb.preventDefault();

    }
})




function validation(testRegex, event,id,nextId,correctRegex){

    let test = correctRegex.test(testRegex);
    if(test){
        $(id).css("border", "1px solid  green");
        $(id).css("box-shadow", "0px 0px 10px #5ad25a");

        if(event.keyCode == 13){
            $(nextId).focus();
        }

        return true;
    }else{
        $(id).css("border", "1px solid  red");
        $(id).css("box-shadow", "0px 0px 10px #d25a72");
        $("#addItem").prop('disabled',true);
        return false;
    }


}

$("#itemCode").keyup(function (event) {
    let code = $("#itemCode").val();
    let boolean = validation(code,event,"#itemCode","#itemName",itemIdRegx);
    console.log(boolean);

    if(boolean){
        $("#validationI1").text("");
        checkButton();
    }else{
        $("#validationI1").text("Input format-(I00-000)")
        $("#validationI1").css("color", "red");
        $("#validationI1").css("font-weight", "bold");
    }

})


$("#itemName").keyup(function (event) {
    let code = $("#itemName").val();
    let boolean = validation(code,event,"#itemName","#itemUnitPrice",itemNameRegx);

    if(boolean){
        $("#validationI2").text("");
        checkButton();
    }else{
        $("#validationI2").text("by A to z characters only one word")
        $("#validationI2").css("color", "red");
        $("#validationI2").css("font-weight", "bold");
    }
})

$("#itemUnitPrice").keyup(function (event) {
    let code = $("#itemUnitPrice").val();
    let boolean = validation(code,event,"#itemUnitPrice","#itemBuyingPrice",itemUnitPriceRegx);

    if(boolean){
        $("#validationI3").text("");
        checkButton();
    }else{
        $("#validationI3").text("only numbers")
        $("#validationI3").css("color", "red");
        $("#validationI3").css("font-weight", "bold");
    }


})

$("#itemPackSize").keyup(function (event) {
    let code = $("#itemPackSize").val();
    let boolean = validation(code,event,"#itemPackSize","#itemQty",itemPackSizeRegx);

    if(boolean){
        $("#validationI4").text("");
        checkButton();
    }else{
        $("#validationI4").text("please input pack Size (kg/l/ml/l) end of number")
        $("#validationI4").css("color", "red");
        $("#validationI4").css("font-weight", "bold");
    }

})

$("#itemBuyingPrice").keyup(function (event) {
    let code = $("#itemBuyingPrice").val();
    let boolean = validation(code,event,"#itemBuyingPrice","#itemPackSize",itemBuyingPriceRegx);

    if(boolean){
        $("#validationI5").text("");
        checkButton();
    }else{
        $("#validationI5").text("only number")
        $("#validationI5").css("color", "red");
        $("#validationI5").css("font-weight", "bold");
    }
})


$("#itemQty").keyup(function (event) {
    let code = $("#itemQty").val();
    let boolean = validation(code,event,"#itemQty","#addItem",itemQtyRegx);

    if(boolean){
        $("#validationI6").text("");
        /*  $("#addItem").prop('disabled',false);*/
        checkButton();
    }else{
        $("#validationI6").text("only number")
        $("#validationI6").css("color", "red");
        $("#validationI6").css("font-weight", "bold");
    }




})

function checkButton() {
    if(($("#itemCode").val() != "") &&  ($("#ItemName").val() != "") &&  ($("#itemUnitPrice").val() != "") &&
        ($("#itemPackSize").val() != "") &&  ($("#itemBuyingPrice").val() != "") && ($("#itemQty").val() != "")){

        $("#addItem").prop('disabled',false);


    }else {
        $("#addItem").prop('disabled',true);

    }
}



function clearTextFieldStyle(){
    let textField  = [];
    textField.push("#itemCode");
    textField.push("#itemName");
    textField.push("#itemUnitPrice");
    textField.push("#itemBuyingPrice");
    textField.push("#itemQty");
    textField.push("#itemPackSize");



    for(let i=0; i < textField.length; i++){
        $(textField[i]).css("border", "1px solid #ced4da");
        $(textField[i]).css("box-shadow", "none");


    }

}



$("#clearText").click(function () {
    clearTextField();
    clearTextFieldStyle();


})





var position2 = 1;
var text = $("#itemManage").text();
function itemManageFun(){
    let outPut2 = text.substr(0,position2);
    $("#itemManage").text(outPut2);
    position2++;

    if(position2 == text.length){
        position2 = 1;
    }


}








setInterval(itemManageFun,100);
