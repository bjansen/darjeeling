
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

darjeelingApp.filter('fullDate', function () {
    return function (rawDate) {
        return new Date(rawDate).toString();
    }
});
