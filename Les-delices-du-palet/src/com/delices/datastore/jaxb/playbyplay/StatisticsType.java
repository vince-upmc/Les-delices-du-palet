//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 07:46:38 PM CET 
//


package com.delices.datastore.jaxb.playbyplay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statisticsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statisticsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="team" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="market" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="player" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="full_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="jersey_number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="strength">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="shorthanded"/>
 *             &lt;enumeration value="powerplay"/>
 *             &lt;enumeration value="even"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statisticsType", propOrder = {
    "team",
    "player"
})
@XmlSeeAlso({
    com.delices.datastore.jaxb.playbyplay.EventType.Statistics.Faceoff.class,
    com.delices.datastore.jaxb.playbyplay.EventType.Statistics.Shot.class,
    com.delices.datastore.jaxb.playbyplay.EventType.Statistics.Shotagainst.class,
    com.delices.datastore.jaxb.playbyplay.EventType.Statistics.Shotmissed.class,
    com.delices.datastore.jaxb.playbyplay.EventType.Statistics.Penalty.class
})
public class StatisticsType {

    protected StatisticsType.Team team;
    protected StatisticsType.Player player;
    @XmlAttribute(name = "strength")
    protected String strength;

    /**
     * Gets the value of the team property.
     * 
     * @return
     *     possible object is
     *     {@link StatisticsType.Team }
     *     
     */
    public StatisticsType.Team getTeam() {
        return team;
    }

    /**
     * Sets the value of the team property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticsType.Team }
     *     
     */
    public void setTeam(StatisticsType.Team value) {
        this.team = value;
    }

    /**
     * Gets the value of the player property.
     * 
     * @return
     *     possible object is
     *     {@link StatisticsType.Player }
     *     
     */
    public StatisticsType.Player getPlayer() {
        return player;
    }

    /**
     * Sets the value of the player property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatisticsType.Player }
     *     
     */
    public void setPlayer(StatisticsType.Player value) {
        this.player = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrength(String value) {
        this.strength = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="full_name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="jersey_number" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Player {

        @XmlAttribute(name = "id", required = true)
        protected String id;
        @XmlAttribute(name = "full_name")
        protected String fullName;
        @XmlAttribute(name = "jersey_number")
        protected String jerseyNumber;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the fullName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * Sets the value of the fullName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullName(String value) {
            this.fullName = value;
        }

        /**
         * Gets the value of the jerseyNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJerseyNumber() {
            return jerseyNumber;
        }

        /**
         * Sets the value of the jerseyNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJerseyNumber(String value) {
            this.jerseyNumber = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="market" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Team {

        @XmlAttribute(name = "id", required = true)
        protected String id;
        @XmlAttribute(name = "market")
        protected String market;
        @XmlAttribute(name = "name")
        protected String name;

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Gets the value of the market property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarket() {
            return market;
        }

        /**
         * Sets the value of the market property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarket(String value) {
            this.market = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }

}
