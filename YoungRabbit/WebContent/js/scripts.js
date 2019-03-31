
$(document).ready(function () {

});

$(document).delegate('.small-verticle-product .card, .large-one-product .card', 'click', function (e) {
    e.stopPropagation();
    var navigateUrl = $(this).data('navigate-url');

    var isCompleteUrl = navigateUrl.toLowerCase().match("^http");
    if (!isCompleteUrl) {

        // to make sure slash is not adding twice.
        if (navigateUrl.indexOf('/') == 0) {

            navigateUrl = navigateUrl.substring(1);
        }

        navigateUrl = baseUrl + '/' + navigateUrl;
    }

    if (navigateUrl) window.location = navigateUrl;

    return false;
});
var composeErrorMessage = function (msg) {

    if(msg){

        return $([
                    ' <div class="alert alert-danger fade in alert-dismissable">',
                    '   <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>',
                    '   <strong>Error!</strong>',
                   msg,
                    ' </div>',
        ].join("\n"));	
    }
}

var setCookie = function (cookieName, cookieValue) {


    if (typeof $.cookie(cookieName) === 'undefined') {
        $.cookie(cookieName, cookieValue, { expires: 30 }); // cookie is valid for 30 days. 
    }
}

var getCookie = function (cookieName) {

    return $.cookie(cookieName);
}

function logEvent(category, label) {
    if (window.ga && ga.create) {
        //Universal event tracking
        //https://developers.google.com/analytics/devguides/collection/analyticsjs/events
        ga('send', 'event', category, 'click', label, {
            // nonInteraction: true
        });
    } 
        else {
            console.info('Google analytics not found in this page')
        }
}

