App.
directive('setClassWhenAtTop', function ($window) {
    var $win = angular.element($window); // wrap window object as jQuery object

    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var topClass = attrs.setClassWhenAtTop, // get CSS class from directive's attribute value
                offsetTop = element.offset().top - 50; // get element's top relative to the document

            $win.on('scroll', function (e) {
                if ($win.scrollTop() >= offsetTop) {
                    element.addClass(topClass);
                } else {
                    element.removeClass(topClass);
                }
            });
        }
    };
}).controller('MenuCtrl', ['$scope', 'ItemService', '$location', function($scope, ItemService, $location){
    var REST_SERVICE_URI = 'http://localhost:7001/laba/cartIndex';
    var indexCart = 0;
    this.fetchCartIndex = fetchCartIndex;

    $scope.scrollTo = function (target){
    };
    $scope.isActive = function(url)  {
        if ($location.path().split('?')[0]==="/laba"+url) {
            return "active";
        }else {
            return "not-active"
        }
    };
    $scope.updateIndex = function() {
        fetchCartIndex();
    }
    fetchCartIndex();
    function fetchCartIndex(){
        ItemService.fetchAllItems(REST_SERVICE_URI)
            .then(
                function(d) {
                    $('#cartNum').html(d);
                },
                function(errResponse){
                    console.error('Error while fetching cart index');
                }
            );
    }

}]);
