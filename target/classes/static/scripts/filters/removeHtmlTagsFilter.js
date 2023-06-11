//for filter html tags in String variable.
app.filter('removeHTMLTags', function () {
    return function (text) {
        return text ? String(text).replace(/<[^>]+>/gm, '') : '';
    };
});