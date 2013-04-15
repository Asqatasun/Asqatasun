/* 
 * JavaScript for audit result page
 */

$(document).ready(function() {
    $('.test-result-detailed').hide();

    $('.test-result-compact').click( function() {
        $('.test-result-detailed').hide();
        $(this).next('.test-result-detailed').toggle();
    });
});
