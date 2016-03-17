angular.module('photoApp').factory('FacetService', ['$http', function($http) {

    return {
        get: function(successCallback, errorCallback) {
            $http({
                method: 'GET',
                url: '/api/facets',
                cache: true
            }).then(successCallback, errorCallback);
        }
    }
}]);