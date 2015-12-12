## Summary

This test consists in checking the pertinence of the altenative associated with an embedded image used as a CAPTCHA.

## Business description

Criterion : 1.4

Test : [1.4.6](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-6)

Test description :

For each embedded image (embed tag with the attribute type=&quot;image/...&quot;) used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) or as [test image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest) and with a text alternative, does the [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg) between &lt;noembed&gt; and &lt;/noembed&gt; allow to identify the kind and the purpose of the image?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1**

If the "captcha" keyword is found on the page, select all the `embed` tags not within an `a` tag (in this case, the embedded image would be considered as a link) with an `type` attribute that starts with "image".

**Set2**

Select from elements of Set1, the ones with an attribute or the text that contains the "captcha" keyword or the ones with a parent whom an attribute or the text contains the "captcha" keyword.

### Process

**Test1**

For each element of Set2, raise a MessageA

##### MessageA : Check the pertinence of the alternative of the embedded image used as a CAPTCHA. 

-   code : CheckCaptchaAlternative
-   status: Pre-Qualified
-   parameter : `alt` attribute, `src` attribute, Snippet
-   present in source : yes

### Analysis

##### Not Applicable

The markup doesn't contain the "captcha" keyword.

Set2 is empty (The page has no `embed` tag with a `type` attribute that starts with "image" and with an attribute or a text that contains the "captcha" keyboard) 

##### Pre-Qualified

In all other cases
