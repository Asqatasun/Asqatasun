# AccessiWeb 2.2 - Rule 1.5.2

## Summary

This test consists in checking whether an alternative solution is present for each button associated with an image used as Captcha

## Business description

Criterion : 1.5

Test : [1.5.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-5-2)

Test description :

Does each button associated with an image (input tag with the attribute type=&quot;image&quot;) used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) pass one of the conditions below? 
<ul> 
 <li>Another form of [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) at least non graphic, is available</li> 
 <li>Another solution to access the secured functionality of the [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) is available</li> 
</ul>

Level : Bronze

## Technical description

Scope : page

Decision level : semidecidable

## Algorithm

### Selection

**Set1**

If the "captcha" keyword is found on the page, select all the `input` tags not within an `a` tag with an `type` attribute that starts with "image".

**Set2**

Select from elements of Set1, the ones with an attribute or the text that contains the "captcha" keyword or the ones with a parent whom an attribute or the text contains the "captcha" keyword.

### Process

**Test1**

For each element of Set2, raise a MessageA

##### MessageA : Check an alternative of a button associated with an image used as CAPTCHA is present

-   code : CheckCaptchaAlternativeAccess
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

##### Not Applicable

The markup doesn't contain the "captcha" keyword.

Set2 is empty (The page has no `input` tag with a `type` attribute that starts with "image" and with an attribute or a text that contains the "captcha" keyboard) 

##### Pre-Qualified

In all other cases
