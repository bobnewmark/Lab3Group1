App.controller('MenuCtrl', function($scope, $location){
    $scope.isActive = function(url)  {
        if ($location.absUrl()===($location.protocol()+"://"+$location.host()+":"+$location.port()+"/laba"+url)) {
            return "active";
        }else {
            return "not-active"
        }
    }
});