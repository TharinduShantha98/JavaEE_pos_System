/*-------------------------------Customer Order part ----------------------------------*/


//$("#addToCart").prop("disabled","true");
$("#itemPay").prop("disabled","true");
$("#btnDelOrder").prop('disabled',true);
$("#btnUpdOrder").prop('disabled',true);




$("#cusNameOrder").attr('disabled', true);
$("#cusAddressOrder").attr('disabled', true);
$("#cusTelOrder").attr('disabled', true);
$("#cusEmailOrder").attr('disabled', true);


  $("#itemPriceOrder").attr('disabled', true);
  $("#itemNameOrder").attr('disabled', true);
  $("#itemQtyOrder").attr('disabled', true);
  $("#itemPackSizeOrder").attr('disabled', true);
  $("#itemTotalPrice").attr('disabled', true);
  $("#allItemTotalFee").attr('disabled', true);



function addCustomerIdForOrderPart() {
    var str  = '';
    for(var i = 0; i < customers.length; i++){
        str += '<option value="'+customers[i].getId()+'" />';
    }

    var my_list = document.getElementById("customerIdList");
    my_list.innerHTML = str;

}




$("#cusIdOrder").keyup(function (event) {

    if(event.keyCode == 13){
        let cusId  = $("#cusIdOrder").val();
        for (var i =0; i < customers.length; i++){
            if(cusId == customers[i].getId()){
                $("#cusIdOrder").css("border", "1px solid  green");


                $("#cusNameOrder").val(customers[i].getFName());
                $("#cusNameOrder").css("border", "1px solid  green")

                $("#cusAddressOrder").val(customers[i].getAddress());
                $("#cusAddressOrder").css("border", "1px solid  green");

                $("#cusTelOrder").val(customers[i].getTelNum());
                $("#cusTelOrder").css("border", "1px solid  green");

                $("#cusEmailOrder").val(customers[i].getEmail());
                $("#cusEmailOrder").css("border", "1px solid  green");

            }
        }

        $("#itemIdOrder").focus();
    }


})



function addItemIdForOrderPart() {
    var str  = '';
    for(var i = 0; i < items.length; i++){
        str += '<option value="'+items[i].getId()+'" />';
    }

    var my_list = document.getElementById("itemIdList");
    my_list.innerHTML = str;

}



$("#itemIdOrder").keyup(function (event) {
    console.log("hello");
    if(event.keyCode == 13){
        let cusId  = $("#itemIdOrder").val();
        for (var i =0; i < items.length; i++){
            if(cusId == items[i].getId()){
                $("#itemIdOrder").css("border", "1px solid  green");


                $("#itemNameOrder").val(items[i].getItemName());
                $("#itemNameOrder").css("border", "1px solid  green")

                $("#itemQtyOrder").val(items[i].getQuantity());
                $("#itemQtyOrder").css("border", "1px solid  green");

                $("#itemPackSizeOrder").val(items[i].getPackSize());
                $("#itemPackSizeOrder").css("border", "1px solid  green");

                $("#itemPriceOrder").val(items[i].getUnitPrice());
                $("#itemPriceOrder").css("border", "1px solid  green");

            }
        }

        $("#saleItemQty").focus();
    }


})






$("#saleItemQty").keyup(function (event) {

    let itemUnitPrice = $("#itemPriceOrder").val();
    let priceNum = parseInt(itemUnitPrice);

    if(event.keyCode == 13){
        let itemQty = $("#itemQtyOrder").val();
        let itemQtyNum = parseInt(itemQty);

        let qty = $("#saleItemQty").val();


        if(qty >= 0){
            if(qty.length >=1){
                if(itemQtyNum > qty){
                    let itemPrice =  qty * priceNum;
                    $("#validationQty").text("");
                    $("#itemTotalPrice").attr('disabled', false);
                    $("#itemTotalPrice").val(itemPrice);
                    $("#itemTotalPrice").focus();

                }else{
                    $("#validationQty").text("please input amount lower than :"+ itemQtyNum);
                    $("#validationQty").css("color", "red");
                    $("#validationQty").css("font-weight", "bold");

                }

            }else{

                $("#validationQty").text("please input qty");
                $("#validationQty").css("color", "red");
                $("#validationQty").css("font-weight", "bold");

            }

        }else{

            $("#validationQty").text("please not input negative number");
            $("#validationQty").css("color", "red");
            $("#validationQty").css("font-weight", "bold");


        }




    }
})



$("#itemTotalPrice").keyup(function (event) {


    let num =  $("#itemTotalPrice").val();


    if(event.keyCode ==  13){


        if(num >= 0){
            if(num.length>=1){
                $("#addToCart").focus();
                $("#validationItemTotal").text("");

            }else{

                $("#validationItemTotal").text("please input qty");
                $("#validationItemTotal").css("color", "red");
                $("#validationItemTotal").css("font-weight", "bold");

            }

        }else{
            $("#validationItemTotal").text("please not input negative number");
            $("#validationItemTotal").css("color", "red");
            $("#validationItemTotal").css("font-weight", "bold");



        }






    }

})


/*===============add to cart===========*/
let cartArray = new Array();

$("#addToCart").click(function () {

    let itemCode =  $("#itemIdOrder").val();
    let itemName = $("#itemNameOrder").val();
    let customerId  =  $("#cusIdOrder").val();
    let unitPrice  =  $("#itemPriceOrder").val();
    let saleQty =  $("#saleItemQty").val();
    let price  =  $("#itemTotalPrice").val();


    $("#itemPay").prop("disabled",false);


    let cartDTO = new CartDTO();
    cartDTO.setItemCode(itemCode);
    cartDTO.setItemName(itemName);
    cartDTO.setCustomerCode(customerId);
    cartDTO.setUnitPrice(unitPrice);
    cartDTO.setSaleQty(saleQty);
    cartDTO.setPrice(price);




    let allItemProfit = 0;
    if(confirm("Are you sure, you want add this order")){
        cartArray.push(cartDTO);
        addOrderCartRow();
        addSumTotalPrice();
        getDateAndTime();
        allItemProfit = allItemProfit + countSaleProfit(cartDTO.getItemCode());
        console.log(allItemProfit);
        tableRowClickCart();
        clearOrderTextFieldPart2();
        $("#itemTotalPrice").attr('disabled', true);

    }else {

    }



})


function tableRowClickCart() {

    $("#orderCartTable>tr").click(function () {
        let itemCode =  $(this).children().eq(0).text();


        $("#btnDelOrder").prop('disabled',false);
        $("#btnUpdOrder").prop('disabled',false);

        for(let i =0; i < cartArray.length; i++){
            if(itemCode ==  cartArray[i].getItemCode()){

                $("#itemIdOrder").val(cartArray[i].getItemCode());
                $("#itemNameOrder").val(cartArray[i].getItemName());
                $("#itemPriceOrder").val(cartArray[i].getUnitPrice());
                $("#saleItemQty").val(cartArray[i].getSaleQty());
                $("#itemTotalPrice").val(cartArray[i].getPrice());



                for(let j =0; j < items.length;j++){
                    if(cartArray[i].getItemCode()== items[j].getId()){
                        $("#itemQtyOrder").val(items[i].getQuantity());
                        $("#itemPackSizeOrder").val(items[i].getPackSize());


                    }

                }


            }
        }


    })


}


$("#btnDelOrder").click(function () {

    if(confirm("Are you sure you want delete this order ?")){

        let itemName = $("#itemIdOrder").val();

        for(let i =0; i < cartArray.length; i++){
            if(itemName == cartArray[i].getItemCode()){
                cartArray.splice(i,1);

            }
        }

        $("#btnDelOrder").prop('disabled',true);
        $("#btnUpdOrder").prop('disabled',true);

        addOrderCartRow();
        tableRowClickCart();



    }

})



$("#searchOrder").keyup(function (event) {

    if(event.keyCode == 13){

        let temp = new Array();

        let orderId =  $("#searchOrder").val();
        console.log("orderid "+ orderId);


        let a = 0;
        for(let b =0; b < orderDetail.length; b++){
            if(orderId == orderDetail[b].getOrderId()){

                console.log(a);
                a++;
                temp.push(orderDetail[b]);

                console.log("temp" + temp[0].getItemCode());

            }

        }

        let customerId;

        for(let i =0; i < order.length; i++){
            if(orderId == order[i].getOrderId()){
                customerId = order[i].getCustomerId();


            }


        }

        console.log("customer id " + customerId);


        let itemName;
        $("#orderCartTable").empty();
        for(let i =0; i < temp.length;i++){

            for(let j =0; j < items.length; j++){
                if(temp[i].getItemCode() ==  items[j].getId()){
                    itemName =  items[j].getItemName();
                }
            }

            let newRow = `<tr><td>${temp[i].getItemCode()}</td></td><td>${customerId}</td><td>${itemName}
                </td><td>${temp[i].getUnitPrice()}</td><td>${temp[i].getQty()}</td><td>${temp[i].getSale()}</td></tr>`;
            $("#orderCartTable").append(newRow);

        }

    }

})




$("#btnUpdOrder").click(function () {
    let itemName = $("#itemIdOrder").val();



    if(confirm("Are you sure you want this order update ?")){
        for(let i =0; i < cartArray.length; i++){
            if(itemName == cartArray[i].getItemCode()){
                cartArray[i].setItemCode($("#itemIdOrder").val());
                cartArray[i].setItemName($("#itemNameOrder").val());
                cartArray[i].setUnitPrice($("#itemPriceOrder").val());
                cartArray[i].setSaleQty($("#saleItemQty").val());
                cartArray[i].setPrice($("#allItemTotalFee").val());

            }
        }
        $("#btnDelOrder").prop('disabled',true);
        $("#btnUpdOrder").prop('disabled',true);
        addOrderCartRow();
        addSumTotalPrice();
        tableRowClickCart();

    }



})








function addOrderCartRow() {

    $("#orderCartTable").empty();
    for (var i =0; i <cartArray.length; i++){
        let newRow = `<tr><td>${cartArray[i].getItemCode()}</td></td><td>${cartArray[i].getCustomerCode()}</td><td>${cartArray[i].getItemName()}
                </td><td>${cartArray[i].getUnitPrice()}</td><td>${cartArray[i].getSaleQty()}</td><td>${cartArray[i].getPrice()}</td></tr>`;
        $("#orderCartTable").append(newRow);
    }


}


function  addSumTotalPrice() {
    let totalPrice = 0;
    for(let i =0; i < cartArray.length;i++){
        let price = parseInt(cartArray[i].getPrice())
        totalPrice = totalPrice + price;
    }
    $("#allItemTotalFee").val(totalPrice);
}


function getDateAndTime() {
    let dateAndTime =  new Date();
    let hours = dateAndTime.getHours();
    let min = dateAndTime.getMinutes();
    let second  = dateAndTime.getSeconds();

    let day = dateAndTime.getDate();
    let month =  dateAndTime.getMonth();
    let year =  dateAndTime.getFullYear();


    let dateAndTime1 =hours + ":" + min + ":" + second +" / "+ day + "-" + month + "-" + year;

    return  dateAndTime1;

}


function countSaleProfit(ItemCode) {
    let itemBuyingPrice = 0;
    let itemUnitPrice   = 0;
    for(let i =0; i < items.length; i++){
        if(ItemCode == items[i].getId()){
            itemBuyingPrice = items[i].getBuyingPrice();
            itemUnitPrice =  items[i].getUnitPrice();

            let buyingPrice = parseInt(itemBuyingPrice);
            let unitPrice = parseInt(itemUnitPrice);

            return  unitPrice - buyingPrice;

        }
    }
}


$("#itemPay").click(function () {

    //  alert("hello");

    let orderId = $("#lblOrderId").text();
    let customerId = $("#cusIdOrder").val();
    let sale =  $("#allItemTotalFee").val();
    let profit =calculateCustomerAllItemProfit();
    let dateAndTime = getDateAndTime();

    let orderDTO = new OrderDTO();
    orderDTO.setCustomerId(customerId);
    orderDTO.setOrderId(orderId);
    orderDTO.setSale(sale);
    orderDTO.setProfit(profit);
    orderDTO.setDateTime(dateAndTime);

    order.push(orderDTO);

    console.log(order[0].getCustomerId());

    for(let  i =0; i < cartArray.length; i++){
        let orderDetailDTO =  new OrderDetailDTO();
        orderDetailDTO.setItemCode(cartArray[i].getItemCode());
        orderDetailDTO.setOrderId(orderId);
        orderDetailDTO.setUnitPrice(cartArray[i].getUnitPrice());
        orderDetailDTO.setQty(cartArray[i].getSaleQty());
        orderDetailDTO.setSale(cartArray[i].getPrice());
        orderDetailDTO.setItemProfit(countSaleProfit(cartArray[i].getItemCode())* cartArray[i].getSaleQty() );

        orderDetail.push(orderDetailDTO);

        calculateForItemQty(cartArray[i].getItemCode(), cartArray[i].getSaleQty());



    }

    cartArray.splice(0, cartArray.length);
    addOrderCartRow();
    generateOrderId();
    addOrderRow();
    clearOrderTextField();
    calAllProfit();
    calAllTodayOrder();
    calAllCustomers();
    calAllItems();




})



function calculateCustomerAllItemProfit() {
    let totalProfit =0;
    //let itemQty = parseInt(qty);

    for(let i =0; i < cartArray.length;i++){
        totalProfit = totalProfit +( countSaleProfit(cartArray[i].getItemCode())* cartArray[i].getSaleQty());


    }
    return totalProfit;


}




function calculateForItemQty(itemCode, qty){
    for(let i= 0; i < items.length; i++){
        if(itemCode == items[i].getId()){
            let num = items[i].getQuantity();
            let count = parseInt(num);
            items[i].setQuantity(count - qty);

        }else {

        }
    }
}





function generateOrderId() {
    let index =  order.length;
    let orderId =  order[index-1].getOrderId();
    let text =  orderId.substr(2);
    let num1 = parseInt(text);
    let numGenerate = num1 + 1;
    let nextId = "0-"+numGenerate;
    $("#lblOrderId").text(nextId);



}


function addOrderRow() {

    $("#orderDashBord").empty();
    for (var i =0; i <order.length; i++){
        let newRow = `<tr><td>${order[i].getOrderId()}</td></td><td>${order[i].getCustomerId()}</td><td>${order[i].getSale()}
                </td><td>${order[i].getProfit()}</td><td>${order[i].getDateAndTime()}</td></tr>`;
        $("#orderDashBord").append(newRow);
    }


}




function clearOrderTextField() {
    $("#cusIdOrder").val("");
    $("#cusNameOrder").val("");
    $("#cusAddressOrder").val("");
    $("#cusTelOrder").val("");
    $("#cusEmailOrder").val("");


    $("#allItemTotalFee").val("");


}


function clearOrderTextFieldPart2() {
    $("#itemIdOrder").val("");
    $("#itemNameOrder").val("");
    $("#itemQtyOrder").val("");
    $("#itemPackSizeOrder").val("");
    $("#itemPriceOrder").val("");


    $("#saleItemQty").val("");
    $("#itemTotalPrice").val("");
}







/*======================dashBord=======================*/
function calAllProfit(){

    let allProfit =0;
    for(let i =0; i< order.length; i++){

        let profit =  order[i].getProfit();
        let profitNum  =  parseInt(profit);

        allProfit = allProfit + profitNum;
        console.log("all profit ------ " + allProfit);
    }

    $("#businessProfit").text(allProfit);

}



function calAllTodayOrder() {
    let sum = 0;
    for(let i =0 ; i < order.length;i++){
        sum++
    }

    $("#todayOrders").text(sum);


}



function calAllCustomers(){
    let sum = 0;

    for(let i =0; i < customers.length; i++){
        sum++;
    }


    $("#allCustomers").text(sum);


}



function calAllItems() {
    let sum = 0;
    for(let i =0; i < items.length; i++){
        sum++;
    }


    $("#allItems").text(sum);



}

