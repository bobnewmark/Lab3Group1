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
}).controller('MenuCtrl', function($scope, $location){
    $scope.scrollTo = function (target){
    };
    $scope.isActive = function(url)  {
        if ($location.absUrl()===($location.protocol()+"://"+$location.host()+":"+$location.port()+"/laba"+url)) {
            return "active";
        }else {
            return "not-active"
        }
    }
});
