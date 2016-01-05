angular.module('photoApp')
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