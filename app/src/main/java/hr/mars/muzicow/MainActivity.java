package hr.mars.muzicow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {
        Context context;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.login);
                context = getApplicationContext();

        }

        public void buttonClickFunction(View v) {


                Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
                String text = mySpinner.getSelectedItem().toString();

                //Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                //toast.show();


                //if (text.equals("Korisnik")){


                Intent myIntent = new Intent(MainActivity.this, User.class);
                        myIntent.putExtra("userRole",text.toString());
                MainActivity.this.startActivity(myIntent);
                //}
                //else
                        //Toast.makeText(this,"hh",Toast.LENGTH_LONG);
        }


}