"use strict";
App.controller("DetailsController", ["$scope", "ItemService", "$location", function($scope, ItemService, $location) {
    var self = this;
    var detailId = $location.path().substr($location.path().lastIndexOf("/") + 1 );
    var URI = "http://localhost:7001/laba/detailed/" + detailId;
    var URI_R = "http://localhost:7001/laba/related/" + detailId;

    self.items = {id:null, name:"", objectType:{id: null, name:""}, parameters:[
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}},
        {id:null, value:"", attribute:{id:null, name:""}, object:{id:null}}]};
    self.related = [];
    self.buy = buy;
    self.buyR = buyR;

    fetchInfo();

    function fetchInfo(){
        ItemService.fetchAllItems(URI)
            .then(
                function(d) {
                    self.items = d;
                    fetchRelated();
                }
            );
    }

    function buy(id, quantity) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: "buy",
            data: ({itemId : id, quantity : quantity}),
            success() {
                $scope.updateIndex();
                //$location.path("/laba/shop");
            }
        });
    }

    function buyR(id) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:7001/laba/addToCart",
            data: ({itemId : id}),
            success() {
                $scope.updateIndex();
            }
        });
    }

    function fetchRelated(){
        ItemService.fetchAllItems(URI_R)
            .then(
                function(d) {
                    self.related = d;
                }
            );
    }
}]);
