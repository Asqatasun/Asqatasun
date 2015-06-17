/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rules.doc.utils.rga33.extractor;

import static org.tanaguru.rules.doc.utils.rga33.extractor.Rgaa3Extractor.levelFromCrit;

/**
 *
 * @author jkowalczyk
 */
public class Rule {

//    public String critId;
    public String crit;
    public String ruleDash;
    public String ruleHtmlWithoutLink;
    public String ruleRawHtml;
    public void setRuleRawHtml(String ruleRawHtml) {
        String ruleContent = ruleRawHtml.replaceAll("Test ", "");
        ruleContent = ruleContent.substring(ruleContent.indexOf(" ") + 1).trim();
        if (ruleContent.startsWith(":")) {
            ruleContent = ruleContent.substring(1, ruleContent.length()).trim();
        }
        this.ruleRawHtml = ruleContent;
    }
    public String ruleText;
    public String ruleId;
    public String level;
    public String baseUrl;
    public String refName;

    public Rule(String baseUrl, String refName) {
        super();
        this.baseUrl = baseUrl;
        this.refName = refName;
    }

    public String getLevelId() {
        switch (level) {
            case "AAA":
                return "3";
            case "AA":
                return "2";
            case "A":
                return "1";
            default :
                return "";
        }
    }
    
    public String getCritUrl () {
        return baseUrl+"#crit-"+getCriterion();
    }
    
    public String getTestUrl () {
        return baseUrl+"#"+ruleId;
    }
    
    public String getCriterion () {
        String[] rulesComponent = ruleDash.split("-");
        return rulesComponent[0]+'-'+rulesComponent[1];
    }
    
    public String getCriterionDot () {
        String[] rulesComponent = ruleDash.split("-");
        return rulesComponent[0]+'.'+rulesComponent[1];
    }
    
    public String getCriterionMardown() {
        return "["+getCriterionDot()+"]("+getCritUrl()+")";
    }
    
    public String getTestMardown() {
        return "["+getRuleDot()+"]("+getTestUrl()+")";
    }

    public String getFileName () {
        String[] rulesComponent = ruleDash.split("-");
        StringBuilder fileName = new StringBuilder();
        fileName.append(refName+"Rule");
        if (rulesComponent[0].length() == 1) {
            fileName.append("0");
        }
        fileName.append(rulesComponent[0]);
        if (rulesComponent[1].length() == 1) {
            fileName.append("0");
        }
        fileName.append(rulesComponent[1]);
        if (rulesComponent[2].length() == 1) {
            fileName.append("0");
        }
        fileName.append(rulesComponent[2]);
        return fileName.toString();
    }
    
    public String getRuleDot() {
        return ruleDash.replaceAll("-", "\\.");
    }
    
    public String getRuleKey() {
        return refName+"-"+ruleDash;
    }
    
    public String getTestDescriptionMardown() {
        String markdown = ruleRawHtml.replaceAll("<ul>", "");
        markdown = markdown.replaceAll(" class=\"ssTests\"", "");
        markdown = markdown.replaceAll("<ul>", "");
        markdown = markdown.replaceAll("</ul>", "");
        markdown = markdown.replaceAll("<code>", "`");
        markdown = markdown.replaceAll("</code>", "`");
        markdown = markdown.replaceAll("<li>", "* ");
        markdown = markdown.replaceAll("</li>", "");
        markdown = markdown.replaceAll("&quot;", "\"");
        markdown = markdown.replaceAll("&lt;", "<");
        markdown = markdown.replaceAll("&gt;", ">");
        return markdown;
    }
}