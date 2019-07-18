$(document).ready(function() {



    $("#receivingCurrency").on('change',function () {

       var receivingCurrency = $(this).children("option").filter(":selected").text();
       var sendingCurrency = $("#sendingCurrency").children("option").filter(":selected").text();

       var sendingAmount = $("#sendingAmount").val();


        console.log("got the values");

        convertCurrency(sendingAmount, sendingCurrency, receivingCurrency, function (err, amount) {
            console.log(amount);
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



    $.get(url, function (res,status) {
        var body = '';

        alert("hello "+ JSON.parse(res)+ status);

        res.on('data', function (chunk) {
            body += chunk;
        });

        res.on('end', function () {
            try {
                var jsonObj = JSON.parse(body);

                var val = jsonObj[query];
                if (val) {
                    var total = val * amount;
                    cb(null, Math.round(total * 100) / 100);
                } else {
                    var err = new Error("Value not found for " + query);
                    console.log(err);
                    cb(err);
                }
            } catch (e) {
                console.log("Parse error: ", e);
                cb(e);
            }
        });
    });
}
