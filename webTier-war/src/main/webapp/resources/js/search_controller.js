'use strict';
App.controller('SearchController', ['$scope', 'ItemService', '$location', function($scope, ItemService, $location) {
    var self = this;
    //var detailId = $location.path().substr($location.path().lastIndexOf('/') + 1 );
    var keyword = $location.search()['keyword'];
    console.log("keyword to search for: " + keyword);
    var URI = 'http://localhost:7001/laba/request/' + keyword;

    self.item = {id:null, name:'', objectType:{id: null}, parameters:[
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}}]};
    self.items = [];
    self.buy = buy;
    //self.buyR = buyR;

    fetchResult();

    function fetchResult(){
        ItemService.fetchAllItems(URI)
            .then(
                function(d) {
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching detailed info');
                }
            );
    }

    // function buy(id, quantity) {
    //     $.ajax({
    //         contentType: "application/json; charset=utf-8",
    //         url: 'buy',
    //         data: ({itemId : id, quantity : quantity}),
    //         success: function() {
    //             $scope.updateIndex();
    //             $location.path('/laba/shop');
    //         }
    //     });
    // }

    function buy(id) {
        console.log('buying from search: ' + id);
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: 'http://localhost:7001/laba/addToCart',
            data: ({itemId : id}),
            success: function() {
                $scope.updateIndex();
            }
        });
    }

    function fetchRelated(){
        ItemService.fetchAllItems(URI_R)
            .then(
                function(d) {
                    self.related = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }


}]);