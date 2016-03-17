angular.module('photoApp')
    .directive('unique', function() {
    return {
        require: 'ngModel',
        scope: {
            array: '=unique'
        },
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$validators.uniqueValue = function(modelValue, viewValue) {
                if (scope.array.indexOf(viewValue) === -1) {
                    return true;
                } else {
                    return false;
                }
            };
        }
    };
    });