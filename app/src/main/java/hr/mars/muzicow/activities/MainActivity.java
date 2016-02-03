package hr.mars.muzicow.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import hr.mars.muzicow.R;


/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity  {
    Button login;
    String choice;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_network_login);
        final Spinner spinn = (Spinner)findViewById(R.id.sNChoose);
        spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                choice= spinn.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        login = (Button) findViewById(R.id.loginID);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (choice) {
                    case "Facebook":
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("networkChoice", "Facebook");
                        startActivity(intent);

                        break;
                    case "Twitter":
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("networkChoice", "Twitter");
                        startActivity(intent);
                        break;
                }
            }
        });


    }

}