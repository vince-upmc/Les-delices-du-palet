package com.delices.datastore.updaters;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import com.delices.utils.Logger;

//to thread?
public class RequestMaker {

	private static Long last_request_time = null;

	public static InputStream makeRequest(URL url) throws IOException,
			InterruptedException {

		Long t = Calendar.getInstance().getTimeInMillis();
		// si il s'est passé moins d'une seconde, on attends
		if (last_request_time != null && t < last_request_time + 1000) {
			Logger.writeLog("Délai trop court entre requêtes, attente");
			// Thread.sleep(last_request_time + 1000 - t);
			// 1s pour être safe
			Thread.sleep(1000);
			Logger.writeLog("Fin d'attente");
		}

		Logger.writeLog("Création d'une requête vers " + url);

		HttpURLConnection con = null;

		try {
			con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			Logger.writeLog("Impossible d'ouvrir une connexion vers " + url);
			return null;
		}

		con.setRequestMethod("GET");

		// Headers
		con.setRequestProperty("User-agent", "Mozilla/5.0");
		con.setRequestProperty("Accept", "application/xml");

		Logger.writeLog("Envoi d'une requête 'GET' sur " + url);
		int respCode = con.getResponseCode();

		if (respCode != HttpURLConnection.HTTP_OK) {
			Logger.writeLog("Echec de la requête : " + url
					+ " - code de retour = " + respCode);
			return null;
		}

		// Update timer
		last_request_time = Calendar.getInstance().getTimeInMillis();

		return con.getInputStream();
	}

}
