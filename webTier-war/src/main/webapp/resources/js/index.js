var map = ({'/laba/': '/laba/phone/',
    '/laba/shop': '/laba/products',
    '/laba/showCart': '/laba/showCart',
     '/laba/search/': '/laba/request/'});
var App = angular.module('myApp', [],function($locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});
App.factory('Service', ['$http', '$q', '$window', function($http, $q, $window){

    var SERVICE_URI = '/laba/registration/';

    var factory = {
        createItem: createItem,
        fetchUser: fetchUser
    };

    return factory;
    function createItem(user) {
        var deferred = $q.defer();
        $http.post(SERVICE_URI, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                    $window.location.href = '/laba/';
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                    $window.location.href = '/laba/registration?reg-err';
                }
            );
        return deferred.promise;
    }
    function fetchUser(url) {
        var deferred = $q.defer();
        $http.get(url)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);

App.controller('mainController',['$scope', 'Service', '$location',  function($scope, Service, $location){
    var self = this;
    self.item = {};
    self.items=[];
    self.submit = submit;
    fetchUser();
    function submit() {
        console.log('Saving New User', self.item);
        Service.createItem(self.item);
    }
    function fetchUser(){
        Service.fetchUser('/laba/user')
            .then(
                function(d) {
                    self.item = d;
                },
                function(errResponse){
                    console.error('Error while fetching User');
                }
            );
    }

    if($location.absUrl().indexOf("reg-err")!=-1){
        $scope.statusreg = 1;
    }else{
        $scope.statusreg = 0;
    }
	}]);

App.controller('loginController',['$scope', '$location',  function($scope, $location){
    if($location.absUrl().indexOf("error")!=-1){
        $scope.status = 1;
    }else{
        $scope.status = 0;
    }


}]);


App.directive("passwordVerify", function() {
    return {
        require: "ngModel",
        scope: {
            passwordVerify: '='
        },
        link: function(scope, element, attrs, ctrl) {
            scope.$watch(function() {
                var combined;

                if (scope.passwordVerify || ctrl.$viewValue) {
                    combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                }
                return combined;
            }, function(value) {
                if (value) {
                    ctrl.$parsers.unshift(function(viewValue) {
                        var origin = scope.passwordVerify;
                        if (origin !== viewValue) {
                            ctrl.$setValidity("passwordVerify", false);
                            return undefined;
                        } else {
                            ctrl.$setValidity("passwordVerify", true);
                            return viewValue;
                        }
                    });
                }
            });
        }
    };
});