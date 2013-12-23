package com.delices.datastore.updaters;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlFactory {

	private final static int VERSION = 3;
	private final static String ACCESSLEVEL = "t";

	public enum NhlSeason {
		PRESEASON, REGULAR, POSTSEASON;
	}

	private static String NhlSeasonToString(NhlSeason t) {
		switch (t) {
		case PRESEASON:
			return "pre";
		case REGULAR:
			return "reg";
		case POSTSEASON:
		default:
			return "pst";
		}
	}

	/**
	 * @TODO un wiki des requêtes
	 */

	public static URL createScheduleRequest(int season, NhlSeason nhl_season,
			String api_key) throws MalformedURLException {
		if (season < 2010 || season > 2014) {
			throw new IllegalArgumentException("Bad season : " + season);
		}

		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/games/" + season + "/"
				+ NhlSeasonToString(nhl_season) + "/schedule.xml?api_key="
				+ api_key);
	}

	public static URL createDailyScheduleRequest(int year, int month, int day,
			String api_key) throws MalformedURLException {
		if (year < 2010 || year > 2014) {
			throw new IllegalArgumentException("Bad year :" + year);
		}
		if (month > 12 || month < 1) {
			throw new IllegalArgumentException("Bad month :" + month);
		}
		if (day > 31 || day < 1) {
			throw new IllegalArgumentException("Bad day :" + day);
		}

		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/" + year + "/" + month + "/" + day
				+ "/schedule.xml?api_key=" + api_key);
	}

	public static URL createSeriesScheduleRequest(int season,
			NhlSeason nhl_season, String api_key) throws MalformedURLException {
		if (season < 2010 || season > 2014) {
			throw new IllegalArgumentException("Bad season : " + season);
		}

		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/games/" + season + "/"
				+ NhlSeasonToString(nhl_season) + "/schedule.xml?api_key="
				+ api_key);
	}

	public static URL createGameBoxscoreRequest(String game_id, String api_key)
			throws MalformedURLException {

		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/games/" + game_id + "/boxscore.xml?api_key="
				+ api_key);
	}

	public static URL createStandingsRequest(int season, NhlSeason nhl_season,
			String api_key) throws MalformedURLException {
		if (season < 2010 || season > 2014) {
			throw new IllegalArgumentException("Bad season : " + season);
		}

		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/seasontd/" + season + "/"
				+ NhlSeasonToString(nhl_season) + "/standings.xml?api_key="
				+ api_key);
	}

	public static URL createLeagueHierarchyRequest(String api_key)
			throws MalformedURLException {
		return new URL("http://api.sportsdatallc.org/nhl-" + ACCESSLEVEL
				+ VERSION + "/league/hierarchy.xml?api_key=" + api_key);
	}

	/** Todo test */

	/**
	 * Todo : Team Profile (Rosters) + Player Profile + Injuries + Game Summary
	 * + Play-By-Play + Seasonal Statistics (Season To Date) + Daily Change Log
	 * + Daily Transfers
	 * 
	 * @see http://developer.sportsdatallc.com/docs/NHL_API
	 */

}
