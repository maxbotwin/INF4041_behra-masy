package org.esiea.behra_masy.projetesiea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Player detail
public class PlayersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Intent intent = getIntent();
        if (intent != null)
            this.initView(intent);

    }

    public void initView(Intent intent) {
        //Long toast to display the full name of the player
        final String playerFullName = intent.getStringExtra("player_surname") + " " + intent.getStringExtra("player_name");
        Toast.makeText(this, playerFullName, Toast.LENGTH_LONG).show();

        // Binding the textblocks to the textviews associated
        TextView nameView = (TextView) findViewById(R.id.detail_name);
        TextView surnameView = (TextView) findViewById(R.id.detail_surname);
        TextView ageView = (TextView) findViewById(R.id.detail_age);
        TextView numberView = (TextView) findViewById(R.id.detail_number);
        TextView positionView = (TextView) findViewById(R.id.detail_position);
        TextView nationalityView = (TextView) findViewById(R.id.detail_nationality);

        nameView.setText(intent.getStringExtra("player_name"));
        surnameView.setText(intent.getStringExtra("player_surname"));
        ageView.setText(intent.getStringExtra("player_age"));
        numberView.setText(intent.getStringExtra("player_number"));
        positionView.setText(intent.getStringExtra("player_position"));
        nationalityView.setText(intent.getStringExtra("player_nationality"));

        //Boutton to browse the player on Google
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_prefix = getString(R.string.url_prefix_recherche);

                String url = url_prefix + playerFullName;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
    }
}