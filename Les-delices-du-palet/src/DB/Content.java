package DB;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.delices.datastore.PMF;
import com.delices.datastore.contents.Team;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Content {

	@SuppressWarnings("unchecked")
	public static JSONObject getAllTeams() throws JSONException{
		JSONObject result = new JSONObject();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.currentTransaction().begin();
		Query q = pm.newQuery(Team.class);

		for (Team t : (List<Team>) q.execute()) {
			result.put("name", t.getName());
		}

		pm.currentTransaction().commit();
		pm.close();

		return result;
	}
}
