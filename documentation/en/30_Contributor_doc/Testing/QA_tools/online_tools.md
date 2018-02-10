
# QA - Online tools
`*` preconfigured tools

* Security
    * [Hardenize](https://www.hardenize.com) (DNS, SMTP, web server)
    * [Mozilla Observatory](https://observatory.mozilla.org/analyze.html?host=app.asqatasun.org) `*` (HTTP header, SSL, cookies, ...)
    * [Security Headers](https://securityheaders.io/?q=https://app.asqatasun.org) `*` (HTTP header)
    * Content-Security-Policy (CSP)
        * [cspvalidator.org](https://cspvalidator.org/#url=https://app.asqatasun.org) `*` 
        * [csp-evaluator.withgoogle.com](https://csp-evaluator.withgoogle.com/?csp=https://app.asqatasun.org) `*` 
    * SSL
        * [ssllabs.com](https://www.ssllabs.com/ssltest/analyze?d=app.asqatasun.org) `*` 
        * [tls.imirhil.fr](https://tls.imirhil.fr/https/app.asqatasun.org) `*` 
* W3C tools
    * [HTML validator](https://validator.w3.org/nu/?doc=https://app.asqatasun.org&showsource=yes&showoutline=yes&showimagereport=yes) `*` 
    * [CSS validator](https://jigsaw.w3.org/css-validator/validator?uri=https://app.asqatasun.org&profile=css3) `*` 
    * [Link checker](https://validator.w3.org/checklink?uri=https://app.asqatasun.org&hide_type=all&depth=&check=Check) `*` 
    * [i18n checker](https://validator.w3.org/i18n-checker/check?uri=https://app.asqatasun.org) `*`
        * [i18n checker for **/?lang=en**](https://validator.w3.org/i18n-checker/check?uri=https://app.asqatasun.org/?lang=en) `*`
        * [i18n checker for **/?lang=fr**](https://validator.w3.org/i18n-checker/check?uri=https://app.asqatasun.org/?lang=fr) `*`
        * [i18n checker for **/?lang=es**](https://validator.w3.org/i18n-checker/check?uri=https://app.asqatasun.org/?lang=es) `*`
* Web accessibility
    * [Asqatasun](https://app.asqatasun.org)
* Web perf
    * [Yellowlab](http://yellowlab.tools)
    * [Webpagetest](https://www.webpagetest.org/)
    * [Test a single asset from 14 locations](https://tools.keycdn.com/performance?url=https://app.asqatasun.org) `*`
* HTTP/2
    * [Http2.pro](https://http2.pro/check?url=https://app.asqatasun.org) `*` (check server HTTP/2, ALPN, and Server-push support)
* Global tools (webperf, accessibility, security, ...)
    * [Dareboost](https://www.dareboost.com)  (free trial)
    * [Sonarwhal](https://sonarwhal.com/scanner/)

* Social networks
  * [Twitter card validator](https://cards-dev.twitter.com/validator)
* structured data (JSON-LD, rdf, schema.org, microformats.org, ...)
  * [Google structured data testing tool](https://search.google.com/structured-data/testing-tool#url=https://app.asqatasun.org/)  `*` 
  * [Yandex structured data testing tool](https://webmaster.yandex.com/tools/microtest/)
  * [Structured Data Linter](http://linter.structured-data.org/?url=https://app.asqatasun.org)  `*` 
  * [Microdata Parser](http://tools.seomoves.org/microdata/)
* Google image
  * [images used on the website](https://www.google.fr/search?tbm=isch&q=site:app.asqatasun.org)  `*`  (site:app.asqatasun.org)
  * [images used on the website but hosted on other domains](https://www.google.fr/search?tbm=isch&q=site:app.asqatasun.org+-src:app.asqatasun.org) `*`  (site:app.asqatasun.org -src:app.asqatasun.org)
  * [images hosted on the domain name](https://www.google.fr/search?tbm=isch&q=src:app.asqatasun.org)  `*`    (src:app.asqatasun.org)
  * [images hosted on the domain name and used by other domain names (hotlinks)](https://www.google.fr/search?tbm=isch&q=src:app.asqatasun.org+-site:app.asqatasun.org)  `*`   (src:app.asqatasun.org -site:app.asqatasun.org)


