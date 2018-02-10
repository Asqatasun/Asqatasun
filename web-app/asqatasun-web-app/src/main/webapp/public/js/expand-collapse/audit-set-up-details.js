/* 
 * JavaScript for audit set-up page
 */

$(document).ready(function() {

    /* 
     * Show / hide audit options
     */
    masterAuditOptionClass = "master-audit-options";
    masterAuditOption = $('.' + masterAuditOptionClass);
    auditOptionAnchorId = "audit-options";
    toggleOptionLink = $('<a>',
            {
                'href': '#' + auditOptionAnchorId,
                'aria-expanded': 'false',
                'aria-controls': auditOptionAnchorId
            });
    masterAuditOption.wrap(toggleOptionLink);
    optionPanel = $('#' + auditOptionAnchorId);
    if (!masterAuditOption.hasClass('on-error')) {
        masterAuditOption.addClass('collapsed');
        optionPanel.hide();
    } else {
        masterAuditOption.addClass('expanded');
    }

    $('a[href=#' + auditOptionAnchorId + ']').click(function()
    {
        if ($(this).attr('aria-expanded') == 'false') {
            $(this).attr('aria-expanded', true).children('span:first-child').removeClass('collapsed').addClass('expanded');
            optionPanel.show();
        } else {
            $(this).attr('aria-expanded', false).children('span:first-child').removeClass('expanded').addClass('collapsed');
            optionPanel.hide();
        }
        return false;
    });
    /* 
     * Show / hide audit urls
     */
    masterAuditInputClass = "master-input";
    masterAuditInput = $('.' + masterAuditInputClass);
    if (masterAuditInput !== null) {
        auditInputAnchorId = "extra-inputs";
        toggleInputLink = $('<a>',
                {
                    'href': '#' + auditInputAnchorId,
                    'aria-expanded': 'false',
                    'aria-controls': auditInputAnchorId
                });
        masterAuditInput.wrap(toggleInputLink);
        inputPanel = $('#' + auditInputAnchorId);
        if (!masterAuditInput.hasClass('on-error')) {
            masterAuditInput.addClass('collapsed');
            inputPanel.hide();
        } else {
            masterAuditInput.addClass('expanded');
        }

        $('a[href=#' + auditInputAnchorId + ']').click(function()
        {
            if ($(this).attr('aria-expanded') == 'false') {
                $(this).attr('aria-expanded', true).children('span:first-child').removeClass('collapsed').addClass('expanded');
                inputPanel.show();
            } else {
                $(this).attr('aria-expanded', false).children('span:first-child').removeClass('expanded').addClass('collapsed');
                inputPanel.hide();
            }
            return false;
        });
    }
});