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


    $.getJSON(url).then(function (data) {
        console.log(data[query]);
    })

    // $.get("https://api.nal.usda.gov/ndb/search/?format=json&q=banana&max=25&ds=Standard%20Reference&offset=0&api_key=rWKfuG6YjQU9h0WMNksynapfFqcr3BJWK5giCqRQ", function (res,status) {
    //     var body = '';
    //
    //     alert("hello "+ res+ status);
    //
    //     // res.on('data', function (chunk) {
    //     //     body += chunk;
    //     // });
    //
    //     // res.on('end', function () {
    //     //     try {
    //     //         var jsonObj = JSON.parse(body);
    //     //
    //     //         var val = jsonObj[query];
    //     //         if (val) {
    //     //             var total = val * amount;
    //     //             cb(null, Math.round(total * 100) / 100);
    //     //         } else {
    //     //             var err = new Error("Value not found for " + query);
    //     //             console.log(err);
    //     //             cb(err);
    //     //         }
    //     //     } catch (e) {
    //     //         console.log("Parse error: ", e);
    //     //         cb(e);
    //     //     }
    //     // });
    // });
}
