var CsrfToken = Ext.util.Cookies.get('CSRF-TOKEN');
var jQueryClone = jQuery;

jQuery(document).ready(function(){
	
	jQueryClone.ajax({
        method: "GET",
        beforeSend: function (request) {
            request.setRequestHeader("X-XSRF-TOKEN", CsrfToken);
        },
        url: SailPoint.CONTEXT_PATH + "/plugin/rest/environmentStatusPlugin/status"
    })
    .done(function (msg) {
    	if (msg.startsWith("http://") || msg.startsWith("https://")) {
    		// We're fine... 
    	} else if (msg.startsWith("/")) {
    		// Add the IdentityIQ base URL:
    		msg = SailPoint.CONTEXT_PATH + msg;
    	} else {
    		// Add the Plugin base URL:
    		msg = SailPoint.CONTEXT_PATH + "/plugin/environmentStatusPlugin/" +  msg
    	}
    	
    	$("img.pull-right").attr("src", msg);
    });
		
});