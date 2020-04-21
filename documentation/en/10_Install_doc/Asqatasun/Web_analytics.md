# Adding Web analytics to an Asqatasun installation

You should have already done these steps:

1. [Check Hardware provisioning](Hardware_network_provisioning.md)
2. [Download Asqatasun](Download.md)
3. [Check pre-requisites](Pre-requisites.md)
4. [Install](Installation.md)

## Configurins web analytics (Piwik, Matomo...)

You may add the analytics code in the file `/var/lib/tomcat8/webapps/asqatasun/WEB-INF/view/template/tracker.jsp`

Example of `tracker.jsp` with Matomo

```jsp
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Matomo -->
<script type="text/javascript">
    var _paq = window._paq || [];
    /* tracker methods like "setCustomDimension" should be called before "trackPageView" */
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u="//analytics.asqatasun.example.org/";
        _paq.push(['setTrackerUrl', u+'matomo.php']);
        _paq.push(['setSiteId', '1']);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'matomo.js'; s.parentNode.insertBefore(g,s);
    })();
</script>
<noscript><p><img src="//analytics.asqatasun.example.org/matomo.php?idsite=10&amp;rec=1" style="border:0;" alt="" /></p></noscript>
<!-- End Matomo Code -->
```
