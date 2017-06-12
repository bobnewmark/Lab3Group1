'use strict';
App.controller('ItemController', ['$scope', 'ItemService', '$location', function($scope, ItemService, $location) {
    var self = this;
    var REST_SERVICE_URI = 'http://localhost:7001/laba/phone/';
    if($location.absUrl()=== 'http://localhost:7001/laba/shop'){
        REST_SERVICE_URI = 'http://localhost:7001/laba/products';
    }else if($location.absUrl()=== 'http://localhost:7001/laba/showCart'){
        REST_SERVICE_URI = 'http://localhost:7001/laba/showCart';
    }
    console.log('REST_SERVICE_URI '+REST_SERVICE_URI);
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
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.show = show;
    self.buy = buy;

    function fetchAllItems(){
        ItemService.fetchAllItems(REST_SERVICE_URI)
            .then(
                function(d) {
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    fetchAllItems();
    function buy(id) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: 'addToCart',
            data: ({itemId : id}),
            success: function(data) {
                var elem = document.getElementById('#cartNum');
                console.log(data);
                $('#cartNum').html(data);
            }
        });
    }

    function showItem(id) {
        ItemService.showItem(id, REST_SERVICE_URI)
            .then(
                function(d) {
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function createItem(item){
        ItemService.createItem(item, REST_SERVICE_URI)
            .then(
                fetchAllItems,
                function(errResponse){
                    console.error('Error while creating Item');
                }
            );
    }

    function updateItem(item, id){
        ItemService.updateItem(item, id, REST_SERVICE_URI)
            .then(
                fetchAllItems,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteItem(id){
        ItemService.deleteItem(id, REST_SERVICE_URI)
            .then(
                fetchAllItems,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function show(id) {
        showItem(id);
    }

    function submit() {
        if(self.item.id===null){
            console.log('Saving New Item', self.item);
            createItem(self.item);
        }else{
            updateItem(self.item, self.item.id);
            console.log('Item updated with id ', self.item.id);
        }
        angular.element(document.querySelector('#editModal')).modal('hide');
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.items.length; i++){
            if(self.items[i].id === id) {
                self.item = angular.copy(self.items[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.item.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteItem(id);
    }


    function reset(){
        self.item={id:null, name:'', objectType:{id: null}, parameters:[
            {id:null, value:'', attribute:{id:null, name:'name'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'price'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'icon'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'icon2'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'icon3'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'OS'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'diagonal'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'rating'}, object:{id:null}},
            {id:null, value:'', attribute:{id:null, name:'quantity'}, object:{id:null}}]};
        $scope.myForm.$setPristine(); //reset Form
    }


}]).directive("owlCarousel", function($window) {
    return {
        restrict: 'E',
        transclude: false,
        link: function (scope) {
            scope.initCarousel = function(element) {
                var defaultOptions = {};
                var options = {
                    autoplay: false,
                    autoplayTimeout: 2500,
                    loop: false, nav: true,
                    responsiveClass: true,
                    margin: 30,
                    responsive: {
                        0: {items: 1},
                        640: {items: 2},
                        1000: {items: 3},
                        1180: {items: 4}
                    }
                };
                var customOptions = scope.$eval($(element).attr('data-options'));
                // combine the two options objects
                for(var key in customOptions) {
                    defaultOptions[key] = customOptions[key];
                }
                $(element).owlCarousel(options);
            };
            scope.destroy = function(element){
                $(element).data('owlCarousel').destroy();
            };

        }
    };
})
    .directive('owlCarouselItem', [function() {
        return {
            restrict: 'A',
            transclude: false,
            link: function(scope, element) {
                // wait for the last item in the ng-repeat then call init
                scope.$watch("item", function() {
                    if($(element.parent()).data('owlCarousel') === undefined){
                        if(scope.$last) {
                            scope.initCarousel(element.parent());
                        }
                    }else{
                        scope.destroy(element.parent());
                        /*scope.initCarousel(element.parent());*/
                        var queryResult = element[0].querySelector('.owl-stage:last-child');
                        console.log("2");
                        var stage = angular.element(queryResult);
                        //stage.addClass('aaaaaaaaaaaaaaaaaaaaa');
                        console.log(stage.hasClass('active'));
                        //stage.remove(stage);
                    }
                });
            }
        };
    }]);
