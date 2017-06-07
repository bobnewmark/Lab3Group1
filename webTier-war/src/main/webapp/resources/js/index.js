
var App = angular.module('myApp', []);
App.factory('Service', ['$http', '$q', function($http, $q){

    var SERVICE_URI = 'http://localhost:7001/laba/registration/';

    var factory = {
        createItem: createItem
    };

    return factory;
    function createItem(user) {
        var deferred = $q.defer();
        $http.post(SERVICE_URI, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
    function updateItem(user, id) {
		var deferred = $q.defer();
		$http.put(SERVICE_URI+id, user)
			.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while updating User');
					deferred.reject(errResponse);
				}
			);
		return deferred.promise;
	}
}]);

	// create angular controller
App.controller('mainController',['$scope', 'Service',  function($scope, Service){
        var self = this;
        self.item = {};
        self.items=[];
        self.submit = submit;
        function submit() {
            if(self.item.id===null){
                console.log('Saving New User', self.item);
                Service.createItem(self.item);
            }else{
                Service.updateItem(self.item, self.item.id);
                console.log('User updated with id ', self.item.id);
            }
        }
		// function to submit the form after all validation has occurred			
		$scope.submitForm = function() {

			// check to make sure the form is completely valid
			if ($scope.userForm.$valid) {
				alert('our form is amazing');
			}

		};


	}]);

App.controller('loginController',['$scope', 'Service',  function($scope, Service){
    // function to submit the form after all validation has occurred
    $scope.submitForm = function() {

        // check to make sure the form is completely valid
        if ($scope.userForm.$valid) {
            alert('our form is amazing');
        }

    };


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