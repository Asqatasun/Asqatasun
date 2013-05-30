/* 
 * JavaScript to highlight a specific line in the source code
 */
$(document).ready(function() {
    var selectedLine = window.location.hash;
    if (selectedLine.length >0) {
        $(selectedLine).addClass('selected-line');
    }
});