$(document).ready(function() {


    $('#exchangeRate').on('input',function (e) {

        var value = $('#exchangeRate').val();
        var sendingAmount = $("#sendingAmount").val();
        console.log(value+"  "+ sendingAmount);

        currencyConverter(value,sendingAmount,function (err,amount) {
            $('#receivedMoney').attr("placeholder","Received Money: "+amount);

        });

    });


    $("#receivingCurrency").on('change',function () {

       var receivingCurrency = $(this).children("option").filter(":selected").text();
       var sendingCurrency = $("#sendingCurrency").children("option").filter(":selected").text();

       if (receivingCurrency === sendingCurrency)
       {

           $('#exchangeRate').prop("disabled",true);

           $('#receivedMoney').val($('#sendingAmount').val());

           return;
       }


        $('#exchangeRate').prop("disabled",false);



        var sendingAmount = $("#sendingAmount").val();

        console.log(receivingCurrency + "\n"+sendingCurrency);

        convertCurrency(sendingAmount, sendingCurrency, receivingCurrency, function (err, amount) {
            $('#receivedMoney').attr("placeholder","Received Money: "+amount);
        });

    });


});



function currencyConverter(val,amount,cb) {

    if (val) {
        var total = val * amount;
        console.log(val +" "+ amount);

        cb(null, Math.round(total * 100) / 100);
    } else {
        var err = new Error("Value not found for " + query);
        console.log(err);
        cb(err);
    }
}

function convertCurrency(amount, fromCurrency, toCurrency, cb) {
    var apiKey = '8bcbf013bc01bdd1bc96';


    fromCurrency = encodeURIComponent(fromCurrency);
    toCurrency = encodeURIComponent(toCurrency);
    var query = fromCurrency + '_' + toCurrency;

    var url = 'https://free.currconv.com/api/v7/convert?q='
        + query + '&compact=ultra&apiKey=' + apiKey;

    console.log(url);

    // var https = require('https');


    $.getJSON(url).then(function (data) {

        var val = data[query];

        $('#exchangeRate').attr("placeholder","Exchange Rate : "+val);


        currencyConverter(val,amount,cb);

     });
}
