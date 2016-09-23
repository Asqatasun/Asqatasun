# Treatment of generated HTML and DOM

<!---
*** WARNING *** WARNING *** WARNING *** WARNING *** WARNING *** WARNING ***
This page is linked from within the Asqatasun app (in asqatasun.conf). Do not rename nor delete please :)
-->

Since version 2.0, Asqatasun works on the "generated HTML": this refers to the HTML generated after JavaScript changes the DOM. It is the current DOM tree instead of the original source code. In other words, it corresponds to what the user can see in his browser.

This evolution may impact the results as follows :

* The tested code is different from the one you can get from a "Ctrl+U" on the browser (or from a telnet).
* The tags `<noembed>`, `<noscript>`, `<noframes>` are now ignored. Asqatasun behaves like a browser to retrieve the code to audit. As a browser it considers the content of the `<embed>`, `<script>` and `<frame>` tags and ignore their alternatives.
* If some resources of the page exceed an arbitrary delay (set to 10 seconds by default) to be retrieved, they are not taken into account, and the tested DOM may differ from the one the user can see in the browser.
