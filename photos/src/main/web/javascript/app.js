angular.module('photoApp', [])
	.controller('PhotoController', ['$scope', 'PhotoService', function($scope, photoService) {
		$scope.results = {};

        $scope.search = function() {
        	var selectedFacets = {};

			if ($scope.results.hasOwnProperty("facets")) {
				for (var facetName in $scope.results.facets) {
					if ($scope.results.facets.hasOwnProperty(facetName)) {
						var facetsToSelect = [];

						for (var facetValue in $scope.results.facets[facetName]) {

							var name = $scope.results.facets[facetName][facetValue].name;
							var selected = $scope.results.facets[facetName][facetValue].selected;

							if (selected) {
								facetsToSelect.push(name);
							}
						}

						if (facetsToSelect.length !== 0) {
							selectedFacets[facetName] = facetsToSelect;
						}
					}
				}
			}

        	photoService.get(selectedFacets, successCallback, errorCallback);

        }

        $scope.update = function() {
        	photoService.update($scope.results.results, function() { console.log("success")}, function() { console.log("error")});
        }

        $scope.highlight = function(photo) {
        	photo.highlighted = !photo.highlighted;
        }

        successCallback = function(response) {
        	$scope.results = response.data.test;
        }

        errorCallback = function(response) {
        	$scope.results = 'error';
        }

	}])
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
	}])
	.directive('photoOptions', function() {
		return {
			templateUrl: 'photo-options.html',
			scope: {
				photo: '=photo'
			},
			controller: ['$scope', function($scope) {
				$scope.remove = function(array, index) {
					array.splice(index, 1);
				};

				$scope.addAlbum = function() {
					$scope.photo.albums.push($scope.newAlbum);
					$scope.newAlbum = '';
				};

				$scope.addCategory = function() {
					$scope.photo.categories.push($scope.newCategory);
					$scope.newCategory = '';
				}
			}]
		};
	});