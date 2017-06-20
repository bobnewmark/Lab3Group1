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
