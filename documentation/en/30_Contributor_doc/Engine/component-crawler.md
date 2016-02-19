# Asqatasun Developer documentation

## Component Description

This component is responsible for the fetch of all the pages to be tested. 
In case of page audit, only one page (corresponding to the filled-in URL) is fetched. 
In case of site audit, the whole site is fetched (regarding limitations like depth, maximum number of pages or maximum duration)

### Input

Url(s)

### Output

One or several WebResource (that hosts the URL of the element) and the corresponding contents (that hosts the content of the page)

## Introduction to Heritrix

Heritrix is the Internet Archive's open-source, extensible, Web-scale, archival-quality Web crawler. It is used as crawler for *site-wide* audits in Asqatasun.

Heritrix is Spring-container-based configuration software which enables to tune settings values and integrate alternate compatible implementations of components.

## Heritrix Architecture

Much of the crawler's work is specified by the sequential application of swappable Processor modules. These Processors are collected into three "chains:"

1. CandidateChain - This chain is applied to URIs being considered for inclusion in the crawl, before a URI is enqueued for collection.
1. FetchChain - This chain is applied to URIs during collection.
1. DispositionChain - This chain is applied after a URI is fetched, analyzed, and link-extracted.

These chains are fed by a Frontier

![Heritrix processor chains](HeritrixProcessorChains.png)