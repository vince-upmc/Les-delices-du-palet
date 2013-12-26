package test;

import java.io.File;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

import com.delices.datastore.Infos;
import com.delices.datastore.jaxb.hierarchy.LeagueType;
import com.delices.datastore.updaters.UrlFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;

public class MyTest {

	public static void main(String[] args) throws JAXBException, SAXException,
			JSONException {

		try {
			System.out.println(UrlFactory.createGameSummaryRequest(
					"c488998b-bc50-4d70-8f14-d0b5b1e7dc2a", Infos.NHL_API_KEY));
		} catch (MalformedURLException e) {
		}

		final JAXBContext jc = JAXBContext
				.newInstance("com.delices.datastore.jaxb.hierarchy");

		Unmarshaller um = jc.createUnmarshaller();

		@SuppressWarnings("unchecked")
		JAXBElement<LeagueType> st = (JAXBElement<LeagueType>) um
				.unmarshal(new File("data/hierarchy.xml"));

		LeagueType s = st.getValue();

		System.out.println(s.getId());
		System.out.println(s.getConference().get(0).getName());

	}
}
