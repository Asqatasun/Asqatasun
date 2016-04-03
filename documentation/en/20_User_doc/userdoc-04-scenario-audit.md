<!---
*** WARNING *** WARNING *** WARNING *** WARNING *** WARNING *** WARNING ***

     This page is linked from within the Asqatasun app 
     in /web-app/asqatasun-web-app/src/main/resources/i18n/scenario-management-I18N(..)

     Do not rename, move or delete please :)
     
*** WARNING *** WARNING *** WARNING *** WARNING *** WARNING *** WARNING ***
-->


# Scenario audit of accessibility with Asqatasun

## Quick steps (for the impatient)

1. Have a Firefox and [download SeBuilder extension](http://www.saucelabs.com/addons/selenium-builder-latest.xpi)
1. Record your scenario (*Selenium 2* format)
1. Replay locally your scenario to verify it works
1. Upload the scenario to Asqatasun
1. Run the scenario audit

## 0. Prerequesite: Firefox + SeBuilder

You need a Firefox browser with the [SeBuilder extension](http://www.saucelabs.com/addons/selenium-builder-latest.xpi)
to record scenarios. Asqatasun scenarios are based on Selenium. The format is *Selenium 2* and scenarios are stored in JSON style.

## 1. Define a scenario

Let say we are in London at *Piccadilly Circus* and want to go to *Monument*. Let
use the [Transport for London website](http://www.tfl.gov.uk/) to help us define our journey.
We want to evalutate the accessibility of the following pages:

* the homepage with the form (where we will type in our departure and arrival),
* the result page giving us the different possible paths.

Let's begin.

* Go to [http://www.tfl.gov.uk/](http://www.tfl.gov.uk/)

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_A1_transport_for_london.png)

## 2. Record the scenario

* Open SeBuilder: Tools > Web developer > Launch Selenium Builder (or keyboard 
shortcut `CTRL-ALT-B`), and select *Selenium 2*.

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_A2_SeBuilder_window.png)

* In the *Tranport for London* page, fill in the *From* field (*Piccadilly circus*)
and *To* field (*Monument*)

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_B_filling_form.png)

* Click the *Plan my journey* button, and see the proposed journeys.

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_C_journey_result_page.png)

* In the SeBuilder window, click the *Stop recording* button:

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_D_stop_recording.png)

* Save your scenario : File > Save as...

## 3. Verify and adjust the scenario

* In the SeBuilder window, choose Run > run test locally

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_E_run_test_locally.png)

This verification is important. Asqatasun just replays the scenario, he can't check
whether or not the scenario is actually what you wanted (!).

Please refer to [Asqatasun scenario advanced usage](userdoc-05-scenario-audit-advanced.md)
if the scenario does not replay the way you'd like.

## 4. Upload the scenario to Asqatasun

* Log in your Asqatasun, go to your project, *Scenario Audit*

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_F_Asqatasun_goto_scenario_audit.png)

* Name your scenario, select the file and upload it

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_G_upload_scenario.png)

## 5. Launch the audit of the scenario

* Now run the scenario: click *Launch audit*

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_H_run_scenario.png)

Eventually, set options then launch the audit.

The scenario audit is ran in *asynchronous* mode, meaning you will receive an
email when the audit is done (depending on the lenght of the audit, but typically
a few minutes).

## 6. See the results

* Go back to your project page, click on the last audit 

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_I_last_audits.png)

* You have the summary of the scenario audit. Verify which pages have been tested
by clicking on the number of pages link

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_J_audit_summary.png)

(If the number of pages is not the expected one, please check [Asqatasun scenario advanced usage](userdoc-05-scenario-audit-advanced.md))

![](Images/screenshot_20150309_ASQATASUN_SCENARIO_step_K_list_of_pages.png)

You may then explore the detail of each audited page.

Scenarios are a powerful tool. Once created a scenario can be executed over and
over providing also **regression testing** in addition of accessibility testing.


