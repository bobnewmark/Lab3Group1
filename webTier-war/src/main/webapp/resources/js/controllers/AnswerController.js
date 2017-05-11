angular.module('app', ['ngResource']);
App.factory('User', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/Spring4MVCAngularJSNgResourceExample/user/:id');
}]);
$resource(url, [paramDefaults], [actions], options);

