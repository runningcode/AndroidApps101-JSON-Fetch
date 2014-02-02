package edu.illinois.coursera.android.json;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import edu.illinois.coursera.android.json.models.Item;

public class ItemListLoader extends AsyncTaskLoader<List<Item>> {

    public ItemListLoader(Context context) {
        super(context);
    }

    @Override
    public List<Item> loadInBackground() {
        try {
            URL url = new URL("https://dl.dropboxusercontent.com/u/7622520/shopping.txt");
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            StringBuilder stringBuilder = new StringBuilder();
            int cp;
            while ((cp = reader.read()) != -1) {
                stringBuilder.append((char) cp);
            }
            inputStream.close();
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            List<Item> result = new ArrayList<Item>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Item item = new Item(jsonArray.getJSONObject(i));
                result.add(item);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
