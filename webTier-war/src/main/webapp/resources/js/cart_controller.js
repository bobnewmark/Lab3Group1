'use strict';
App.controller('CartController', ['$scope', 'ItemService', function ($scope, ItemService) {
    var self = this;
    var REST_SERVICE_URI = 'http://localhost:7001/laba/showCart/';

    self.item = {id:null, name:'', objectType:{id: null},
        references:[
            {id:null, name:'', object:{id:null},
             refObject:{id:null, name:'',
             mapParameters: {price: {id: null, attribute: {id: null, name: '', unique: null},
                                     value: ''}}} }
            ],
        parameters:[]};

    self.items =[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.show = show;
    self.buy = buy;
    self.getCount = getCount;
    self.total = total;

    fetchAllItems();

    function fetchAllItems() {
        ItemService.fetchAllItems(REST_SERVICE_URI)
            .then(
                function (d) {
                    self.items = d;
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


    function fetchCart() {
        ItemService.fetchAllItems(REST_SERVICE_URI_CART)
            .then(
                function (d) {
                    self.items = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    function buy(id) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: 'addToCart',
            data: ({itemId: id}),
            success: function (data) {
                var elem = document.getElementById('#cartNum');
                console.log(data);
                $('#cartNum').html(data);
            }
        });
    }

    function showItem(id) {
        ItemService.showItem(id)
            .then(
                function (d) {
                    self.items = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Users');
                }
            );
    }

    function createItem(item) {
        ItemService.createItem(item)
            .then(
                fetchAllItems,
                function (errResponse) {
                    console.error('Error while creating Item');
                }
            );
    }

    function updateItem(item, id) {
        ItemService.updateItem(item, id)
            .then(
                fetchAllItems,
                function (errResponse) {
                    console.error('Error while updating User');
                }
            );
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

    function show(id) {
        showItem(id);
    }

    function submit() {
        if (self.item.id === null) {
            console.log('Saving New Item', self.item);
            createItem(self.item);
        } else {
            updateItem(self.item, self.item.id);
            console.log('Item updated with id ', self.item.id);
        }
        angular.element(document.querySelector('#editModal')).modal('hide');
        reset();
    }

    function edit(id) {
        console.log('id to be edited', id);
        for (var i = 0; i < self.items.length; i++) {
            if (self.items[i].id === id) {
                self.item = angular.copy(self.items[i]);
                break;
            }
        }
    }

    function remove(id) {
        console.log('id to be deleted', id);
        if (self.item.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteItem(id);
    }


    function reset() {
        self.item = {id: null, username: '', address: '', email: ''};
        $scope.myForm.$setPristine(); //reset Form
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