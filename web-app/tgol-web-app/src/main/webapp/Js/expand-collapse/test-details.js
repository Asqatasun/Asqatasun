/* 
 * JavaScript for audit result page
 */

$(document).ready(function() {

    var panels = $('.test-result-detailed').hide();
    
    $('.rule-id .hide-test-details-link-icon').each(function(i) {
	$(this).parent().parent().siblings('.rule-label').addClass("expandable");
        var anchor = $(this).parent().parent().parent().parent().next(panels)[0].id, 

            lien = $('<a>',
        {
            'href': '#' + anchor,
            'class': 'test-expand',
            'title': $(this).next().attr("alt"),
            'aria-expanded': 'false',
            'aria-controls': anchor
        });
        $(this).next().andSelf().wrapAll(lien);
        $(this).hide();
        $(this).next().show();
    }); 
    
    $('.rule-id .test-expand').click(function() {
        var showIcon;
        if ($(this).attr('aria-expanded') == 'false') {
            $(this).attr('aria-expanded', true).parent().parent().parent().parent().next(panels).show();
            showIcon = $(this).children('.hide-test-details-link-icon');
            showIcon.show();
            $(this).attr('title', showIcon.attr('alt'));
            $(this).children('.show-test-details-link-icon').hide();
        } else {
            $(this).attr('aria-expanded', false).parent().parent().parent().parent().next(panels).hide();
            showIcon = $(this).children('.show-test-details-link-icon');
            showIcon.show();
            $(this).attr('title', showIcon.attr('alt'));
            $(this).children('.hide-test-details-link-icon').hide();
        }
        return false;
    });

    $('.rule-label.expandable').click( function() {
	$(this).siblings('.rule-id').find('.test-expand').click();
    });
    
});