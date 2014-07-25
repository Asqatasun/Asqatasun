/* 
 * JavaScript to display and hide comment area in manual audit
 */
$(document).ready(
        function() {

            // hide all comment area
            $(".audit-result-manual-comment").addClass( "audit-result-manual-comment-hidden" ).removeClass( "audit-result-manual-comment" );

            //enable comment area for checked failed radio 
            $('input:radio').each(function() {
                if ((($(this).val()) === 'failed') && $(this).is(':checked')) {
                    var commentAreaId = 'commentContainer' + $(this).attr('id').substring(6, $(this).attr('id').length);
                    $('div[id="' + commentAreaId + '"]').addClass( "audit-result-manual-comment" ).removeClass( "audit-result-manual-comment-hidden" );
                }
            }
            );

            // bind event to radiobutton
            $('input:radio').on(
                    'change',
                    function() {
                        if (($(this).val()) === 'failed') {
                            var commentAreaId = 'commentContainer' + $(this).attr('id').substring(6, $(this).attr('id').length);
                            $('div[id="' + commentAreaId + '"]').addClass( "audit-result-manual-comment" ).removeClass( "audit-result-manual-comment-hidden" );
                        }
                    });

            $('input:radio').on('change',
                    function() {
                        if ((($(this).val()) === 'passed')) {
                            var commentAreaId = 'commentContainer' + $(this).attr('id').substring(6, $(this).attr('id').length);
                            $('div[id="' + commentAreaId + '"]').addClass( "audit-result-manual-comment-hidden" ).removeClass( "audit-result-manual-comment" );
                        }
                        if (($(this).val()) === 'na') {
                            var commentAreaId = 'commentContainer' + $(this).attr('id').substring(3, $(this).attr('id').length);
                            $('div[id="' + commentAreaId + '"]').addClass( "audit-result-manual-comment-hidden" ).removeClass( "audit-result-manual-comment" );
                        }

                    });
        });