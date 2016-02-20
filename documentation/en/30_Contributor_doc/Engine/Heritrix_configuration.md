# Heritrix configuration

The Crawler Component of Asqatasun is based on Heritrix. Considering the specific 
needs of Asqatasun in terms of contents filtering and performance considerations, 
the default spring configuration file provided by heritrix to define crawl 
properties has to be adapted. The changes are described below, chain by chain.

## Candidate chain configuration

The Candidate chain is composed of 2 components : the scoper and the preparer

### The Scoper

The scoper's work is to define, through a decision rule sequence, the set of 
possible URIs that can be captured (for more informations about the rules, 
please refer to https://webarchive.jira.com/wiki/display/Heritrix/Crawl+Scope).

#### Page Crawl

The Decision Rule Sequence adapted to asqatasun's page crawl needs is defined as follows :

* `RejectDecideRule` with default settings
* `TooManyHopsDecideRule` with "maxHops" property set to 0 (to limit the crawl of the specified url)
* `TransclusionDecideRule` with "maxTransHops" property set to 1 (to deal with redirected pages) and "maxSpeculativeHops" property set to 0.
* `NotOnDomainsDecideRule` with the "decision" property set to REJECT
* `PathologicalPathDecideRule` with default value ("maxRepetition" property set to 2)
* `TooManyPathSegmentsDecideRule` with default value ("maxPathDepth" property set to 15)
* `MatchesListRegexDecideRule` with the "decision" property set to REJECT, the "listLogicalOr" property set to true and the "regexList" property defined as follow :

```
<value>.*(?i)(\.(avi|wmv|mpe?g))$</value>
<value>.*(?i)(\.(rar|zip|tar))$</value>
<value>.*(?i)(\.(doc|xls|odd))$</value>
<value>.*(?i)(\.(xml))$</value>
<value>.*(?i)(\.(txt|conf|pdf))$</value>
<value>.*(?i)(\.(swf))$</value>
<value>.*(?i)(\.(js))$</value>
<value>.*(?i)(\.(bmp|gif|jpe?g|png|svg|tiff?))$</value>
```

* `MatchesListRegexDecideRule` with the "decision" property set to ACCEPT, 
the "listLogicalOr" property set to true and the "regexList" property defined as follow 
to include all CSS contents despite their domain:

```
<value>.*(?i)(\.css(\?.*)?)$</value>
```

* `PrerequisiteAcceptDecideRule` with default settings

#### Site Crawl

The Decision Rule Sequence adapted to asqatasun's page crawl needs is defined as follows

* `RejectDecideRule` with default settings
* `SurtPrefixedDecideRule` with "seedsAsSurtPrefixes" property set to true
* `TooManyHopsDecideRule` with "maxHops" property set to 300 (arbitrary value that correspond to infinite)
* `TransclusionDecideRule` with "maxTransHops" property set to 1 (to deal with redirected pages) and "maxSpeculativeHops" property set to 0.
* `NotOnDomainsDecideRule` with the "decision" property set to REJECT
* `PathologicalPathDecideRule` with default value ("maxRepetition" property set to 2)
* `TooManyPathSegmentsDecideRule` with default value ("maxPathDepth" property set to 15)
* `MatchesListRegexDecideRule` with the "decision" property set to REJECT, the "listLogicalOr" property set to true and the "regexList" property defined as follow :

```
<value>.*(?i)(\.(avi|wmv|mpe?g))$</value>
<value>.*(?i)(\.(rar|zip|tar))$</value>
<value>.*(?i)(\.(doc|xls|odd))$</value>
<value>.*(?i)(\.(xml))$</value>
<value>.*(?i)(\.(txt|conf|pdf))$</value>
<value>.*(?i)(\.(swf))$</value>
<value>.*(?i)(\.(js))$</value>
<value>.*(?i)(\.(bmp|gif|jpe?g|png|svg|tiff?))$</value>
```

* `MatchesListRegexDecideRule` with the "decision" property set to ACCEPT, 
the "listLogicalOr" property set to true and the "regexList" property defined as 
follow to include all css contents despite their domain:

```
<value>.*(?i)(\.css(\?.*)?)$</value>
```

### The preparer

This component is used with default settings.

## Fetch chain configuration

The fetch chain is composed of 5 components (FetchDNS, FetchHttp, ExtractorHttp, 
ExtractorHtml, ExtractorCss) in case of page crawl and 6 components 
(PreconditionEnforcer, FetchDNS, FetchHttp, ExtractorHttp, ExtractorHtml, ExtractorCss)
 in case of site crawl.

The preselector, extractorJs and extractorSwf components have been removed because 
they are not adapted to asqatasun's crawl needs. (for more informations about the
 processors, please refer to https://webarchive.jira.com/wiki/display/Heritrix/Processor+Settings

### PreconditionEnforcer (in case of Site Crawl)

In this component, one property has been overriden :

* The "calculateRobotsOnly" is set to true to exclude Uri's that are excluded by the robots.txt file.

### FetchDNS

This component's work is to realize a dns lookup. One property has been overidden:

* The "digestContent" property set to false (to avoid useless treatments)

### FetchHttp

This component's work is to fetch the content. Three properties have been overriden :

* The "TimeoutSeconds" property set to 10.
* The "defaultEncoding' property set to UTF-8
* The "SoTimeoutMs" property set to 10000
* The "digestContent" property set to false (to avoid useless treatments)

### ExtractorHttp

This component is used with default settings.

### ExtractorHTML

The heritrix HTML extractor has been extended to enable to register a listener. 
Otherwise, this extractor behaves exactly the same way.

Two properties have been been overriden :

* The `extractJavascript` property is set to false
* The `treatFramesAsEmbedLinks` property is set to false to make sure frames are filtered in case of page crawl.

### ExtractorCSS

The heritrix CSS extractor has been extended to enable to register a listener. Otherwise, this extractor behaves exactly the same way.

This component is used with default settings.

## Disposation chain configuration

The disposation chain is composed of 3 components : The writerProcessor, the candidateProcessor and the disposationProcessor

### The WriterProcessor

The original "WarcWriterProcessor" has been replaced by the "TanaguruWriterProcessor".
This module is specific and corresponds to asqatasun's crawl needs. It converts the 
results of successful fetches (raw data) to tanaguru-like Web-resources and Contents.

Two properties are needed to define this processor

* The `htmlRegexp` property defined as follows :

```
<value>.*(?i)(/|\.htm|\.html|\.php|\.asp|\.aspx|\.jsp|\.do)$</value>
```

* The `cssRegexp` property defined as follows 

```
<value>.*(?i)(\.css(\?.*)?)$</value>
```

### The CandidateProcessor

This component is used with default settings.

### The DisposationProcessor

This component's work is to mark-up, late in the processing, the crawl with values
and updating informations.

Three properties have been overriden :

* The `minDelayMs` property is set to 0 to avoid wasting time before recontacting same server (by default, this property is set to 3000 ms)
* The `maxDelayMs` property is set to 1000(ms)  (by default, this property is set to 30000)
* The `respectCrawlDelayUpToSeconds` property set to 5

## Crawl Controller configuration

This controller manages allt the crawl context ; it collects all the processors which cooperate to perform a crawl and provides a high-level interface to the running crawl.

Two properties have been overriden for this component:

* The `PauseAtStart` property set to false
* The `PauseAtFinish` property set to false
* The `maxToeThread` property set to 10 in case of site crawl and set to 3 in case of page crawl.

## The Frontier configuration

This component is used to manage the known hosts (queues) and pending URIs.

Two properties have been overriden for this component:

* The `snoozeLongMs` property set to 0
* The `retryDelaySeconds` property set to 1
* The `maxRetries` set to 3

## Crawl Limiter configuration (in case of Site Crawl)

This module enables to stop the crawl when some configured limits are reached.

Three properties can be set :

* `maxBytesDownload` that limits the number of downloaded bytes
* `maxDocumentsDownload` that limits the number of fetched resources. This property has been set to 10000 in the version1.0.0 of Asqatasun.
* `maxTimeSeconds` that limits the duration of a crawl.


