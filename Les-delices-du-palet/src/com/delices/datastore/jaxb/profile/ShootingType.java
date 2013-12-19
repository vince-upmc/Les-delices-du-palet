//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 07:46:49 PM CET 
//


package com.delices.datastore.jaxb.profile;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shootingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shootingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://feed.elasticstats.com/schema/hockey/profile-v2.0.xsd}baseShootingAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shootingType")
public class ShootingType {

    @XmlAttribute(name = "goals")
    protected BigInteger goals;
    @XmlAttribute(name = "assists")
    protected BigInteger assists;
    @XmlAttribute(name = "shots")
    protected BigInteger shots;
    @XmlAttribute(name = "missed_shots")
    protected BigInteger missedShots;

    /**
     * Gets the value of the goals property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGoals() {
        return goals;
    }

    /**
     * Sets the value of the goals property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGoals(BigInteger value) {
        this.goals = value;
    }

    /**
     * Gets the value of the assists property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAssists() {
        return assists;
    }

    /**
     * Sets the value of the assists property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAssists(BigInteger value) {
        this.assists = value;
    }

    /**
     * Gets the value of the shots property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getShots() {
        return shots;
    }

    /**
     * Sets the value of the shots property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setShots(BigInteger value) {
        this.shots = value;
    }

    /**
     * Gets the value of the missedShots property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMissedShots() {
        return missedShots;
    }

    /**
     * Sets the value of the missedShots property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMissedShots(BigInteger value) {
        this.missedShots = value;
    }

}