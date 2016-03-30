angular.module('photoApp')
	.controller('SearchController', ['$scope', '$rootScope', 'PhotoService', function($scope, $rootScope, photoService) {

        $rootScope.results = {};
        $rootScope.pageNumber = 1;

        $scope.search = function() {
        	$rootScope.pageNumber = 1;
        	search();
        };

        $scope.searchInit= function() {
            $scope.search();

        };

        var search = function() {
            var selectedFacets = {};

            if ($rootScope.results.hasOwnProperty("facets")) {
                for (var facetName in $rootScope.results.facets) {
                    if ($rootScope.results.facets.hasOwnProperty(facetName)) {
                        var facetsToSelect = [];

                        for (var facetValue in $rootScope.results.facets[facetName]) {

                            var name = $rootScope.results.facets[facetName][facetValue].name;
                            var selected = $rootScope.results.facets[facetName][facetValue].selected;

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

            selectedFacets['page'] = $rootScope.pageNumber;

            photoService.get(selectedFacets, successCallback, errorCallback);
        }

        $rootScope.$watch(
            function() {
                return $rootScope.pageNumber;
            },
            function change(oldValue,newValue) {
                search();
            }
        );

        $scope.activate = function(facetValue, selected) {
            if (selected) {
                facetValue.selected = true;
            }
        }

        $scope.deactivate = function(facetValue) {
            for (var pivotFacet in facetValue.pivotFacets) {
                pivotFacet.selected = false;
            }
        }

        $scope.update = function() {
        	photoService.update($rootScope.results.results,
				function() {
					$scope.search();
				},
				function() {
					console.log("error")
				}
        	 );
        }

        successCallback = function(response) {
            $rootScope.results = response.data;
        }

        errorCallback = function(response) {
            $rootScope.results = 'error';
        }
	}]);
