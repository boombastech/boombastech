angular.module('photoApp')
        	.controller('PaginationController', ['$scope', '$rootScope', 'PhotoService', function($scope, $rootScope, photoService) {
        	    $scope.changePage = function(key) {
        	        $rootScope.pageNumber = key;
        	    };

        	    $scope.isCurrentPage = function(key) {
        	        if ($rootScope.pageNumber === key) {
        	            return "active";
        	        }
        	        else {
        	            return "";
        	        }
        	    };

        	    $scope.previous = function() {
        	        $scope.changePage($rootScope.pageNumber-1);
        	    };

        	    $scope.next = function() {
                    $scope.changePage($rootScope.pageNumber+1);
                };
        	}]);