$(document).ready(function() {
    
    $('#launch-audit-submit').click(function() {

        var processingMessages = [], 
            displayedMessages = [], 
            firstRandomMessage;
        $(".process-message").each(function (d, i) {
            processingMessages.push($(this).text());
            $(this).hide();
        });

        $("#process-anim").show();

        firstRandomMessage = Math.floor((Math.random()*processingMessages.length));
        displayedMessages.push(firstRandomMessage);

        $("#process-dialog")
            .append("<p id='process-msg' aria-live='true'>"+processingMessages[firstRandomMessage]+"</p>")
            .dialog({
                autoOpen : true,
                modal : true,
                title :  "",
                resizable : false,
                draggable : false,
                dialogClass : "noCloseBtn",
                width : 300,
                height : 220
            });

        setInterval(function() {
            var randomMessage = Math.floor((Math.random()*processingMessages.length));
            while (jQuery.inArray(randomMessage, displayedMessages) != -1) {
                randomMessage = Math.floor((Math.random()*processingMessages.length));
            }
            displayedMessages.push(randomMessage);
            if (displayedMessages.length == processingMessages.length) {
                displayedMessages = [];
            }
            $('#process-msg').text(processingMessages[randomMessage]);
        }, 1000);
    });

});