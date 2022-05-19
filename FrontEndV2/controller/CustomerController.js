/*===================================================customer part=============================*/
$("#addCustomer").prop('disabled',true);
$("#updateCustomer").prop('disabled',true);
$("#deleteCustomer").prop('disabled',true);

//$("#customerId").attr('disabled', true);


getAllCustomer();
function getAllCustomer() {
    console.log("get all cutomer")

    $("#customerTable").empty();

    $.ajax({
        url: "http://localhost:8080/posSystemV2/customer?option=GETALL",
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
        url:"http://localhost:8080/posSystemV2/customer?option=SEARCH&customerId="+ cusId,
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
        url: "http://localhost:8080/posSystemV2/customer",
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


            $("#updateCustomer").prop('disabled',false);
            $("#deleteCustomer").prop('disabled',false);

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
        url:"http://localhost:8080/posSystemV2/customer",
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
        url: "http://localhost:8080/posSystemV2/customer?cusId=" + customerId,
        method: "DELETE",


        success: function (resp) {
            if(resp.status == 200){
                alert(resp.message);
                getAllCustomer();
                getCustomerId();

            }else if(resp.status == 500){
                alert(resp.message);
            }

        }
    })



})


getCustomerId();

function getCustomerId() {
    $.ajax({
        url: "http://localhost:8080/posSystemV2/customer?option=GET_CUSTOMER_CODE",
        method: "GET",

        success:function (resp) {
            if(resp.status == 200){
                console.log(resp.orderId)
                $("#customerId").val(resp.customerCode);
            }
        }
    })
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








$("#CustomerSearch").keyup(function (event) {

    if(event.keyCode == 13){
        $("#btnCustomerSearch").focus();
    }

})




$("#clearTextCustomer").click(function () {
    clearCustomerTextField();
    clearTextFieldStyleForCustomer();
    getCustomerId();
})








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



  let customerIdRegx = /^(C)[-][0-9]{3,9}$/
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

