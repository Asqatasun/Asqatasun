/* 
 * JavaScript for audit result page
 */

$(document).ready(function() {
    $('.test-result-detailed').hide();

    $('.test-result-compact').click( function() {
        $(this).next('.test-result-detailed').toggle();
    });
});
