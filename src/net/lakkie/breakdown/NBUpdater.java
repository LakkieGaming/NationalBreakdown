package net.lakkie.breakdown;

import java.util.function.Consumer;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class NBUpdater implements Consumer<Object>
{

	public static final String API_URL = "https://lakkie.net/core/app/callfunc.php?func-name=%s&app-id=%s";
	public static final String UPDATE_URL = "https://lakkie.net/software.php?download=list&app-id=%s";
	public static final String UPDATER_AGENT = "NB/Updater";
	public static final String API_APP_ID = "nat-bre";

	private NBUpdaterResult result = new NBUpdaterResult();

	private NBUpdater()
	{
	}

	public static NBUpdaterResult getLatest() throws Exception
	{

		Content resultContent = Request.Get(String.format(API_URL, "getversions", API_APP_ID)).execute().returnContent();

		String result = resultContent.asString();

		JSONObject object = new JSONObject(result);

		if (object.getInt("status") != 0) { throw new HttpResponseException(object.getInt("status"), "Error with response: " + object.getString("details")); }

		JSONArray versions = object.getJSONArray("versions");

		NBUpdater resultUpdater = new NBUpdater();
		versions.forEach(resultUpdater);
		return resultUpdater.result;
	}

	public void accept(Object t)
	{
		JSONObject obj = (JSONObject) t;
		if (obj.getInt("version_status") == 1)
		{
			this.result.setRecommendedVersionName(obj.getString("version_name"));
			this.result.setRecommendedVersionID(obj.getString("version_id"));
		}
		if (obj.getInt("version_status") == 2 && this.result.getRecommendedVersionID() == null)
		{
			this.result.setRecommendedVersionName(obj.getString("version_name"));
			this.result.setRecommendedVersionID(obj.getString("version_id"));
		}
	}

}
