"use strict";
App.controller("SearchController", ["$scope", "ItemService", "$location", function ($scope, ItemService, $location) {
    var self = this;
    var keyword = $location.search()["keyword"];
    var URI = "http://localhost:7001/laba/request/" + keyword;

    self.item = {
        id: null, name: "", objectType: {id: null}, parameters: [
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}},
            {id: null, value: "", attribute: {id: null, name: ""}, object: {id: null}}]
    };
    self.items = [];
    self.buy = buy;

    fetchResult();

    function fetchResult() {
        ItemService.fetchAllItems(URI)
            .then(
                function (d) {
                    self.items = d;
                }
            );
    }

    function buy(id) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:7001/laba/addToCart",
            data: ({itemId: id}),
            success () {
                $scope.updateIndex();
            }
        });
    }

    function fetchRelated() {
        ItemService.fetchAllItems(URI_R)
            .then(
                function (d) {
                    self.related = d;
                }
            );
    }
}]);