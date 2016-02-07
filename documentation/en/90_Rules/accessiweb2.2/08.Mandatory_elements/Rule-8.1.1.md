# AccessiWeb 2.2 - Rule 8.1.1

## Summary

This tests checks whether a document type is available on the page.

## Business description

Criterion : 8.1

Test : [8.1.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-1-1)

Test description :

For each Web page, is the [document type](http://www.accessiweb.org/index.php/glossary-76.html#mDTD) (`doctype` tag) available?

Level : Bronze

## Technical description

Scope : page

Decision level :
decidable

## Algorithm

### Selection

The `<!doctype>` tag on the page

### Process

The selection handles the process

### Analysis

#### Failed

The page has no `doctype` (the selection is empty)

#### Passed

A `doctype` is available on the page (the selection is not empty)

## Notes

No notes yet for that rule
