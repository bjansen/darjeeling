
darjeelingApp.filter('removeSpam', function () {
    return function (string) {
        var thingsToRemove = [];
        var doc = document.implementation.createHTMLDocument("");
        doc.documentElement.innerHTML = string;

        var qcRss = doc.getElementsByTagName("map");
        for (var i = 0; i < qcRss.length; i++) {
            var node = qcRss[i];
            if (node.name.startsWith("admap")) {
                thingsToRemove = thingsToRemove.concat(node.parentNode);
                if (node.parentNode.nextSibling.tagName == "TABLE") {
                    thingsToRemove = thingsToRemove.concat(node.parentNode.nextSibling);
                }
            }
        }

        thingsToRemove = thingsToRemove.concat([].slice.call(doc.getElementsByClassName('feedflare')));

        for (var i = 0; i < thingsToRemove.length; i++) {
            thingsToRemove[i].parentNode.removeChild(thingsToRemove[i]);
        }

        return doc.documentElement.innerHTML;
    };
});

darjeelingApp.filter('trust', function ($sce) {
    return function (raw) {
        return $sce.trustAsHtml(raw);
    };
});

darjeelingApp.filter('prettyDate', function () {
    var msPerMinute = 1000 * 60;
    var msPerHour = 1000 * 60 * 60;
    var msPerDay = 1000 * 60 * 60 * 24;

    function pluralize(qty, label) {
        return qty + label.replace("(s)", qty > 1 ? "s" : "");
    }

    return function (rawDate) {
        var date = new Date(rawDate);
        var dateDiff = new Date() - date;

        if (dateDiff / msPerMinute < 60) {
            return pluralize(Math.floor(dateDiff / msPerMinute), " minute(s) ago");
        } else if (dateDiff / msPerHour < 24) {
            return pluralize(Math.floor(dateDiff / msPerHour), " hour(s) ago");
        } else if (dateDiff / msPerDay < 4) {
            return pluralize(Math.floor(dateDiff / msPerDay), " day(s) ago");
        }

        return date.toDateString();
    };
});

darjeelingApp.filter('fullDate', function () {
    return function (rawDate) {
        return new Date(rawDate).toString();
    }
});
