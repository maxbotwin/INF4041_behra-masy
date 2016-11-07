package org.esiea.behra_masy.projetesiea;

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
        final Button test_button = (Button) findViewById(R.id.button_de_test);
        test_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                new AlertDialog.Builder(context).setMessage(R.string.HelloWorld).create().show();
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

            if (item.getItemId() == R.id.menu_help){
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();

            }
        return true;
    }


}
