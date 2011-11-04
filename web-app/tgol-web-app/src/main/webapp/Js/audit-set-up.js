/* 
 * JavaScript for audit result page
 */

var create_name = function(text) {
  // Convert text to lower case.
  var name = text.toLowerCase();

  // Remove leading and trailing spaces, and any non-alphanumeric
  // characters except for ampersands, spaces and dashes.
  name = name.replace(/^\s+|\s+$|[^a-z0-9&\s-]/g, '');

  // Replace '&' with 'and'.
  name = name.replace(/&/g, 'and');

  // Replaces spaces with dashes.
  name = name.replace(/\s/g, '-');

  // Squash any duplicate dashes.
  name = name.replace(/(-)+\1/g, "$1");

  return name;
};

var add_link = function(currentSelector, suffix) {
  // Convert the h2 element text into a value that
  // is safe to use in a name attribute.
//  var selectorText='.toggle-master-'.concat(suffix);
  var name = create_name(currentSelector.text());

  // Create a name attribute in the toggle elements
  // to act as a fragment anchor.
  $('.toggle-'.concat(suffix)).attr('name', name);
  // Replace the .toggle-master element with a link to the
  // fragment anchor.
  $(currentSelector).html(
    '<a href="#' + name + '" >' +
    $(currentSelector).html() + '</a>');
};

var toggle_url = function(event) {
    event.preventDefault();
  // Toggle the 'expanded' class of the toggle-master
  // element, then apply the slideToggle effect
  // to toggle element to hide/display.
  $(this).toggleClass('expanded');
  $('.toggle-url').slideToggle('slow');
};

var toggle_parameters = function(event) {
    event.preventDefault();
    // Toggle the 'expanded' class of the toggle-master
    // element, then apply the slideToggle effect
    // to toggle element to hide/display.
    $(this).toggleClass('expanded');
    $('.toggle-parameters').slideToggle('slow');
};

var toggle_display_parameters = function(event) {
    event.preventDefault();
    // Toggle the 'expanded' class of the toggle-master
    // element, then apply the slideToggle effect
    // to toggle element to hide/display.
    $(this).toggleClass('expanded');
    $('.toggle-display-parameters').slideToggle('slow');
};

var remove_focus = function() {
  // Use the blur() method to remove focus.
  $(this).blur();
};

var update_class_name = function(text) {
    $('._'.concat(text)).
        removeClass('_'.concat(text)).
        addClass(text);
}

var initialize_toogle = function(text) {
    update_class_name('toggle-'.concat(text));
    update_class_name('toggle-master-'.concat(text));
    update_class_name('expanded');

    // Add a link to each toggle element.
    var selector= $('.toggle-master-'.concat(text));
    if ( selector.length ) {
        add_link(selector,text);
    }

    selector= $('.toggle-master-'.concat(text,':not(.expanded)'));
    if ( selector.length ) {
        $('.toggle-'.concat(text)).hide();
    }

    // Remove the focus from the link tag when accessed with a mouse.
    selector= $('.toggle-master-'.concat(text,' a'));
    selector.mouseup(remove_focus);
};

$(document).ready (function (){
    initialize_toogle('parameters');
    // Add a click event handler to all toggle elements.
    $('.toggle-master-parameters').click(toggle_parameters);

    initialize_toogle('url');
    // Add a click event handler to all toggle elements.
    $('.toggle-master-url').click(toggle_url);

    initialize_toogle('display-parameters');
    // Add a click event handler to all toggle elements.
    $('.toggle-master-display-parameters').click(toggle_display_parameters);
});
