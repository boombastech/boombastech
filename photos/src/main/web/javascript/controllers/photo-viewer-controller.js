angular.module('photoApp')
	.controller('PhotoViewerController', ['$scope', '$uibModalInstance', 'PhotoService','photo', function($scope, $uibModalInstance, photoService, photo) {
		$scope.photo = photo;

		$scope.save = function() {
			photoService.update([photo], function(response) {
				console.log('success');
			},
			function(response) {
				console.log('error');
			})
		}

		$scope.cancel = function() {
			$uibModalInstance.dismiss('cancel');
		}
	}]);