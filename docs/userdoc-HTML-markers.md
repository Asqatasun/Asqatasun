# Leverage Accessibility automation with HTML Markers

HTML markers are used to give Tanaguru informations. For instance, if you know your
dev team typically codes data table with a `class="data-table"`, just give this 
information to Tanaguru. This way more tests can be achieved.

Markers are available for:

* data tables
* presentation tables
* decorative images
* meaningful images

The value passed as a marker will be checked against the following attributes:

* `class`
* `id`
* `role`