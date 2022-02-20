var app = angular.module('myApp', []);

app.controller('myCtrl', function ($scope, $http) {

    function getAll() {
        return $http({
            method: 'GET',
            url: "http://localhost:8080/getAll"
        });
    }

    function saveBuyList(model){
        return $http({
            method: 'POST',
            url: "http://localhost:8080/saveBuyList",
            data: model
        });
    }

    function saveSellList(model){
        return $http({
            method: 'POST',
            url: "http://localhost:8080/saveSellList",
            data: model
        });
    }

    var buyList=[];
    var sellList=[];

    getAll().then(function(response){
        let result= response.data;

        angular.forEach(result, function(value, key){
            for(let i=0; i <value.length; i++){
                if(value[i].close < 0.3){
                    //skip price < 0.3
                    continue;
                }

                var isProfit5Per= false;

                for(let j= i + 1 ; j<value.length; j++){
                   if(value[i].close > value[j].close){
                       break;
                   }

                   if(isProfit5Per){
                       if(value[j].close < value[j-1].close){
                           buyList.push(value[i]);
                           sellList.push(value[j-1]);
                           i= j;
                           break;
                       }
                   }else if(!isProfit5Per && (value[j].close - value[i].close)/value[i].close >= 0.05){
                       isProfit5Per= true;
                   }
               }
           }
        });

        saveBuyList(buyList);
        saveSellList(sellList);
    })
});