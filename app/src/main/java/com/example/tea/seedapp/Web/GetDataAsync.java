package com.example.tea.seedapp.Web;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Tea on 24.11.2017.
 */

public class GetDataAsync extends AsyncTask<String, Void, JSONArray> {

    public GetDataAsync(DataListener listener){
        this.listener = listener;
    }

    private DataListener listener;

    public interface DataListener{
        void dataReceived(JSONArray data);
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        try {
            return readJsonFromUrl(params[0]);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        listener.dataReceived(result);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
    }
}