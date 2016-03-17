angular.module('photoApp')
    .factory('PhotoService', ['$http', function($http) {
        var results;

        return {
            get : results,
            update: function(newResults) {
                results = newResults;
            }
        };
    }]);