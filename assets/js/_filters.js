
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

    function format(qty, label) {
    	var unit = label.replace("(s)", Math.abs(qty) > 1 ? "s" : "");
    	
        return qty<0 ? "in " + Math.abs(qty) + unit : qty + unit + " ago";
    }

    return function (rawDate) {
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
});

darjeelingApp.filter('fullDate', function () {
    return function (rawDate) {
        return new Date(rawDate).toString();
    }
});
