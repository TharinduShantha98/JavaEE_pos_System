/*==============item part =======================*/
$("#addItem").prop('disabled',true);
$("#updateItem").prop('disabled',true);
$("#deleteItem").prop('disabled',true);

$("#itemCode").val("I00-100");



$("#addItem").click(function () {
    $("#itemTable>tr").off('click');
    $("#updateItem").off('click');
    /*------------------------ add to data for table---------------- */

    let itemCode = $("#itemCode").val();
    let itemName = $("#itemName").val();
    let itemUnitPrice = $("#itemUnitPrice").val();
    let itemPackSize = $("#itemPackSize").val();
    let itemBuyingPrice = $("#itemBuyingPrice").val();
    let itemQty = $("#itemQty").val();


    var item =  new  ItemDTO();
    item.setId(itemCode);
    item.setItemName(itemName);
    item.setUnitPrice(itemUnitPrice);
    item.setPackSize(itemPackSize);
    item.setBuyingPrice(itemBuyingPrice);
    item.setQuantity(itemQty);

    if(confirm("Are you sure you want to add this item?")){
        items.push(item);
        addRowForItemTable();
        itemTableStyle();
        clearTextField();
        addItemIdForOrderPart();
        generateItemId();
        clearTextFieldStyle();
        $("#addItem").prop('disabled',true);
    }else{

    }
    /*------------------------------- end add to data for table---------------- */



    /*-------------------------------------table row click  --------------------------*/



    itemTableRowClick();

    /*-------------------------------------table row click --------------------------*/






    /*-------------------------------------update table row click --------------------------*/
    $("#updateItem").click(function () {
        let itemId  = $("#itemCode").val();
        console.log(itemId);

        if(confirm("Are you sure, you want to update this customer?")){
            for(var i =0; i < items.length; i++){
                if(itemId == items[i].getId()){

                    items[i].setItemName($("#itemName").val());
                    items[i].setUnitPrice($("#itemUnitPrice").val());
                    items[i].setPackSize($("#itemPackSize").val());
                    items[i].setBuyingPrice($("#itemBuyingPrice").val());
                    items[i].setQuantity($("#itemQty").val());
                }
            }



            $("#updateItem").prop('disabled',true);
            $("#deleteItem").prop('disabled',true);
        }else{


        }
        addRowForItemTable();
        itemTableRowClick();
        itemTableStyle();
        clearTextField();
        generateItemId();
        clearTextFieldStyle();


    })

})



function itemTableRowClick(){
    $("#itemTable>tr").click(function () {
        if(confirm("Are you sure you want to get this item")){
            let code = $(this).children().eq(0).text();
            let  name = $(this).children().eq(1).text();
            let unitPrice = $(this).children().eq(2).text();
            let packSize = $(this).children().eq(3).text();
            let buyingPrice = $(this).children().eq(4).text();
            let qty = $(this).children().eq(5).text();

            $("#itemCode").val(code);
            $("#itemName").val(name);
            $("#itemUnitPrice").val(unitPrice);
            $("#itemPackSize").val(packSize);
            $("#itemBuyingPrice").val(buyingPrice);
            $("#itemQty").val(qty);



            $("#updateItem").prop('disabled',false);
            $("#deleteItem").prop('disabled',false);
        }else{


        }

    })

}


function addRowForItemTable(){
    $("#itemTable").empty();
    for(var i =0; i < items.length; i++){
        let row = `<tr><td>${items[i].getId()}</td><td>${items[i].getItemName()}</td><td>${items[i].getUnitPrice()}</td><td>${items[i].getPackSize()}</td><td>${items[i].getBuyingPrice()}</td>
                <td>${items[i].getQuantity()}</td></tr>`;
        $("#itemTable").append(row);
    }
}


function itemTableStyle(){
    let table = document.getElementById("itemTable");
    for ( var i =0; i< table.rows.length ; i++){
        $("#itemTable").children().eq(i).css("font-weight","bold");
        $("#itemTable").children().eq(i).css("text-align","center");

    }

    /*  -------------table row hover ---------------*/
    $("#itemTable>tr").hover(function () {
        $(this).css("background-color","#95a5a6" );
    },function () {
        $(this).css("background-color", "#54a0ff");
    })

    $("#itemTable>tr").css("background-color","#54a0ff");

}




/* -------------------------------delete Item ------------------------------*/

$("#deleteItem").click(function () {
    if(confirm("Are you sure, you want delete this item?")){
        let id =  $("#itemCode").val();
        for(let i =0; i <items.length; i++){
            if(id == items[i].getId()){
                items.splice(i,1);
            }else{

            }

        }
        addRowForItemTable();
        itemTableStyle();
        itemTableRowClick();
        clearTextField();
        generateItemId();
        clearTextFieldStyle();
        $("#updateItem").prop('disabled',true);
        $("#deleteItem").prop('disabled',true);



    }else{

    }




})





/*-------------------------------------Search Item--------------------------*/

$("#itemSearch").keyup(function (event) {

    if(event.keyCode == 13){
        $("#btnItemSearch").focus();
    }

})




$("#btnItemSearch").click(function () {
    let searchItemCode = $("#itemSearch").val();
    for(var k=0; k < items.length; k++){
        if(items[k].getId() == searchItemCode){
            $("#itemCode").val(items[k].getId());
            $("#itemName").val(items[k].getItemName());
            $("#itemUnitPrice").val(items[k].getUnitPrice());
            $("#itemPackSize").val(items[k].getPackSize());
            $("#itemBuyingPrice").val(items[k].getBuyingPrice());
            $("#itemQty").val(items[k].getQuantity());

        }else{

        }

    }

})

$("#clearText").click(function () {
    clearTextField();
    generateItemId();
})


function clearTextField(){
    //$("#itemCode").val("");
    $("#itemName").val("");
    $("#itemUnitPrice").val("");
    $("#itemPackSize").val("");
    $("#itemBuyingPrice").val("");
    $("#itemQty").val("");



}

function generateItemId(){

    let index = items.length;
    let gId  = items[index-1].getId();
    let text =  gId.substr(4,7);
    let number =  Number(text);
    let nextIdNum =  number+ 1;
    let nextId =  "I00-"+nextIdNum;
    $("#itemCode").val(nextId);

}






/*-------------text field focus  and validation part of item ------------*/
let itemIdRegx = /^(I00)[-][0-9]{3,9}$/;
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


