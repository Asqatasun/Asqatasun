/* 
 * JavaScript to display images that need to be checked
 */

$(document).ready(function() {

    $('.src').each(function()
    {
        $this = $(this).children('a:first-child');
        
        img = $('<img>',
        {
            'src': $this.attr("title"),
            'alt': $this.attr("title")
        }, '</>');
        $this.empty();
        $this.append(img);
        if ($this.width()>150 || $this.width()>150) {
            $this.addClass('iconify');
        }
    }); 
    
});