<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Author" content="Open-S.com // Matthieu Faure" lang="fr" />
        <title>Tanaguru home</title>
        <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.0r4/build/reset-fonts/reset-fonts.css&amp;2.8.0r4/build/base/base-min.css"/>
        <link rel="stylesheet" type="text/css" href="Css/tanaguru.css" />
        <script type="text/javascript" src="Js/tanaguru.js"></script>
    </head>
    <body id="tanaguru-home">
    <div id="doc2">
        <div id="hd">
            <h1>Hello World Tanaguru !</h1>
        </div>

        <div id="bd">
        <p>Please enter the page URL to check</p>

        <form action="AuditLauncher" id="submit-form" method="post">
            <div>
                <input type="text" title="Page URL to check" name="pageUrl" id="pageUrl"/>
                <select id="reference-select" title="Accessibility reference">
                    <option selected="selected">AccessiWeb 2.0</option>
                    <option disabled="disabled">RGAA 2.2.1</option>
                </select>
                <%--<input type="submit" value="Check accessibility" id="input-submit" class="large red awesome"/>--%>
                <button type="submit" id="input-submit" class="red awesome">Check accessibility &raquo;</button>
            </div>
        </form>
        </div>

        <div id="ft">
            &copy; <a href="http://www.Open-S.com/">Open-S</a>
        </div>
    </div>
    </body>
</html>

