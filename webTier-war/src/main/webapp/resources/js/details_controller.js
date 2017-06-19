'use strict';
App.controller('DetailsController', ['$scope', 'ItemService', '$location', function($scope, ItemService, $location) {
    var self = this;
    var detailId = $location.path().substr($location.path().lastIndexOf('/') + 1 );
    var URI = 'http://localhost:7001/laba/detailed/' + detailId;
    var URI_R = 'http://localhost:7001/laba/related/' + detailId;

    self.items = {id:null, name:'', objectType:{id: null, name:''}, parameters:[
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}},
        {id:null, value:'', attribute:{id:null, name:''}, object:{id:null}}]};
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
                },
                function(errResponse){
                    console.error('Error while fetching detailed info');
                }
            );
    }

    function buy(id, quantity) {
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: 'buy',
            data: ({itemId : id, quantity : quantity}),
            success: function() {
                $scope.updateIndex();
                $location.path('/laba/shop');
            }
        });
    }

    function buyR(id) {
        console.log('buying related: ' + id);
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
