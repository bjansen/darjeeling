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

darjeelingApp.directive('prettyDate', ['$parse', '$interval', function ($parse, $interval) {
    var msPerMinute = 1000 * 60;
    var msPerHour = 1000 * 60 * 60;
    var msPerDay = 1000 * 60 * 60 * 24;

    function format(qty, label) {
    	var unit = label.replace("(s)", Math.abs(qty) > 1 ? "s" : "");
    	
        return qty<0 ? "in " + Math.abs(qty) + unit : qty + unit + " ago";
    }

    var prettyDate = function (rawDate) {
        var date = new Date(rawDate);
        var dateDiff = new Date() - date;

        if (Math.abs(dateDiff / msPerMinute) < 60) {
            return format(Math.floor(dateDiff / msPerMinute), " minute(s)");
        } else if (Math.abs(dateDiff / msPerHour) < 24) {
            return format(Math.floor(dateDiff / msPerHour), " hour(s)");
        } else if (Math.abs(dateDiff / msPerDay) < 4) {
            return format(Math.floor(dateDiff / msPerDay), " day(s)");
        }

        return date.toDateString();
    };

    if (darjeelingApp.dateTimer === undefined) {
    	darjeelingApp.dateTimer = 0;
    	$interval(function() {
    		darjeelingApp.dateTimer++;
    	}, 10000);
    }
    
    return function (scope, element, attributes) {
    	var timeChanged = function() {
    		return darjeelingApp.dateTimer;
    	};
    	
        scope.$watch(timeChanged, function(value) {
        	var rawDate = $parse(attributes.prettyDate)(scope);
        	angular.element(element).text(prettyDate(rawDate));
        });
    }
}]);
