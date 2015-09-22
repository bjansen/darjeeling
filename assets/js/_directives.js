darjeelingApp.directive('scrollIf', function () {
    return function (scope, element, attributes) {
        scope.$watch(attributes.scrollIf, function(value) {
            if (value) {
                window.scrollTo(0, scope.offset(element[0]).top - 30);
            }
        });
    }
});

darjeelingApp.directive('href', function () {
    return {
        compile: function (obj) {
            var url = obj[0].href;

            if (!url.match(/^mailto\:/)
                && (url != location.hostname)
                && !url.match(/^javascript\:/)
                && !url.match(/^$/)) {
                obj.attr('target', '_blank');
            }
        }
    };
});
