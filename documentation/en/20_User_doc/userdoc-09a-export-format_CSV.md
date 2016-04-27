# CSV export format description

## Important foreword

To understand how CSV export is crafted, we **strongly** suggest you read
[Structure of a result](userdoc-02b-structure_of_a_result.md). This describes
how Asqatasun results are organised. Once you get this, export formats
will be quite straightforward to understand.

## Structure of CSV export

CSV export is composed of many fields, and some of those fields may contain 
nested CSV-like data. Here is the global structure:

```
data ,
data ,
data ,
    data nesting level 1 ø
    data nesting level 1 ø
    data nesting level 1 ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        data nesting level 2 ;
    } ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        data nesting level 2 ;
    } ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        [
            data nesting level 3 |
            data nesting level 3 |
            data nesting level 3 |
        ] ;
    }
```

Nesting level 0:

* separator: comma `,`

Nesting level 1:

* separator: character `ø`

Nesting level 2:

* enclosing characters: curly brackets `{` and `}` 
* separator: semi-colon `;`

Nesting level 3:

* enclosing characters: brackets `[` and `]` 
* separator: pipe `|`

One may say: this blob looks like Json put into CSV, well that's not false :)

## Exported data

CSV format contains the following fields (starting from row 3, two first
lines being used for date and URL of audited page):

1. Theme
2. Criterion
3. Test
4. Test label
5. Level
6. Result
7. Details

## Theme

Theme is the name of the topic. 

Example for *RGAA 3* referential:

* `Images`
* `Frames`
* `Colours`

## Criterion

Criterion is the reference of the criterion

Example for *RGAA 3* referential:

* `1.1`
* `1.2`
* `8.9`
* `13.7`

## Test

Test is the reference of the test. 

Example for *RGAA 3* referential:

* `1.1.1`
* `1.2.1`
* `8.9.1`
* `13.7.1`

## Test Label

Test Label is the complete description fo the test (from the official referential).

Example for test 1.1.1 from *RGAA 3* referential:

> Does each decorative image without legend (tag `img`) and with an `alt` attribute meet all these requirements?<ul><li>the content of the `alt` attribute is empty (`alt=""`)</li><li>the decorative image has no `title` attribute</li></ul>

## Level

Level is the priority of the test.

Examples for *RGAA 3* referential:

* `LEVEL_1` (equivalent of priority "A")
* `LEVEL_2` (equivalent of priority "AA")
* `LEVEL_3` (equivalent of priority "AAA")

## Result

Result is the actual result of the test. It can be one of:

* Passed
* Failed
* Not Applicable
* Pre-Qualified
* Not Tested

## Details

*Details* store the *Process Remarks* and their *Evidence Elements* (you already know
 these concepts as you read [Structure of a result](userdoc-02b-structure_of_a_result.md) ;) ).
*Details* contain various data that are structured in nested CSV-like structure (presented below).

```
Theme data ,
Criterion data ,
Test data ,
Test label data ,
Level data ,
Result data ,
    Process Remarks count ø
    Evidence elements headers ø
    Instance of Evidence element ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        data nesting level 2 ;
    } ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        data nesting level 2 ;
    } ø
    {
        data nesting level 2 ;
        data nesting level 2 ;
        [
            data nesting level 3 |
            data nesting level 3 |
            data nesting level 3 |
        ] ;
    }
```

...and organised this way:
 
* Field 1:
    * 0 or N,
    * N being the number of *Process Remarks
* Field 2: header description for the *Process Remarks* to follow on next lines, enclosed into curly brackets `{` and `}` and separated by semi-colons `;`
    * `key`: the *message_key* raised to the user (which is in fact the key used for internationalisation)
    * `result`: `failed` or `nmi` (nmi, "need more information", was the old name for Pre-Qualified)
    * `target`: the HTML element concerned
    * `evidenceElementCounter`: number or *Evidence Elements*
    * `evidenceElementStructure`: 
    * `evidenceElementList`
* Field 3 to 3+N: actual content, structured according to the headers (Field 2)

## Example 1 for Details

Here is an extract of a CVS export, focused on test 2.2.1 (relevancy of `title` attribute content on `iframes`).
This example is the one used in [Structure of a result](userdoc-02b-structure_of_a_result.md).  

```
Frames,2.2,2.2.1,"For each iframe (tag <code>iframe</code>) with a <code>title</code> attribute, is the content of this attribute relevant?",LEVEL_1,Failed,2ø{key;result;target;evidenceElementCounter;evidenceElementStructure;evidenceElementList}ø{NotPertinentTitleOfIframe;failed;iframe;1;[title|Snippet|Line-Number];[|&lt\;iframe width=&quot\;0&quot\; height=&quot\;0&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8__hidden__&quot\; title=&quot\;&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8__hidden__&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\; visibility: hidden\; display: none\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|203]}ø{CheckTitleOfIframePertinence;nmi;iframe;11;[title|Snippet|Line-Number];[Online Quality Survey|&lt\;iframe frameborder=&quot\;0&quot\; name=&quot\;edr_l_first&quot\; id=&quot\;edr_l_first&quot\; src=&quot\;http://edigitalsurvey.com/l.php?id=INS-vt29-666188954&amp\;amp\;v=7215&amp\;amp\;x=1280&amp\;amp\;y=1024&amp\;amp\;d=8&amp\;amp\;c=null&amp\;amp\;ck=1&amp\;amp\;p=%2F&amp\;amp\;xdm=edr&amp\;amp\;xdm_o=http%3A%2F%2Fwww.bbc.com&amp\;amp\;xdm_c=edr0&quot\; scrolling=&quot\;no&quot\; title=&quot\;Online Quality Survey&quot\;&gt\;&lt\;/iframe&gt\;|192];[empty|&lt\;iframe width=&quot\;0&quot\; height=&quot\;0&quot\; id=&quot\;99bd57cd-6ed5-7770-33a9-fc5325245085&quot\; title=&quot\;empty&quot\; style=&quot\;display: none\;&quot\; src=&quot\;http://s.effectivemeasure.net/html/frame_2.3.3.html&quot\;&gt\;&lt\;/iframe&gt\;|195];[3rd party ad content|&lt\;iframe width=&quot\;1&quot\; height=&quot\;1&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|201];[3rd party ad content|&lt\;iframe width=&quot\;728&quot\; height=&quot\;90&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_1&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_1&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|565];[3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;250&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_3&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_3&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|701];[3rd party ad content|&lt\;iframe width=&quot\;100%&quot\; height=&quot\;370&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_2&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_2&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|935];[3rd party ad content|&lt\;iframe width=&quot\;88&quot\; height=&quot\;31&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_5&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_5&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|974];[3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;251&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_4&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_4&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|989];[3rd party ad content|&lt\;iframe width=&quot\;88&quot\; height=&quot\;31&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_6&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_6&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1023];[3rd party ad content|&lt\;iframe width=&quot\;976&quot\; height=&quot\;400&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_0&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_0&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1286];[3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;251&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_7&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_7&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1569]}
```

...which gives reformated and explained:

* **Theme:** Frames, 
* **Criterion:** 2.2,
* **Test:** 2.2.1,
* **Test detail:** "For each iframe (tag <code>iframe</code>) with a <code>title</code> attribute, is the content of this attribute relevant?"
* **Level:** LEVEL_1 (i.e. Priority A),
* **Result:** Failed,
* **Details**:
    * 2
    * ø{key; result; target; evidenceElementCounter; evidenceElementStructure; evidenceElementList}
    * ø{
        * NotPertinentTitleOfIframe;
        * failed;
        * iframe;
        * 1;
        * \[title|Snippet|Line-Number\];
        * \[|&lt\;iframe width=&quot\;0&quot\; height=&quot\;0&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8__hidden__&quot\; title=&quot\;&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8__hidden__&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\; visibility: hidden\; display: none\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|203\]
        }
    * ø{
        * CheckTitleOfIframePertinence;
        * nmi;
        * iframe;
        * 11;
        * [title|Snippet|Line-Number];
        * [Online Quality Survey|&lt\;iframe frameborder=&quot\;0&quot\; name=&quot\;edr_l_first&quot\; id=&quot\;edr_l_first&quot\; src=&quot\;http://edigitalsurvey.com/l.php?id=INS-vt29-666188954&amp\;amp\;v=7215&amp\;amp\;x=1280&amp\;amp\;y=1024&amp\;amp\;d=8&amp\;amp\;c=null&amp\;amp\;ck=1&amp\;amp\;p=%2F&amp\;amp\;xdm=edr&amp\;amp\;xdm_o=http%3A%2F%2Fwww.bbc.com&amp\;amp\;xdm_c=edr0&quot\; scrolling=&quot\;no&quot\; title=&quot\;Online Quality Survey&quot\;&gt\;&lt\;/iframe&gt\;|192];
        * [empty|&lt\;iframe width=&quot\;0&quot\; height=&quot\;0&quot\; id=&quot\;99bd57cd-6ed5-7770-33a9-fc5325245085&quot\; title=&quot\;empty&quot\; style=&quot\;display: none\;&quot\; src=&quot\;http://s.effectivemeasure.net/html/frame_2.3.3.html&quot\;&gt\;&lt\;/iframe&gt\;|195];
        * [3rd party ad content|&lt\;iframe width=&quot\;1&quot\; height=&quot\;1&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_8&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|201];
        * [3rd party ad content|&lt\;iframe width=&quot\;728&quot\; height=&quot\;90&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_1&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_1&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|565];
        * [3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;250&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_3&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_3&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|701];
        * [3rd party ad content|&lt\;iframe width=&quot\;100%&quot\; height=&quot\;370&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_2&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_2&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|935];
        * [3rd party ad content|&lt\;iframe width=&quot\;88&quot\; height=&quot\;31&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_5&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_5&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|974];
        * [3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;251&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_4&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_4&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|989];
        * [3rd party ad content|&lt\;iframe width=&quot\;88&quot\; height=&quot\;31&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_6&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_6&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1023];
        * [3rd party ad content|&lt\;iframe width=&quot\;976&quot\; height=&quot\;400&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_0&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_0&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1286];
        * [3rd party ad content|&lt\;iframe width=&quot\;300&quot\; height=&quot\;251&quot\; frameborder=&quot\;0&quot\; id=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_7&quot\; title=&quot\;3rd party ad content&quot\; name=&quot\;google_ads_iframe_/4817/bbccom.live.site.www/bbc_homepage_int_7&quot\; scrolling=&quot\;no&quot\; marginwidth=&quot\;0&quot\; marginheight=&quot\;0&quot\; style=&quot\;border: 0px none\; vertical-align: bottom\;&quot\; src=&quot\;javascript:&amp\;quot\;&amp\;lt\;html&amp\;gt\;&amp\;lt\;body style='background:transparent'&amp\;gt\;&amp\;lt\;/body&amp\;gt\;&amp\;lt\;/html&amp\;gt\;&amp\;quot\;&quot\;&gt\;&lt\;/iframe&gt\;|1569]}

## Example 1 for Details

Here is an extract of a CVS export, this line has a non-empty Details field  

```
Colours,3.3,3.3.1,"On each web page, <strong>until 150%</strong> of the default font size(ou 1.5em), do text and image of text without bold type meet one these requirements (except in special cases)?<ul><li>The contrast ratio between the text and its background is at least 4.5:1</li><li>A mechanism allows the user to display the text with a contrast ratio of at least 4.5:1</li></ul>",LEVEL_2,Pre-qualified,1ø{key;result;target;evidenceElementCounter;evidenceElementStructure;evidenceElementList}ø{CheckTheContrastOfImagesWithText;nmi;;0;;}
```

...which gives reformated and explained:

* **Theme:** Colours,
* **Criterion:** 3.3,
* **Test:** 3.3.1,
* **Test detail:** "On each web page, <strong>until 150%</strong> of the default font size(ou 1.5em), do text and image of text without bold type meet one these requirements (except in special cases)?<ul><li>The contrast ratio between the text and its background is at least 4.5:1</li><li>A mechanism allows the user to display the text with a contrast ratio of at least 4.5:1</li></ul>",
* **Level:** LEVEL_2,
* **Result:** Pre-qualified,
* **Details**:
    * 1
    * ø{key; result; target; evidenceElementCounter; evidenceElementStructure; evidenceElementList}
    * ø{CheckTheContrastOfImagesWithText; nmi; ; 0; ; }

Having a closer look to *Details* field, we observe this:

* N=1, so we will have 1 line of actual data
* The header line is present
* Line 3 gives the actual data structured against the headers
