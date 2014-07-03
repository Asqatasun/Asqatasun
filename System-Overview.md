The following diagram represents the workflow of an audit through the different components that can modify its state and populate/aggregate data : 
![Global architecture of Tanaguru ](http://asset.open-s.com/Doc/archi/global_architecture.png)

***

* The Crawler Component (or content-loader) uses the URL(s) set to the system to fetch the pages and thus form the set of WebResources (URL) and Contents to test.

* The Adapter Component will transform the data to make them parsable (this transformation only concerns CSS, the HTML is treated as is). 

* The Processor Component will then realize the effective audit treatment by executing the Tests on all the Contents. The results are here considered as raw results.

* The next step consists in consolidating these raw results in net (or definitive) results. This is only needed for tests of site scope.

* The results are treated by the Analyser Component to compute the mark and extract statistics.
