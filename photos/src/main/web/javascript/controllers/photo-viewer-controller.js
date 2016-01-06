angular.module('photoApp')
	.controller('PhotoViewerController', ['$scope', '$uibModalInstance', 'photo', function($scope, $uibModalInstance, photo) {
		$scope.photo = photo;

		$scope.cancel = function() {
			$uibModalInstance.dismiss('cancel');
		}
	}]);