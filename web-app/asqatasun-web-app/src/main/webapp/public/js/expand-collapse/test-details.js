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
        var themeInHash = null;
        var hashCode = window.location.hash.substring(1).toString();
        var testFromAnchor = hashCode.substring(5).toString();
        if (testFromAnchor.match(/^\d+\.\d+\.\d+/))
            themeInHash = 'r' + testFromAnchor + '-detailed';
        var detailId = $(this).parent().parent().parent().parent().parent().parent().children('.test-result-detailed').attr('id');
        if (themeInHash !== null && detailId === themeInHash) {
            $(this).parent().attr('aria-expanded', true).parent().parent().parent().parent().next(panels).show();
            $(this).parent().children('.hide-test-details-link-icon').show();
            $(this).parent().attr('title', $(this).attr('alt'));
            $(this).parent().children('.show-test-details-link-icon').hide();
        }
        else {
            $(this).hide();
            $(this).next().show();
        }
    });

    $('.rule-id .test-expand').click(function() {
        var showIcon;
        if ($(this).attr('aria-expanded') === 'false') {
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

    $('.rule-label.expandable').click(function() {
        $(this).siblings('.rule-id').find('.test-expand').click();
    });

});