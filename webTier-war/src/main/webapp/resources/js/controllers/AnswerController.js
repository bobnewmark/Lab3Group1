vgangular.module('app', ['ngResource']);
App.factory('User', ['$resource', function ($resource) {
    return $resource('http://localhost:7001/laba/phone/:id');
}]);
$resource(url, [paramDefaults], [actions], options);

