/**
 * 
 */
$(window).on("beforeunload", function() { 
    return inFormOrLink ? "Do you really want to close?" : null; 
})