## Summary

This test consists in checking whether an `applet` causes a flashing effect or a change of luminance with a period inferior to 3 in a second or whether the covered area of the effects is inferior or equal to 21824 pixels. The implementation consists in checking the presence of the `applet` element on the page.

## Business description

Criterion : 13.15

Test : [13.15.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-15-4)

Test description :

On each Web page, does each java applet causing a sudden change in luminance or a flashing effect pass one of the conditions below?

 * The effect frequency is less than 3 in one second
 * The added up total area of the effects is less than or equal to 21 824 pixels

Level : Bronze

## Technical description

Scope : Page

Decision level : Semi-decidable

## Algorithm

### Selection

Selection1 : All the `applet` tags

### Process

The selection handles the process.

For each occurence of the Selection1 raise a MessageA
###### MessageA : Check Manually the elements

    code :ManualCheckOnElements
    status: NMI
    parameter : snippet, `code` attribute
    present in source : yes

### Analysis

#### Not Applicable

Selection is empty (The page has no `applet` tag)

#### Pre-qualified

The selection is not empty

## Notes
