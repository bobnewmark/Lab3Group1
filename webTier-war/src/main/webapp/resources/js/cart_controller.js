"use strict";
App.controller("CartController", ["$scope", "ItemService", function ($scope, ItemService) {
    var self = this;
    var REST_SERVICE_URI = "/laba/showCart/";

    self.item = {
        id: null, name: "", objectType: {id: null},
        references: [
            {
                id: null, name: "", object: {id: null},
                refObject: {
                    id: null, name: "",
                    mapParameters: {
                        price: {
                            id: null, attribute: {id: null, name: "", unique: null},
                            value: ""
                        }
                    }
                }
            }
        ],
        parameters: []
    };

    self.items = [];
    self.checkoutMap = {};
    self.remove = remove;
    self.getCount = getCount;
    self.total = total;
    self.checkout = checkout;

    fetchAllItems();

    function fetchAllItems() {
        ItemService.fetchAllItems(REST_SERVICE_URI)
            .then(
                function (d) {
                    self.items = d;
                    $scope.updateIndex();
                }
            );
    }

    function getCount(i) {
        var iCount = iCount || 0;
            for (var y = 0; y < this.items["references"].length; y++) {
                if (this.items["references"][y]["refObject"]["id"] === i) {
                    iCount++;
                }
            }
        return iCount;
    }

    function total() {
        try {
            return self.items.references.reduce(function (acc, item) {
                if ((!item.cost) || (!item.qty)) {
                    return acc;
                }
                return acc + (item.cost * item.qty);
            }, 0);
        } catch (err) {
            return 0;
        }
    }

    function checkout() {
        try {
            self.items.references.reduce(function (acc, item) {
                self.checkoutMap[item.num] = item.qty;
            }, 0);
        } catch (err) {
        }

        ItemService.createItem(self.checkoutMap, "checkout")
            .then(
                function (d) {
                    if (d.toString().length > 0) {
                        $scope.tooMuch = 1;
                    } else {
                        $scope.tooMuch = 0;
                    }
                    fetchAllItems();
                }
            );
    }

    function deleteItem(id) {
        ItemService.deleteItem(id, REST_SERVICE_URI)
            .then(
                fetchAllItems
            );
    }

    function remove(id) {
        deleteItem(id);
    }
}])

    .filter("unique", function () {
        return function (collection, keyname) {
            var output = [],
                keys = [];

            angular.forEach(collection, function (item) {
                var key = item.refObject[keyname];
                if (keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                }
            });
            return output;
        };
    });