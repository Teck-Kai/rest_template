var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http,$filter) {

    // let dbColumns = ["cci20","recommendall","aroondown","aroonup","chaikinmoneyflow","adx","perf3m","perf6m",
    //     "ao","bbpower","macdmacd","macdsignal","mom","moneyflow","perf1m","recommendma","adxdin","recommendother",
    //     "adxdip","relativevolume10dcalc","stochd","stochk","stochrsik","stochrsid","uo","volatilityd","volatilitym","volatilityw",
    //     "vwma","perfytd"];

    function getBuyList() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getBuyList"
        });
    }

    function getAll() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getAll"
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
            url: "http://localhost:8080/updateColumnScore?fields="+dbColumns + "&score=" + score
        });
    }

    let highestScore= {
        fields: null,
        score: 0
    }

    run();

    function run(){
        getBuyList().then(function(response){
            var result= response.data;

            angular.forEach(result, function (value){
                value.score=0;
            });

            let filters= {};
            let existedStock= [];

            getColumns().then(function (response) {

                if(!response.data.data){
                    return;
                }

                let dbColumns = response.data.data.split(',');

                angular.forEach(dbColumns, function (column) {
                    result = $filter('orderBy')(result, column);

                    for (let i = 0; i < result.length; i++) {
                        if (i <= 180) {
                            result[i].score += 1;
                        } else if (i <= 360) {
                            result[i].score += 2;
                        } else if (i <= 540) {
                            result[i].score += 3;
                        } else if (i <= 720) {
                            result[i].score += 4;
                        } else if (i <= 900) {
                            result[i].score += 5;
                        } else if (i <= 1080) {
                            result[i].score += 5;
                        } else if (i <= 1260) {
                            result[i].score += 4;
                        } else if (i <= 1440) {
                            result[i].score += 3;
                        } else if (i <= 1620) {
                            result[i].score += 2;
                        } else if (i <= 1800) {
                            result[i].score += 1;
                        }
                    }
                });

                result = $filter('orderBy')(result, "score", true);

                let topTier = result.slice(0, 80);

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

                        existedStock.push(topTier[j].name);
                    }

                    filters[dbColumns[i]] = {};
                    filters[dbColumns[i]].min = min;
                    filters[dbColumns[i]].max = max;

                }

                var backTest = [];

                getAll().then(function (fullResponse) {
                    let fullData = fullResponse.data;

                    angular.forEach(fullData, function (value, key) {

                        if(existedStock.indexOf(key) <= -1) {
                            for (let i = 0; i < value.length; i++) {

                                if(value[i].close < 0.3){
                                    //skip price < 0.3
                                    continue;
                                }

                                let isFullFilled = true;

                                for (let j = 0; j < dbColumns.length; j++) {
                                    if (value[i][dbColumns[j]] >= filters[dbColumns[j]].min &&
                                        value[i][dbColumns[j]] <= filters[dbColumns[j]].max) {

                                    } else {
                                        isFullFilled = false;
                                        break;
                                    }
                                }

                                if (isFullFilled) {
                                    backTest.push(value[i]);
                                    i= value.length;
                                }
                            }
                        }
                    });

                    let score= topTier.length / (backTest.length + topTier.length) * 100;

                    if(score > highestScore.score){
                        console.log("THERE IS THE HIGHEST SCORE.");

                        highestScore= {
                            fields: dbColumns,
                            score: score
                        };
                    }

                    console.log(dbColumns + " : " + score);

                    updateColumnScore(dbColumns, score);

                    //run();
                });
            });
        });
    }
});