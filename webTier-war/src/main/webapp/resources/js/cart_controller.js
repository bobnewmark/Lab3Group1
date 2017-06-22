'use strict';
App.controller('CartController', ['$scope', 'ItemService', function ($scope, ItemService) {
    var self = this;
    var REST_SERVICE_URI = 'http://localhost:7001/laba/showCart/';

    self.item = {
        id: null, name: '', objectType: {id: null},
        references: [
            {
                id: null, name: '', object: {id: null},
                refObject: {
                    id: null, name: '',
                    mapParameters: {
                        price: {
                            id: null, attribute: {id: null, name: '', unique: null},
                            value: ''
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
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    function getCount(i) {
        var iCount = iCount || 0;
        for (var item in this.items) {
            for (var level1 in this.items[item]) {
                for (var level2 in this.items[item][level1]) {
                    for (var level3 in this.items[item][level1][level2]) {
                        if (this.items[item][level1][level2][level3] == i) {
                            iCount++;
                        }
                    }
                }
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
            console.log("something's wrong");
        }

        ItemService.createItem(self.checkoutMap, 'checkout')
            .then(
                fetchAllItems,
                function(errResponse){
                    console.error('Error while checkout');
                }
            )
    }

    function deleteItem(id, cartId) {
        ItemService.deleteItem(id, REST_SERVICE_URI)
            .then(
                fetchAllItems,
                function (errResponse) {
                    console.error('Error while deleting User');
                }
            );
    }

    function remove(id) {
        if (self.item.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteItem(id);
    }
}])

    .filter('unique', function () {
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