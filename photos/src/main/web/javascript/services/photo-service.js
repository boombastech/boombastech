angular.module('photoApp')
	.factory('PhotoService', ['$http', function($http) {
		return {
			get: function(params, successCallback, errorCallback) {
				$http({
					method: 'GET',
					url: '/api/search',
					cache: true,
					params: params
				}).then(successCallback, errorCallback);
			},
			update: function(photos, successCallback, errorCallback) {
				$http({
					method: 'POST',
					url: '/api/update',
					data: photos,

				}).then(successCallback, errorCallback);
			}
		};
	}]);