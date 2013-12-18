//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 07:47:28 PM CET 
//


package com.delices.datastore.jaxb.team;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for teamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="teamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="venue" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}venueType" minOccurs="0"/>
 *         &lt;element name="hierarchy">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="league" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
 *                   &lt;element name="conference" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
 *                   &lt;element name="division" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="coaches" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="coach" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attGroup ref="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}baseCoachAttributes"/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="players">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="player" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}playerProfileType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}baseTeamAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "teamType", propOrder = {
    "venue",
    "hierarchy",
    "coaches",
    "players"
})
public class TeamType {

    protected VenueType venue;
    @XmlElement(required = true)
    protected TeamType.Hierarchy hierarchy;
    protected TeamType.Coaches coaches;
    @XmlElement(required = true)
    protected TeamType.Players players;
    @XmlAttribute(name = "market")
    protected String market;
    @XmlAttribute(name = "founded")
    protected BigInteger founded;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "alias")
    protected String alias;

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link VenueType }
     *     
     */
    public VenueType getVenue() {
        return venue;
    }

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link VenueType }
     *     
     */
    public void setVenue(VenueType value) {
        this.venue = value;
    }

    /**
     * Gets the value of the hierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link TeamType.Hierarchy }
     *     
     */
    public TeamType.Hierarchy getHierarchy() {
        return hierarchy;
    }

    /**
     * Sets the value of the hierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamType.Hierarchy }
     *     
     */
    public void setHierarchy(TeamType.Hierarchy value) {
        this.hierarchy = value;
    }

    /**
     * Gets the value of the coaches property.
     * 
     * @return
     *     possible object is
     *     {@link TeamType.Coaches }
     *     
     */
    public TeamType.Coaches getCoaches() {
        return coaches;
    }

    /**
     * Sets the value of the coaches property.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamType.Coaches }
     *     
     */
    public void setCoaches(TeamType.Coaches value) {
        this.coaches = value;
    }

    /**
     * Gets the value of the players property.
     * 
     * @return
     *     possible object is
     *     {@link TeamType.Players }
     *     
     */
    public TeamType.Players getPlayers() {
        return players;
    }

    /**
     * Sets the value of the players property.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamType.Players }
     *     
     */
    public void setPlayers(TeamType.Players value) {
        this.players = value;
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
     * Gets the value of the founded property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFounded() {
        return founded;
    }

    /**
     * Sets the value of the founded property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFounded(BigInteger value) {
        this.founded = value;
    }

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

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
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
     *       &lt;sequence>
     *         &lt;element name="coach" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attGroup ref="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}baseCoachAttributes"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "coach"
    })
    public static class Coaches {

        protected List<TeamType.Coaches.Coach> coach;

        /**
         * Gets the value of the coach property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the coach property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCoach().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TeamType.Coaches.Coach }
         * 
         * 
         */
        public List<TeamType.Coaches.Coach> getCoach() {
            if (coach == null) {
                coach = new ArrayList<TeamType.Coaches.Coach>();
            }
            return this.coach;
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
         *       &lt;attGroup ref="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}baseCoachAttributes"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Coach {

            @XmlAttribute(name = "position")
            protected String position;
            @XmlAttribute(name = "id", required = true)
            protected String id;
            @XmlAttribute(name = "first_name")
            protected String firstName;
            @XmlAttribute(name = "last_name")
            protected String lastName;
            @XmlAttribute(name = "full_name")
            protected String fullName;

            /**
             * Gets the value of the position property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPosition() {
                return position;
            }

            /**
             * Sets the value of the position property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPosition(String value) {
                this.position = value;
            }

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
             * Gets the value of the firstName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFirstName() {
                return firstName;
            }

            /**
             * Sets the value of the firstName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFirstName(String value) {
                this.firstName = value;
            }

            /**
             * Gets the value of the lastName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastName() {
                return lastName;
            }

            /**
             * Sets the value of the lastName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastName(String value) {
                this.lastName = value;
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
     *       &lt;sequence>
     *         &lt;element name="league" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
     *         &lt;element name="conference" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
     *         &lt;element name="division" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}organizationType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "league",
        "conference",
        "division"
    })
    public static class Hierarchy {

        @XmlElement(required = true)
        protected OrganizationType league;
        @XmlElement(required = true)
        protected OrganizationType conference;
        @XmlElement(required = true)
        protected OrganizationType division;

        /**
         * Gets the value of the league property.
         * 
         * @return
         *     possible object is
         *     {@link OrganizationType }
         *     
         */
        public OrganizationType getLeague() {
            return league;
        }

        /**
         * Sets the value of the league property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrganizationType }
         *     
         */
        public void setLeague(OrganizationType value) {
            this.league = value;
        }

        /**
         * Gets the value of the conference property.
         * 
         * @return
         *     possible object is
         *     {@link OrganizationType }
         *     
         */
        public OrganizationType getConference() {
            return conference;
        }

        /**
         * Sets the value of the conference property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrganizationType }
         *     
         */
        public void setConference(OrganizationType value) {
            this.conference = value;
        }

        /**
         * Gets the value of the division property.
         * 
         * @return
         *     possible object is
         *     {@link OrganizationType }
         *     
         */
        public OrganizationType getDivision() {
            return division;
        }

        /**
         * Sets the value of the division property.
         * 
         * @param value
         *     allowed object is
         *     {@link OrganizationType }
         *     
         */
        public void setDivision(OrganizationType value) {
            this.division = value;
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
     *       &lt;sequence>
     *         &lt;element name="player" type="{http://feed.elasticstats.com/schema/hockey/team-v2.0.xsd}playerProfileType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "player"
    })
    public static class Players {

        protected List<PlayerProfileType> player;

        /**
         * Gets the value of the player property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the player property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPlayer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PlayerProfileType }
         * 
         * 
         */
        public List<PlayerProfileType> getPlayer() {
            if (player == null) {
                player = new ArrayList<PlayerProfileType>();
            }
            return this.player;
        }

    }

}
