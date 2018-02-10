/* 
 * JavaScript for audit result page
 */

$(document).ready(function() {

    var selectedTheme = $("select[id='result.theme'] > option[selected]").attr("value"),
            passedChecked = false,
            nmiChecked = false,
            failedChecked = false,
            naChecked = false,
            collapse,
            panels;

    $(".inputs-list  input[checked]").each(function(i) {
        var elementValue = $(this).attr('value');
        if (elementValue === "PASSED") {
            passedChecked = true;
        } else if (elementValue === "FAILED") {
            failedChecked = true;
        } else if (elementValue === "NEED_MORE_INFO") {
            nmiChecked = true;
        } else if (elementValue === "NOT_APPLICABLE") {
            naChecked = true;
        }
    });
    collapse = (selectedTheme === "all-theme" && passedChecked && nmiChecked && failedChecked && naChecked) ? true : false;
    panels = $('.theme-details');
    if (collapse) {
        panels.hide();
    }
//    getAnchorValue -> test-1.1.1
//    if AnchorValue  !== null -> extract Theme index -> 1
    var hashCode = window.location.hash.substring(1).toString();
    var themeInHash;
    if (hashCode.charAt(6).match('^\\d')){
        themeInHash = 'theme' + hashCode.charAt(5) + hashCode.charAt(6) + '-details';}
    else {
        themeInHash = 'theme' + hashCode.charAt(5) + '-details';
    }

    $('.theme-info .hide-theme-details-link-icon').each(function(i)
    {
        var ancre = $(this).parent().parent().parent().parent().next(panels)[0].id,
                lien;
        if (collapse) {
            
            var ariaExpandedValue = 'false';
            if (ancre === themeInHash) {
                ariaExpandedValue = 'true';
            }
            lien = $('<a>',
                    {
                        'href': '#' + ancre,
                        'title': $(this).next().attr("alt"),
                        'aria-expanded': ariaExpandedValue,
                        'aria-controls': ancre
                    });
            $(this).parent().parent().parent().addClass("collapsed");
            $(this).next().andSelf().wrapAll(lien);

            if (ancre !== themeInHash) {
                hidePanel($(this).parent(), panels);
            } else {
                showPanel($(this).parent(), panels);
            }
        } else {
            lien = $('<a>',
                    {
                        'href': '#' + ancre,
                        'title': $(this).attr("alt"),
                        'aria-expanded': 'true',
                        'aria-controls': ancre
                    });
            $(this).next().andSelf().wrapAll(lien);
            showPanel($(this).parent(), panels);
        }
    });
    $('#collapse-all').css('display', 'inline-block').attr('aria-controls', 'all-themes');
    $('#expand-all').css('display', 'inline-block').attr('aria-controls', 'all-themes');

    $('.theme-info  a').click(function()
    {
        if ($(this).attr('aria-expanded') == 'false') {
            showPanel($(this), panels);

        } else {
            hidePanel($(this), panels);
        }
        return false;
    });

    // Add action to the thematic label
    $('.theme-label > h3').click(function()
    {
        $(this).parent().prev('div').find('a:nth-child(1)').click();
    });

    // Add the expand all action
    $('#expand-all').click(function()
    {
        $('.theme-info  a').each(function() {
            if ($(this).attr('aria-expanded') == 'false') {
                showPanel($(this), panels);
            }
        });
    });

    // Add the collapse all action
    $('#collapse-all').click(function()
    {
        $('.theme-info  a').each(function() {
            if ($(this).attr('aria-expanded') == 'true') {
                hidePanel($(this), panels);
            }
        });
    });
    
//    window.location.hash=window.location.hash; // unexpected line of code, must 
// be there for something 
});

function showPanel(selection, panneaux) {
    selection.attr('aria-expanded', true).parent().parent().parent().parent().next(panneaux).show();
    selection.parent().parent().parent().removeClass("collapsed");
    var showIcon = selection.children('.hide-theme-details-link-icon'),
            themeResultRepartition;
    showIcon.show();
    selection.attr('title', showIcon.attr('alt'));
    selection.children('.show-theme-details-link-icon').hide();

    themeResultRepartition = selection.parent().siblings('.theme-result-repartition');
    toggleIconResult(themeResultRepartition, false);
}

function hidePanel(selection, panneaux) {
    selection.attr('aria-expanded', false).parent().parent().parent().parent().next(panneaux).hide();
    selection.parent().parent().parent().addClass("collapsed");
    var showIcon = selection.children('.show-theme-details-link-icon'),
            themeResultRepartition;
    showIcon.show();
    selection.attr('title', showIcon.attr('alt'));
    selection.children('.hide-theme-details-link-icon').hide();

    themeResultRepartition = selection.parent().siblings('.theme-result-repartition');
    toggleIconResult(themeResultRepartition, true);
}

function toggleIconResult(selection, highlight) {
    var grayClassSuffix = '-m-gray.png',
            coloredClassSuffix = '-m.png';

    if (highlight) {
        changeSelectionSrc(
                selection.children('img[src$="ico-passed' + grayClassSuffix+'"]'),
                'ico-passed' + grayClassSuffix,
                'ico-passed' + coloredClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-failed' + grayClassSuffix+'"]'),
                'ico-failed' + grayClassSuffix,
                'ico-failed' + coloredClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-nmi' + grayClassSuffix+'"]'),
                'ico-nmi' + grayClassSuffix,
                'ico-nmi' + coloredClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-na' + grayClassSuffix+'"]'),
                'ico-na' + grayClassSuffix,
                'ico-na' + coloredClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-nt' + grayClassSuffix+'"]'),
                'ico-nt' + grayClassSuffix,
                'ico-nt' + coloredClassSuffix);
    } else {
        changeSelectionSrc(
                selection.children('img[src$="ico-passed' + coloredClassSuffix+'"]'),
                'ico-passed' + coloredClassSuffix,
                'ico-passed' + grayClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-failed' + coloredClassSuffix+'"]'),
                'ico-failed' + coloredClassSuffix,
                'ico-failed' + grayClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-nmi' + coloredClassSuffix+'"]'),
                'ico-nmi' + coloredClassSuffix,
                'ico-nmi' + grayClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-na' + coloredClassSuffix+'"]'),
                'ico-na' + coloredClassSuffix,
                'ico-na' + grayClassSuffix);
        changeSelectionSrc(
                selection.children('img[src$="ico-nt' + coloredClassSuffix+'"]'),
                'ico-nt' + coloredClassSuffix,
                'ico-nt' + grayClassSuffix);
    }
}

if (typeof String.prototype.startsWith !== 'function') {
  String.prototype.startsWith = function (str){
    return this.slice(0, str.length) === str;
  };
}

function changeSelectionSrc(selection, fromSrc, toSrc) {
    var attr = selection.attr('alt');
    if (typeof attr !== typeof undefined && attr !== false && attr.length > 0 && !attr.startsWith("0")) {
        var src = selection.attr('src').replace(fromSrc, toSrc);
        selection.attr('src', src);
    }
}