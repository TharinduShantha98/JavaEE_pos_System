/*===================================================customer part=============================*/
$("#addCustomer").prop('disabled',true);
$("#updateCustomer").prop('disabled',true);
$("#deleteCustomer").prop('disabled',true);
$("#customerId").val("C00-100");
//$("#customerId").attr('disabled', true);



/* ====================== customer Crud Operation ====================*/
/*-----------------------customer Add part start------ */














$("#addCustomer").click(function () {
    $("#customerTable>tr").off('click');
    $("#updateCustomer").off('click');
    $("#deleteCustomer").off('click');




    let cusId = $("#customerId").val();
    let cusFName = $("#customerFName").val();
    let cusLName = $("#customerLName").val();
    let cusAddress = $("#customerAddress").val();
    let cusEmail = $("#customerEmail").val();
    let cusTelNum = $("#customerTelNum").val();



    let  customer = new  customerDTO();
    customer.setId(cusId);
    customer.setFName(cusFName);
    customer.setLName(cusLName);
    customer.setAddress(cusAddress);
    customer.setEmail(cusEmail);
    customer.setTelNum(cusTelNum);
    if(confirm("Are you sure, you want to add this customer")){

        customers.push(customer);
        addCustomerIdForOrderPart();
        generateCustomerId();
        clearCustomerTextField();
        clearTextFieldStyleForCustomer();
        $("#addCustomer").prop('disabled',true);

    }else{

    }

    console.log(customer);
    addTableRow();
    generateId();
    /*-----------------------customer Add part End ------ */

    tableRowStyle();


    /*-------------------customer Table Row click part start ------ */

    let rowIndex;



    customerTableRowClick();




    /*-------------------customer Table Row click part End ------ */





    /*-------------------customer update part start ------ */

    $("#updateCustomer").click(function (){
        //alert("hello");
        let id  = $("#customerId").val();
        console.log(id);

        if(confirm("Are you sure, you want to update this customer ")){
            for(var j = 0; j < customers.length; j++){
                if(id == customers[j].getId()){
                    customers[j].setFName($("#customerFName").val());
                    customers[j].setLName($("#customerLName").val());
                    customers[j].setAddress($("#customerAddress").val());
                    customers[j].setEmail($("#customerEmail").val());
                    customers[j].setTelNum($("#customerTelNum").val());
                    // console.log("update");
                }

            }

            $("#updateCustomer").prop('disabled',true);
            $("#deleteCustomer").prop('disabled',true);



            addTableRow();
            tableRowStyle();
            clearCustomerTextField();
            generateCustomerId();
            customerTableRowClick();
            clearTextFieldStyleForCustomer();


        }else {

        }





    })

    /*-------------------customer update part End ------ */





    /*-------------------customer delete part Start ------ */


    $("#deleteCustomer").click(function () {

        if(confirm("Are you sure, you want to delete this customer")){
            let id  = $("#customerId").val();
            for(var i = 0; i < customers.length; i++){
                if(id == customers[i].getId()){
                    customers.splice(i,1);
                }

            }

            $("#updateCustomer").prop('disabled',true);
            $("#deleteCustomer").prop('disabled',true);



            addTableRow();
            tableRowStyle();
            clearCustomerTextField();
            generateCustomerId();
            customerTableRowClick();
            clearTextFieldStyleForCustomer();

        }else{

        }


    })






})



function customerTableRowClick(){

    $("#customerTable>tr").click(function (){
        if(confirm("Are you sure, you want to see this row")){
            let cusId = $(this).children().eq(0).text();
            for(let i =0; i < customers.length; i++){
                if(cusId == customers[i].getId()){
                    $("#customerId").val(customers[i].getId());
                    $("#customerFName").val(customers[i].getFName());
                    $("#customerLName").val(customers[i].getLName());
                    $("#customerAddress").val(customers[i].getAddress());
                    $("#customerEmail").val(customers[i].getEmail());
                    $("#customerTelNum").val(customers[i].getTelNum());

                }
            }
            rowIndex  = this.rowIndex;
            // console.log("rowIndex " + rowIndex);

            $("#updateCustomer").prop('disabled',false);
            $("#deleteCustomer").prop('disabled',false);

        }else {

        }



    })




}


function addTableRow(){
    $("#customerTable").empty();
    for (var i =0; i < customers.length; i++){
        let newRow = `<tr><td>${customers[i].getId()}</td></td><td>${customers[i].getFName()}</td><td>${customers[i].getLName()}
                </td><td>${customers[i].getAddress()}</td><td>${customers[i].getEmail()}</td><td>${customers[i].getTelNum()}</td></tr>`;
        $("#customerTable").append(newRow);
    }


}

function tableRowStyle(){
    $("#customerTable>tr").css("background-color", "#54a0ff");

    let table = document.getElementById("customerTable");
    for ( var i =0; i< table.rows.length ; i++  ){
        $("#customerTable").children().eq(i).css("font-weight","bold");

    }

    $("#customerTable>tr").hover(function () {
        $(this).css("background-color","#95a5a6" );
    },function () {
        $(this).css("background-color", "#54a0ff");
    })
}





function generateId(){

    let index = customers.length;
    let gId  = customers[index-1].getId();
    let text =  gId.substr(4,7);
    let number =  Number(text);
    let nextIdNum =  number+ 1;
    let nextId =  "C00-"+nextIdNum;
    $("#customerId").val(nextId);

}


$("#CustomerSearch").keyup(function (event) {

    if(event.keyCode == 13){
        $("#btnCustomerSearch").focus();
    }

})


$("#btnCustomerSearch").click(function () {
    let searchItemCode = $("#CustomerSearch").val();
    for(var k=0; k < customers.length; k++){
        if(customers[k].getId() == searchItemCode){
            $("#customerId").val(customers[k].getId());
            $("#customerFName").val(customers[k].getFName());
            $("#customerLName").val(customers[k].getLName());
            $("#customerAddress").val(customers[k].getAddress());
            $("#cusEmail").val(customers[k].getEmail());
            $("#customerTelNum").val(customers[k].getTelNum());

        }else{
            /* alert("this item code not in dataBase");*/
        }

    }

})



$("#clearTextCustomer").click(function () {
    generateCustomerId();
    clearCustomerTextField();
})


function generateCustomerId(){

    let index = customers.length;
    let gId  = customers[index-1].getId();
    let text =  gId.substr(4,7);
    let number =  Number(text);
    let nextIdNum =  number+ 1;
    let nextId =  "C00-"+nextIdNum;
    $("#customerId").val(nextId);

}





function clearCustomerTextField(){
    //$("#itemCode").val("");
    //  $("#customerId").val("");
    $("#customerFName").val("");
    $("#customerLName").val("");
    $("#customerAddress").val("");
    $("#cusEmail").val("");
    $("#customerTelNum").val("");
    $("#customerEmail").val("");



}







/* -------------------customer validation part start------------------*/



  let customerIdRegx = /^(C00)[-][0-9]{3,9}$/
  let cusFNameRegx = /^[A-z]{3,15}$/;
  let cusLNameRegx = /^[A-z]{3,15}$/;
  let cusAddressRegx = /^(No-)[1-9]{1,9}(\s)[A-z]{1,15}|[A-z]{0,20}$/;
  let cusEmailRegx = /^[A-z0-9$#^&*]{0,30}(@)(gmail.com)$/;
  let cusTelNumRegx = /^[0-9]{10}$/;



$('#customerId,#customerFName,#customerLName,#customerAddress,#customerTelNum,#customerEmail').keydown(function (eventOb) {
    if (eventOb.key == "Tab") {
        eventOb.preventDefault();

    }
})



  function validationCustomer(testRegex, event,id,nextId,correctRegex){

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


  $("#customerId").keyup(function (event) {

      let code = $("#customerId").val();
      let boolean = validationCustomer(code,event,"#customerId","#customerFName",customerIdRegx);

      if(boolean){
          $("#validationC6").text("");


      }else{
          $("#validationC6").text("input format(C00-***)");
          $("#validationC6").css("color", "red");
          $("#validationC6").css("font-weight", "bold");

      }

      checkButtonCustomerAdd();
  })



  $("#customerFName").keyup(function (event) {

      let code = $("#customerFName").val();
      let boolean = validationCustomer(code,event,"#customerFName","#customerLName",cusFNameRegx);

      if(boolean){
          $("#validationC1").text("");


      }else{
          $("#validationC1").text("by A to z characters only one word");
          $("#validationC1").css("color", "red");
          $("#validationC1").css("font-weight", "bold");

      }

      checkButtonCustomerAdd();
  })

  $("#customerLName").keyup(function (event) {

      let code = $("#customerLName").val();
      let boolean = validationCustomer(code,event,"#customerLName","#customerAddress",cusLNameRegx);

      if(boolean){
          $("#validationC2").text("");


      }else{
          $("#validationC2").text("by A to z characters only one word");
          $("#validationC2").css("color", "red");
          $("#validationC2").css("font-weight", "bold");

      }

      checkButtonCustomerAdd();
  })

  $("#customerAddress").keyup(function (event) {
      let code = $("#customerAddress").val();
      let boolean = validationCustomer(code,event,"#customerAddress","#customerTelNum",cusAddressRegx);

      if(boolean){
          $("#validationC3").text("");

      }else{
          $("#validationC3").text(" as a no-1 ********");
          $("#validationC3").css("color", "red");
          $("#validationC3").css("font-weight", "bold");

      }

      checkButtonCustomerAdd();

  })
  $("#customerEmail").keyup(function (event) {

      let code = $("#customerEmail").val();
      let boolean = validationCustomer(code,event,"#customerEmail","#addCustomer",cusEmailRegx);

      if(boolean){
          $("#validationC4").text("");


      }else{
          $("#validationC4").text("please input last text as a @gmail.com");
          $("#validationC4").css("color", "red");
          $("#validationC4").css("font-weight", "bold");

      }

      checkButtonCustomerAdd();
  })
  $("#customerTelNum").keyup(function (event) {

      let code = $("#customerTelNum").val();
      let boolean = validationCustomer(code,event,"#customerTelNum","#customerEmail",cusTelNumRegx);

      if(boolean){
          $("#validationC5").text("");
          $("#addCustomer").prop('disabled', false);

      }else{
          $("#validationC5").text("please input only numbers");
          $("#validationC5").css("color", "red");
          $("#validationC5").css("font-weight", "bold");

      }


      checkButtonCustomerAdd();
  })


  function checkButtonCustomerAdd() {


      if(($("#CustomerFName").val() != "") &&  ($("#customerLName").val() != "") &&  ($("#customerAddress").val() != "") &&
          ($("#customerEmail").val() != "") &&  ($("#customerTelNum").val() != "")){
          console.log("false");
          $("#addCustomer").prop('disabled',false);

      }else {
          $("#addCustomer").prop('disabled',true);
          console.log("true");
      }
  }



  function checkButtonCustomerAdd() {
      if(($("#validationC1").text() == "") &&  ($("#validationC2").text() == "") &&  ($("#validationC3").text() == "") &&
          ($("#validationC4").text() == "") &&  ($("#validationC5").text() == "") && ($("#validationC6").text() == "") ){
          console.log("false");
          $("#addCustomer").prop('disabled',false);

      }else {
          $("#addCustomer").prop('disabled',true);
          console.log("true");
      }
  }







function clearTextFieldStyleForCustomer(){

    let textFieldCustomer  = [];

    textFieldCustomer.push("#customerId");
    textFieldCustomer.push("#customerFName");
    textFieldCustomer.push("#customerLName");
    textFieldCustomer.push("#customerAddress");
    textFieldCustomer.push("#customerEmail");
    textFieldCustomer.push("#customerTelNum");



    for(let i=0; i < textFieldCustomer.length; i++){
        $(textFieldCustomer[i]).css("border", "1px solid #ced4da");
        $(textFieldCustomer[i]).css("box-shadow", "none");


    }

}












/* -------------------customer validation part end------------------*/

var timeId;
var position = 1;
var test =  $("#customerManage").text();

console.log(test);
function customerManegeFun() {

    var output =test.substr(0,position);
    position++;


    $("#customerManage").text(output);

    if(position == test.length){
        position = 1;
    }


}


timeId = setInterval(customerManegeFun,100);
/* console.log(timeId);*/

