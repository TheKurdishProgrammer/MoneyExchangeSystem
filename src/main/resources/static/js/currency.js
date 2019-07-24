$(document).ready(function() {



    $("#receivingCurrency").on('change',function () {

       var receivingCurrency = $(this).children("option").filter(":selected").text();
       var sendingCurrency = $("#sendingCurrency").children("option").filter(":selected").text();

       var sendingAmount = $("#sendingAmount").val();


        console.log(receivingCurrency + "\n"+sendingCurrency);

        convertCurrency(sendingAmount, sendingCurrency, receivingCurrency, function (err, amount) {
            $('#receivedMoney').attr("placeholder","Received Money: "+amount);
        });

    });


});



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


                 if (val) {
                     var total = val * amount;
                     console.log(total);

                     cb(null, Math.round(total * 100) / 100);
                 } else {
                     var err = new Error("Value not found for " + query);
                     console.log(err);
                     cb(err);
                 }
     });
}
