package hr.mars.muzicow.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import hr.mars.muzicow.Activities.adapters.FragmentAdapter;

import hr.mars.muzicow.R;

/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instanciram singleton fabric da mogu provjerit dal postoji sesija
        setContentView(R.layout.select_role_login);
        context = getApplicationContext();
    }

    public void buttonClickFunction(View v) {
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String uloga = mySpinner.getSelectedItem().toString();
        Intent myIntent = new Intent(MainActivity.this, FragmentAdapter.class);
        myIntent.putExtra("userRole",uloga);
        myIntent.putExtra("sesija","");
        MainActivity.this.startActivity(myIntent);
    }
}