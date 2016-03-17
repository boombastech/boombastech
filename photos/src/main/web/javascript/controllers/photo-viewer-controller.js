angular.module('photoApp')
	.controller('PhotoViewerController', ['$scope', '$uibModalInstance', 'PhotoService','photo', function($scope, $uibModalInstance, photoService, photo) {
		$scope.photo = angular.copy(photo);

		$scope.save = function() {
		    $uibModalInstance.close($scope.photo);
		}

		$scope.cancel = function() {
			$uibModalInstance.dismiss('cancel');
		}
	}]);