/**
 * Created by Admin on 21.05.2017.
 */
'use strict';

angular.module('myApp').factory('PhoneService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:7001/laba/phone/';

    var factory = {
        fetchAllPhones: fetchAllPhones,
        createPhone: createPhone,
        updatePhone:updatePhone,
        deletePhone:deletePhone
    };

    return factory;

    function fetchAllPhones() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Phones');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createPhone(phone) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, phone)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Phone');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updatePhone(phone, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, phone)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating Phone');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deletePhone(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Phone');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);