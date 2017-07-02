'use strict';

App.factory('ItemService', ['$http', '$q', function($http, $q){
    var factory = {
        fetchAllItems: fetchAllItems,
        createItem: createItem,
        updateItem: updateItem,
        deleteItem: deleteItem,
        showItem: showItem,
        uploadFileToUrl: uploadFileToUrl
    };

    return factory;

    function fetchAllItems(url) {
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

    function showItem(id, url) {
        var deferred = $q.defer();
        $http.get(url+id)
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

    function createItem(user, url) {
        var deferred = $q.defer();
        $http.post(url, user)
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


    function updateItem(item, url) {
        var deferred = $q.defer();
        $http.post(url, item)
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

    function deleteItem(id, url) {
        var deferred = $q.defer();
        $http.delete(url+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function uploadFileToUrl(file, url, id){
        var deferred = $q.defer();
        var fd = new FormData();
        fd.append('id', id);
        Object.keys(file).forEach(function(key) {
            fd.append(key, file[key]);
            console.log('file is ' );
            console.dir(file[key]);
        });
        $http.post(url, fd, {
            transformRequest: function(data, headersGetterFunction) {
                return data;
            },
            headers: {'Content-Type': undefined}
        })
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
