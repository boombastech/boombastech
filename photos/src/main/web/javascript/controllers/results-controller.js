angular.module('photoApp')
	.controller('ResultsController', ['$scope', '$rootScope', 'PhotoService', '$uibModal', function($scope, $rootScope, photoService, modal) {
		$scope.open = function(photo) {
		    var openPhoto = photo;
			var photoViewerModal = modal.open({
				templateUrl: 'photo-viewer.html',
				controller: 'PhotoViewerController',
				size: 'lg',
				resolve: {
					photo: function() {
						return photo;
					}
				}
			});

		    photoViewerModal.result.then(
		        function (updatedPhoto) {
                    openPhoto = updatedPhoto;
                }, function () {
                    console.log('Modal dismissed at: ' + new Date());
                }
            );
		};

        $scope.highlight = function(photo) {
        	photo.highlighted = !photo.highlighted;
        };
	}]);