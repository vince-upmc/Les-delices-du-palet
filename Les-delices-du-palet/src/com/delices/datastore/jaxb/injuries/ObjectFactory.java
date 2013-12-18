//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 07:46:29 PM CET 
//


package com.delices.datastore.jaxb.injuries;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.delices.datastore.jaxb.injuries package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _League_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/injuries-v2.0.xsd", "league");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.delices.datastore.jaxb.injuries
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LeagueType }
     * 
     */
    public LeagueType createLeagueType() {
        return new LeagueType();
    }

    /**
     * Create an instance of {@link PlayerType }
     * 
     */
    public PlayerType createPlayerType() {
        return new PlayerType();
    }

    /**
     * Create an instance of {@link DraftType }
     * 
     */
    public DraftType createDraftType() {
        return new DraftType();
    }

    /**
     * Create an instance of {@link VenueType }
     * 
     */
    public VenueType createVenueType() {
        return new VenueType();
    }

    /**
     * Create an instance of {@link TeamType }
     * 
     */
    public TeamType createTeamType() {
        return new TeamType();
    }

    /**
     * Create an instance of {@link InjuryType }
     * 
     */
    public InjuryType createInjuryType() {
        return new InjuryType();
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link LeagueType.Injuries }
     * 
     */
    public LeagueType.Injuries createLeagueTypeInjuries() {
        return new LeagueType.Injuries();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeagueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/injuries-v2.0.xsd", name = "league")
    public JAXBElement<LeagueType> createLeague(LeagueType value) {
        return new JAXBElement<LeagueType>(_League_QNAME, LeagueType.class, null, value);
    }

}
