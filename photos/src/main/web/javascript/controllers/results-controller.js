angular.module('photoApp')
	.controller('ResultsController', ['$scope', '$rootScope', 'PhotoService', '$uibModal', function($scope, $rootScope, photoService, modal) {

		$scope.open = function(photo) {
			modal.open({
				templateUrl: 'photo-viewer.html',
				controller: 'PhotoViewerController',
				size: 'lg',
				resolve: {
					photo: function() {
						return photo;
					}
				}
			});
		}

        $scope.highlight = function(photo) {
        	photo.highlighted = !photo.highlighted;
        }
	}]);