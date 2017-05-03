package com.example.gustavo.whowroteit;

import android.content.ClipData;
import android.os.AsyncTask;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.BitSet;

/**
 * Created by Gustavo on 01/05/2017.
 */

public class FetchBook extends AsyncTask<String,Void,String> {
    private TextView mTitleText;
    private TextView mAuthorText;
    private BitSet itemsArray;

    public FetchBook(Object mTitleText, Object mAuthorText) {
    }

    @Override
    protected String doInBackground(String... params) {

        return null;
    }

    protected void onPostExecute(String s){
        super.onPostExecute(s);
        //iterate throght the results

        for(int i = 0; i<itemsArray.length(); i++){
            JSONObject book= new itemsArray.getJSONObject();//GET the current item
            String title=null;
            String author =null;
            JSONObject volumeInfo= null;
            try {
                volumeInfo = book.getJSONObject("volumeInfo");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try{
                title=volumeInfo.getString("title");
                author = volumeInfo.getString("authors");

            }catch(Exception e){
                e.printStackTrace();
            }

           //if Both a title  andauthor  exist, update  the TextViewa and returns
            if (title !=null && author !=null){
                mTitleText.setText(title);
                mAuthorText.setText(author);
                return;
            }


        }

    }
}
