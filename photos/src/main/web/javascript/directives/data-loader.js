angular.module('photoApp').
    directive("preloadResource", function() {
        return {
            link: function(scope, element, attrs) {
                scope.preloadResource = attrs.preloadResource;
                element.remove();
            }
        };
});

//<div ng-cloak preload-resource=""></div>