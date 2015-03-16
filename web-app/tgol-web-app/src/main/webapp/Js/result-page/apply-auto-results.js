/**
 * 
 */
$(document).ready(function() {

    $('#apply-all-auto-passed').click(function () {
        $('.audit-result-container').each(function () {
            var passed = $(this).find('.test-result img[src*="ico-passed"]'); 
            if (passed.length > 0) {
                $(this).find('.audit-result-manual input[value*="passed"]').click();
//                $(this).find('.audit-result-manual input[value*="passed"]').attr('checked', 'checked');
            }
        });
    });
    $('#apply-all-auto-failed').click(function () {
        $('.audit-result-container').each(function () {
            var failed = $(this).find('.test-result img[src*="ico-failed"]'); 
            if (failed.length > 0) {
                $(this).find('.audit-result-manual input[value*="failed"]').click();
//                $(this).find('.audit-result-manual input[value*="failed"]').attr('checked', 'checked');
            }
        });
    });
    $('#apply-all-auto-na').click(function () {
        $('.audit-result-container').each(function () {
            var na = $(this).find('.test-result img[src*="ico-na"]'); 
            if (na.length > 0) {
                $(this).find('.audit-result-manual input[value*="na"]').click();
//                $(this).find('.audit-result-manual input[value*="na"]').attr('checked', 'checked');
            }
        });
    });
    
});