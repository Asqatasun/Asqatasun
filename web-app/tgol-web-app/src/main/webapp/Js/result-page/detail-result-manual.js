/* 
 * JavaScript to display and hide comment area in manual audit
 */
$(document).ready(
		function() {
			
			// hide all comment area
			$(".audit-result-manual-comment").css("visibility", "hidden");

			// bind event to radiobutton
			$('input:radio').on(
					'change',
					function() {
						if (($(this).val()) == 'failed') {
							var commentAreaId = 'commentContainer'+$(this).attr('id').substring(6,$(this).attr('id').length);
							$('div[id="'+commentAreaId+'"]').css("visibility", "visible");
							// $(this).parent().parent().parent().parent().parent().parent().find('div[id^="commentContainer"]').css("visibility","visible")
						}
					});

			$('input:radio').on('change',
					function() {
						if ((($(this).val()) == 'passed')) {
							var commentAreaId = 'commentContainer'+$(this).attr('id').substring(6,$(this).attr('id').length);
							$('div[id="'+commentAreaId+'"]').css("visibility", "hidden");
						}
						if (($(this).val()) == 'na') {
							var commentAreaId = 'commentContainer'+$(this).attr('id').substring(3,$(this).attr('id').length);
							$('div[id="'+commentAreaId+'"]').css("visibility", "hidden");
						}

					});
		});