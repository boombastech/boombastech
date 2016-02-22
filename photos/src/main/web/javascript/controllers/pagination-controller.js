angular.module('photoApp')
	.controller('PaginationController', ['$scope', '$rootScope', 'PhotoService', function($scope, $rootScope, photoService) {
	    $scope.changePage = function(key) {
	        $rootScope.pageNumber = key;
	    }
	}]);