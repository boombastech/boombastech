angular.module('photoApp')
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
        	photoService.update($scope.results.results,
				function() {
					$scope.search();
					console.log("success");
				},
				function() {
					console.log("error")
				}
        	 );
        }

        $scope.highlight = function(photo) {
        	photo.highlighted = !photo.highlighted;
        }

        successCallback = function(response) {
        	$scope.results = response.data;
        }

        errorCallback = function(response) {
        	$scope.results = 'error';
        }
	}]);