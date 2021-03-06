//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.18 at 07:47:08 PM CET 
//


package com.delices.datastore.jaxb.standings;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.delices.datastore.jaxb.standings package. 
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

    private final static QName _League_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "league");
    private final static QName _TeamRecordTypeRecordsSoutheast_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "southeast");
    private final static QName _TeamRecordTypeRecordsDivision_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "division");
    private final static QName _TeamRecordTypeRecordsConference_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "conference");
    private final static QName _TeamRecordTypeRecordsLast10Home_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "last_10_home");
    private final static QName _TeamRecordTypeRecordsCentral_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "central");
    private final static QName _TeamRecordTypeRecordsLast10Road_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "last_10_road");
    private final static QName _TeamRecordTypeRecordsLast10_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "last_10");
    private final static QName _TeamRecordTypeRecordsMetropolitan_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "metropolitan");
    private final static QName _TeamRecordTypeRecordsMetro_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "metro");
    private final static QName _TeamRecordTypeRecordsHome_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "home");
    private final static QName _TeamRecordTypeRecordsAtlantic_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "atlantic");
    private final static QName _TeamRecordTypeRecordsNortheast_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "northeast");
    private final static QName _TeamRecordTypeRecordsPacific_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "pacific");
    private final static QName _TeamRecordTypeRecordsNorthwest_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "northwest");
    private final static QName _TeamRecordTypeRecordsRoad_QNAME = new QName("http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", "road");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.delices.datastore.jaxb.standings
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TeamRecordType }
     * 
     */
    public TeamRecordType createTeamRecordType() {
        return new TeamRecordType();
    }

    /**
     * Create an instance of {@link LeagueType }
     * 
     */
    public LeagueType createLeagueType() {
        return new LeagueType();
    }

    /**
     * Create an instance of {@link LeagueType.Season }
     * 
     */
    public LeagueType.Season createLeagueTypeSeason() {
        return new LeagueType.Season();
    }

    /**
     * Create an instance of {@link LeagueType.Season.Conference }
     * 
     */
    public LeagueType.Season.Conference createLeagueTypeSeasonConference() {
        return new LeagueType.Season.Conference();
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
     * Create an instance of {@link InjuryType }
     * 
     */
    public InjuryType createInjuryType() {
        return new InjuryType();
    }

    /**
     * Create an instance of {@link RecordType }
     * 
     */
    public RecordType createRecordType() {
        return new RecordType();
    }

    /**
     * Create an instance of {@link StreakRecordType }
     * 
     */
    public StreakRecordType createStreakRecordType() {
        return new StreakRecordType();
    }

    /**
     * Create an instance of {@link OrganizationType }
     * 
     */
    public OrganizationType createOrganizationType() {
        return new OrganizationType();
    }

    /**
     * Create an instance of {@link TeamRecordType.Streak }
     * 
     */
    public TeamRecordType.Streak createTeamRecordTypeStreak() {
        return new TeamRecordType.Streak();
    }

    /**
     * Create an instance of {@link TeamRecordType.Records }
     * 
     */
    public TeamRecordType.Records createTeamRecordTypeRecords() {
        return new TeamRecordType.Records();
    }

    /**
     * Create an instance of {@link LeagueType.Season.Conference.Division }
     * 
     */
    public LeagueType.Season.Conference.Division createLeagueTypeSeasonConferenceDivision() {
        return new LeagueType.Season.Conference.Division();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeagueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "league")
    public JAXBElement<LeagueType> createLeague(LeagueType value) {
        return new JAXBElement<LeagueType>(_League_QNAME, LeagueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "southeast", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsSoutheast(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsSoutheast_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "division", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsDivision(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsDivision_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "conference", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsConference(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsConference_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "last_10_home", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsLast10Home(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsLast10Home_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "central", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsCentral(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsCentral_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "last_10_road", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsLast10Road(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsLast10Road_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "last_10", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsLast10(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsLast10_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "metropolitan", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsMetropolitan(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsMetropolitan_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "metro", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsMetro(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsMetro_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "home", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsHome(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsHome_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "atlantic", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsAtlantic(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsAtlantic_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "northeast", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsNortheast(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsNortheast_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "pacific", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsPacific(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsPacific_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "northwest", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsNorthwest(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsNorthwest_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://feed.elasticstats.com/schema/hockey/nhl/standings-v2.0.xsd", name = "road", scope = TeamRecordType.Records.class)
    public JAXBElement<RecordType> createTeamRecordTypeRecordsRoad(RecordType value) {
        return new JAXBElement<RecordType>(_TeamRecordTypeRecordsRoad_QNAME, RecordType.class, TeamRecordType.Records.class, value);
    }

}
