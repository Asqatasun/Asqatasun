/* 
 * JavaScript that show/hide audit parameters
 */

$(document).ready(function() {

    masterAuditParameterClass = "master-audit-parameters";
    masterAuditParameter = $('#'+masterAuditParameterClass);
    auditParameterAnchorId = "audit-parameters";
    
    toggleParameterLink = $('<a>',
    {
        'href': '#' + auditParameterAnchorId,
        'aria-expanded': 'false',
        'aria-controls': auditParameterAnchorId
    });
    masterAuditParameter.wrap(toggleParameterLink);
    masterAuditParameter.addClass('collapsed');
    parameterPanel = $('#'+auditParameterAnchorId);
    parameterPanel.hide();
    
    $('a[href=#'+auditParameterAnchorId+']').click(function()
    {
        if ($(this).attr('aria-expanded') == 'false') {
            $(this).attr('aria-expanded', true).children('span:first-child').removeClass('collapsed').addClass('expanded');
            parameterPanel.show();
        } else {
            $(this).attr('aria-expanded', false).children('span:first-child').removeClass('expanded').addClass('collapsed');
            parameterPanel.hide();
        }
        return false;
    });
    
});