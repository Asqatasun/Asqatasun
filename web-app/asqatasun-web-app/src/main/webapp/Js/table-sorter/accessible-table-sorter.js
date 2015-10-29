$(document).ready(function() {
    $('.sortable-table').each(function(i) {
        var gTableCaptionText = $(this).children('caption').text();

        $.tablesorter.addWidget({

            id: 'addA11y',
            format: function(table) {

                // Now add the accessibility information
                jQuery('tr', table).each( function(e) {

                    // Do the header first because sorted and unsorted columns have this class
                    $('th.'+table.config.cssHeader, this).each( function(e) {
                        $('span.offscreen', this).each( function(e) {
                            $(this).remove();
                        });

                        $(this).children('caption').text(gTableCaptionText); // modify
                        $(this).removeAttr("aria-sort"); // remove any existing aria-sort attribute on this header

                        $(this).append( $('<span>').addClass('offscreen').text( 'Sortable, Not Sorted')); // add some offscreen text to the header
                    });

                    // Now do the ascending and descending
                    $(this).find( 'th.'+table.config.cssAsc).each( function(e) {
                        $('span.offscreen', this).each( function(e) {
                            $(this).remove();
                        });
                        var headerText = $(this).text();
                        $(this).children('caption').text(gTableCaptionText + ': Sorted By ' + headerText + ' Descending ');

                        $(this).attr("aria-sort", "descending");//give it aria-sort attribute with value of descending
                        $(this).append( $('<span>').addClass('offscreen').text( 'Sortable, Sorted Descending'));// add some offscreen text to the header
                    });
                    $('th.'+table.config.cssDesc, this).each( function(e) {
                        $('span.offscreen', this).each( function(e) {
                            $(this).remove();
                        });
                        var headerText = $(this).text();
                        $(this).children('caption').text(gTableCaptionText + ': Sorted By ' + headerText + ' Ascending ');

                        $(this).attr("aria-sort", "ascending");//give it aria-sort attribute with value of ascending
                        $(this).append( $('<span>').addClass('offscreen').text( 'Sortable, Sorted Ascending'));// add some offscreen text to the header
                    });
                });
            }
        });

        // make the good table
        $(this)
        .tablesorter({
            widgets: ['addA11y']
        })
        .wrap('<div role="application">') // wrap it in a DIV with a role of application
        .attr("role", "grid") // add role of grid to the table itself
        .attr("aria-readonly", "true")  // mark the grid as readonly (because none of the data is editable
        .attr("aria-labelledby", "theCaption") // label the table with caption
        .bind( 'keypress keyup keydown', function(e) {
            var keyCode;
            var special = (e.ctrlKey || e.shiftKey || e.metaKey || e.altKey);
            if ( !e.keyCode && 'which' in e && e.which) keyCode = e.which;
            else keyCode = e.keyCode;
            // Whenever a header cell is active, pressing the spacebar or enter
            // will trigger the sort
            if ( keyCode == 13 || keyCode == 32) {
                if ( $(e.target).hasClass( 'header')) {
                    $(e.target).trigger( 'click');
                }
            }
        });  // set the table to be labelled by its caption

        // add role of row for all trs
        $(this).children('tr').attr("role", "row");

        // assign role of columnheader and row header
        // THs inside the THEAD get columnheaders
        // THs inside the TBODY get rowheaders
        // primarily because we know this is a simple, symmetrical table. YMMV
        // also add tabindex to each header (this is an assumption that they are all sortable
        $(this).children('thead th').attr("role", "columnheader").attr("scope", "col");
        $(this).children('tbody th').attr("role", "rowheader").attr("scope", "row");

        // add tabindex so tabindex only appears on the sortable headers
        // which is basicaly any TH *with* a class of 'header' (which gets put there by tablesorter)
        $(this).children('th.header').attr("tabindex", "0");

        // assign role of gridcell on all data cells
        $(this).children('tbody td').attr("role", "gridcell");

        $(this).children('caption').attr({
            role: 'log',
            'aria-live': 'assertive',
            'aria-relevant': 'all',
            'aria-atomic': 'true'
        });
    }); 
});
