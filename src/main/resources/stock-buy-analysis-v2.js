var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http,$filter) {

    function getBuyList() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getBuyListV2"
        });
    }

    function getAll() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getHistoricalListV2"
        });
    }

    function getColumns() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getColumn"
        });
    }

    function updateColumnScore(dbColumns, score){
        return $http({
            method: 'GET',
            url: "http://localhost:8080/updateColumnScore?fields="+dbColumns + "&scorev2=" + score
        });
    }

    let largestLength=14;

    getBuyList().then(function(response1){
        let buyList= response1.data;

        getAll().then(function(response2){
           let historicalList= response2.data;

           run(buyList, historicalList);

        });

    });

    function run(buyList, historicalList){
        let filters= {};

        getColumns().then(function (response3){
            let dbColumns = response3.data.data.split(',');
            let result=[];

            angular.forEach(dbColumns, function (column) {
                result = $filter('orderBy')(buyList, column);

                angular.forEach(result, function (value){
                    value.score=0;
                });

                for (let i = 0; i < result.length; i++) {
                    if (i <= 9) {
                        result[i].score += 1;
                    } else if (i <= 18) {
                        result[i].score += 2;
                    } else if (i <= 27) {
                        result[i].score += 3;
                    } else if (i <= 36) {
                        result[i].score += 4;
                    } else if (i <= 45) {
                        result[i].score += 5;
                    } else if (i <= 54) {
                        result[i].score += 5;
                    } else if (i <= 63) {
                        result[i].score += 4;
                    } else if (i <= 72) {
                        result[i].score += 3;
                    } else if (i <= 81) {
                        result[i].score += 2;
                    } else if (i <= 90) {
                        result[i].score += 1;
                    }
                }
            });

            result = $filter('orderBy')(result, "score", true);

            for(let m= 1; m < result.length; m++){
                let topTier = result.slice(0, result.length-m);

                for (let i = 0; i < dbColumns.length; i++) {

                    let min= null ;
                    let max= null;

                    for (let j = 0; j < topTier.length; j++) {
                        var current = Number(topTier[j][dbColumns[i]]);

                        if (min == null) {
                            min = current;
                        } else if (current < min) {
                            min = current;
                        }

                        if (max == null) {
                            max = current;
                        } else if (current > max) {
                            max = current;
                        }

                    }

                    filters[dbColumns[i]] = {};
                    filters[dbColumns[i]].min = min;
                    filters[dbColumns[i]].max = max;

                }

                let backTest = [];

                for(let i=0; i<historicalList.length; i++){

                    if(historicalList[i].close < 0.3){
                        //skip price < 0.3
                        continue;
                    }

                    let isFullFilled = true;

                    for (let j = 0; j < dbColumns.length; j++) {
                        if (historicalList[i][dbColumns[j]] >= filters[dbColumns[j]].min &&
                            historicalList[i][dbColumns[j]] <= filters[dbColumns[j]].max) {

                        } else {
                            isFullFilled = false;
                            break;
                        }
                    }

                    if (isFullFilled) {
                        backTest.push(historicalList[i]);
                    }
                }

                let score= topTier.length / (backTest.length) * 100;

                if(score >= 80){
                    if(topTier.length >= largestLength) {
                        console.log(dbColumns + " : topTier length: " + topTier.length + " Back Test length: " + backTest.length + " Score: " + score);
                        console.log(filters);
                        largestLength= topTier.length;
                    }
                    break;
                }
            }

            setTimeout(function(){
                run(buyList, historicalList);
            }, 5000);

        });
    }
});