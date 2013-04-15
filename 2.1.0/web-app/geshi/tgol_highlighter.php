<?php
/**
 * GeSHi example script
 *
 * Just point your browser at this script (with geshi.php in the parent directory,
 * and the language files in subdirectory "../geshi/")
 *
 * @author  Nigel McNie
 * @version $Id: example.php 1512 2008-07-21 21:05:40Z benbe $
 */
header('Content-Type: text/html; charset=utf-8');

error_reporting(E_ALL);

// Rudimentary checking of where GeSHi is. In a default install it will be in ../, but
// it could be in the current directory if the include_path is set. There's nowhere else
// we can reasonably guess.
if (is_readable('../geshi.php')) {
    $path = '../';
} elseif (is_readable('geshi.php')) {
    $path = './';
} else {
    die('Could not find geshi.php - make sure it is in your include path!');
}
require $path . 'geshi.php';

$fill_source = false;
if (isset($_POST['submit'])) {
    if (get_magic_quotes_gpc()) {
        $_POST['source'] = stripslashes($_POST['source']);
    }
    if (!strlen(trim($_POST['source']))) {
        $_POST['language'] = preg_replace('#[^a-zA-Z0-9\-_]#', '', $_POST['language']);
        $_POST['source'] = implode('', @file($path . 'geshi/' . $_POST['language'] . '.php'));
        $_POST['language'] = 'php';
    } else {
        $fill_source = true;
    }

    // Here's a free demo of how GeSHi works.

    // First the initialisation: source code to highlight and the language to use. Make sure
    // you sanitise correctly if you use $_POST of course - this very script has had a security
    // advisory against it in the past because of this. Please try not to use this script on a
    // live site.
    $geshi = new GeSHi($_POST['source'], $_POST['language']);

    // Use the PRE_VALID header. This means less output source since we don't have to output &nbsp;
    // everywhere. Of course it also means you can't set the tab width.
    // HEADER_PRE_VALID puts the <pre> tag inside the list items (<li>) thus producing valid HTML markup.
    // HEADER_PRE puts the <pre> tag around the list (<ol>) which is invalid in HTML 4 and XHTML 1
    // HEADER_DIV puts a <div> tag arount the list (valid!) but needs to replace whitespaces with &nbsp
    //            thus producing much larger overhead. You can set the tab width though.
    $geshi->set_header_type(GESHI_HEADER_DIV);

    // Enable CSS classes. You can use get_stylesheet() to output a stylesheet for your code. Using
    // CSS classes results in much less output source.
    $geshi->enable_classes();

    // Enable line numbers. We want fancy line numbers, and we want every 5th line number to be fancy
    $geshi->enable_line_numbers(GESHI_FANCY_LINE_NUMBERS, 5);

    // Set the style for the PRE around the code. The line numbers are contained within this box (not
    // XHTML compliant btw, but if you are liberally minded about these things then you'll appreciate
    // the reduced source output).
    $geshi->set_overall_style('font: normal normal 90% monospace; color: #000066; border: 1px solid #d0d0d0; background-color: #f0f0f0;', false);

    // Set the style for line numbers. In order to get style for line numbers working, the <li> element
    // is being styled. This means that the code on the line will also be styled, and most of the time
    // you don't want this. So the set_code_style reverts styles for the line (by using a <div> on the line).
    // So the source output looks like this:
    //
    // <pre style="[set_overall_style styles]"><ol>
    // <li style="[set_line_style styles]"><div style="[set_code_style styles]>...</div></li>
    // ...
    // </ol></pre>
    $geshi->set_line_style('color: #003030;', 'font-weight: bold; color: #006060;', true);
    $geshi->set_code_style('color: #000020;', true);

    // Styles for hyperlinks in the code. GESHI_LINK for default styles, GESHI_HOVER for hover style etc...
    // note that classes must be enabled for this to work.
    $geshi->set_link_styles(GESHI_LINK, 'color: #000060;');
    $geshi->set_link_styles(GESHI_HOVER, 'background-color: #f0f000;');

    // Use the header/footer functionality. This puts a div with content within the PRE element, so it is
    // affected by the styles set by set_overall_style. So if the PRE has a border then the header/footer will
    // appear inside it.
//    $geshi->set_header_content('<SPEED> <TIME> GeSHi &copy; 2004-2007, Nigel McNie, 2007-2008 Benny Baumann. View source of example.php for example of using GeSHi');
    $geshi->set_header_content_style('font-family: sans-serif; color: #808080; font-size: 70%; font-weight: bold; background-color: #f0f0ff; border-bottom: 1px solid #d0d0d0; padding: 2px;');

    // You can use <TIME> and <VERSION> as placeholders
//    $geshi->set_footer_content('Parsed in <TIME> seconds at <SPEED>, using GeSHi <VERSION>');
    $geshi->set_footer_content_style('font-family: sans-serif; color: #808080; font-size: 70%; font-weight: bold; background-color: #f0f0ff; border-top: 1px solid #d0d0d0; padding: 2px;');
    $geshi->set_use_language_tab_width(true);

    if (isset($_POST['submit'])) {
        // Output the stylesheet. Note it doesn't output the <style> tag
        echo $geshi->parse_code();
    }

} else {
    // make sure we don't preselect any language
    $_POST['language'] = null;
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>TGOL Highlighter</title>
</head>
<body>
tgol highlighter
</body>
</html>
<?php
}
?>
