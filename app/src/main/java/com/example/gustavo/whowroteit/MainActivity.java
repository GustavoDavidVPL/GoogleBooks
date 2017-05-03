package com.example.gustavo.whowroteit;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String EditText,TextView;
    private Object mTitleText, mAuthorText,mQueryString ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void searchBooks (){
        /* En el método searchBooks (), obtenga el texto del widget EditText
         y convierta a String, asignándolo a una variable de cadena.*/

        android.widget.TextView mBookInput = null;
        String querysString= mBookInput.getText().toString();
        /*mBookInput.getText () devuelve un tipo de datos "Editable" que necesita ser convertido en una cadena*/

        new	FetchBook(mTitleText,mAuthorText).execute((Runnable) mQueryString);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

    }
}
