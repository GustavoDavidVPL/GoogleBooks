package com.example.gustavo.whowroteit;

import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.util.Log.*;

/**
 * Created by Gustavo on 02/05/2017.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName ();
    private static int[] params;

    static String getBookInfo (int queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            //Aumente su URI de solicitud en el bloque try
            //Build  up your query URI, limiting results to 10 items ans printed books
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, String.valueOf(queryString))
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            //Convierta su URI a una URL
            URL requestURL = new URL(builtURI.toString());
            //En el bloque try del método getBookInfo (), abra la conexión URL y haga la solicitud:
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //Lea la respuesta utilizando un InputStream y un StringBuffer, y luego convertirlo a String:
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                //nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "/n");

            }
            if (buffer.length() == 0) {
                //stream was empty. nopoint in parsing.
                return null;

            }
            bookJSONString = buffer.toString();


        } catch (Exception ex) {
            //Cierre el bloque try y registre la excepción en el bloque catch.
            ex.printStackTrace();
            return null;

        } finally {
            //Cierre las variables urlConnection y reader en el bloque finall

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return bookJSONString;
        }
    }

        //Registre el valor de la variable bookJSONString antes de devolverlo. Ahora se hace con el método getBookInfo ().}



    private static String doInBackground() {
        return	NetworkUtils.getBookInfo(params[0]);
    }

    private static final String BOOK_BASE_URL= "http//www.googleapis.com/books/v1/volumenes?"; //Base URI for the books Api
    private static final String QUERY_PARAM="q";// parameter for the search String
    private static final String MAX_RESULTS ="maxresults";//parameter that limits search results
    private static final String PRINT_TYPE= "printType";//  parameter to filter by print type


}
