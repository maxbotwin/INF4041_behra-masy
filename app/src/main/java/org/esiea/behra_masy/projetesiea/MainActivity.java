package org.esiea.behra_masy.projetesiea;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button LastResult = (Button) findViewById(R.id.score);
        final Button Players = (Button) findViewById(R.id.players);

        // Creating listener for the two buttons of the homepage
        LastResult.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Create the AlertDialog when the button is clicked.
                new AlertDialog.Builder(context).setMessage(R.string.LastResult).create().show();
            }
        });

        Players.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Intent intent = new Intent(this, PlayersActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_help)
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();

        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(this, ChildActivity.class);
            startActivity(intent);
        }
            return true;
    }


}
