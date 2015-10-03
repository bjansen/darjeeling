var AppHelpers = {};

// taken from https://github.com/jquery/jquery/blob/master/src/offset.js
AppHelpers.offset = function (elem) {
    var docElem, win, rect, doc;
    if (!elem) {
        return;
    }

    // Support: IE<=11+
    // Running getBoundingClientRect on a
    // disconnected node in IE throws an error
    if (!elem.getClientRects().length) {
        return {top: 0, left: 0};
    }

    rect = elem.getBoundingClientRect();

    // Make sure element is not hidden (display: none)
    if (rect.width || rect.height) {
        doc = elem.ownerDocument;
        win = window;
        docElem = doc.documentElement;

        return {
            top: rect.top + win.pageYOffset - docElem.clientTop,
            left: rect.left + win.pageXOffset - docElem.clientLeft
        };
    }

    // Return zeros for disconnected and hidden elements (gh-2310)
    return rect;
};

var darjeelingApp = angular.module('darjeelingApp', ['ngMaterial', 'angular-mousetrap', 'headroom']);

darjeelingApp.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('indigo')
        .accentPalette('purple');
});

// This makes AJAX requests use query strings instead of JSON body
darjeelingApp.service('customHttp', ['$http', function($http) {

    var setupHttp = function ($httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        var param = function (obj) {
            var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

            for (name in obj) {
                value = obj[name];

                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                }
                else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                }
                else if (value !== undefined && value !== null)
                    query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
            }

            return query.length ? query.substr(0, query.length - 1) : query;
        };

        $httpProvider.defaults.transformRequest = [function (data) {
            return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
        }];
    };

    setupHttp($http);

    this.getHttp = function() {
        return $http;
    }
}]);
