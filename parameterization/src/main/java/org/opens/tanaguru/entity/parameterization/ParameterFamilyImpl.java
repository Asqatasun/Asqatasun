/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.parameterization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "PARAMETER_FAMILY")
@XmlRootElement
public class ParameterFamilyImpl implements ParameterFamily, Serializable{

    private static final long serialVersionUID = -6433539300057498390L;

    @Id
    @GeneratedValue
    @Column(name = "Id_Parameter_Family")
    private Long id;

    @Column(name = "Cd_Parameter_Family")
    private String paramFamilyCode;

    @Column(name = "Description")
    private String description;

    @Column(name = "Short_Label")
    private String shortLabel;

    @Column(name = "Long_Label")
    private String longLabel;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getParameterFamilyCode() {
        return paramFamilyCode;
    }

    @Override
    public void setParameterFamilyCode(String parameterFamilyCode) {
        this.paramFamilyCode = parameterFamilyCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    @Override
    public String getShortLabel() {
        return shortLabel;
    }

    @Override
    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

}