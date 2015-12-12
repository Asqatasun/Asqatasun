# AccessiWeb 2.2 - Rule 1.5.1

## Summary

This test consists in checking whether an alternative solution is present for each image used as Captcha

## Business description

Criterion : 1.5

Test : [1.5.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-5-1)

Test description :

Does each image (img, area, applet, object, embed tags) used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) pass one of the conditions below?

-   Another form of [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) at least non grafic is available
-   Another solution to access the secured functionality of the [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) is available

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1**

If the "captcha" keyword is found on the page, select all the `img` tags not within an `a` tag.

**Set2**

If the "captcha" keyword is found on the page, select all the `area` tags not within an `a` tag.

**Set3**

If the "captcha" keyword is found on the page, select all the `applet` tags not within an `a` tag.

**Set4**

If the "captcha" keyword is found on the page, select all the `object` tags not within an `a` tag, with a `type` attribute that starts with "image".

**Set5**

If the "captcha" keyword is found on the page, select all the `embed` tags not within an `a` tag, with a `type` attribute that starts with "image".

**Set6**

Select from elements of Set1, Set2, Set3, Set4 and Set5, the ones with an attribute or the text that contains the "captcha" keyword or the ones with a parent whom an attribute or the text contains the "captcha" keyword.

### Process

**Test1**

For each element of Set6, raise a MessageA

##### MessageA : Check an alternative of an image used as CAPTCHA is present

-   code : CheckCaptchaAlternativeAccess
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

##### Not Applicable

The markup doesn't contain the "captcha" keyword.

Set6 is empty (The page has no image with an attribute or a text that contains the "captcha" keyboard) 

##### Pre-Qualified

In all other cases
