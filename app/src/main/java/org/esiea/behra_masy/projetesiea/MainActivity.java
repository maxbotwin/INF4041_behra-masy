package org.esiea.behra_masy.projetesiea;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HttpJsonRequest.OnGetJsonListener, PlayerDataAdapter.OnPlayerClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players = new ArrayList<>();

        //Récupération des éléments
        this.recyclerView = (RecyclerView) findViewById(R.id.players_recycler_view);
        this.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(layoutManager);

        try {
            URL url = new URL("https://api.myjson.com/bins/11jdjd");

            HttpJsonRequest h = new HttpJsonRequest(this, this);
            h.execute(url);
            createNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Create the menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*Get JSON Method*/
    public void OnGetJson(JSONObject jsonObject) {

        try {
            // get an array of players
            JSONArray array = jsonObject.getJSONArray("players");

            ArrayList<Player> tempPlayers = new ArrayList<Player>();

            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // Pour tous les objets on récupère les infos
                tempPlayers.add(new Player(obj, true));
            }

            if (this.players.isEmpty()) {
                this.players = tempPlayers;
                PlayerDataAdapter adapter = new PlayerDataAdapter(getApplicationContext(), this.players, this);
                this.recyclerView.setAdapter(adapter);
            } else {
                for (int i = 0; i < this.players.size(); i++) {
                    Player player = this.players.get(i);

                    if (!tempPlayers.contains(players)) {
                        this.recyclerView.removeViewAt(i);
                        this.recyclerView.getAdapter().notifyItemRemoved(i);
                        this.recyclerView.getAdapter().notifyItemRangeChanged(i, this.players.size());
                        this.recyclerView.getAdapter().notifyDataSetChanged();
                        this.players.remove(player);
                        i--;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method called when an item on the menu is displayed*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_about) {
            final Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return true;
    }

    /***
     * When a player is clicked, a new activity is created.
     *
     * @param item
     */
    public void onItemClick(Player item) {
        Intent intent = new Intent(MainActivity.this, PlayersActivity.class);
        intent.putExtra("player_name", item.getName());
        intent.putExtra("player_surname", item.getSurname());
        intent.putExtra("player_age", item.getAge());
        intent.putExtra("player_nationality", item.getNationality());
        intent.putExtra("player_number", item.getNumber());
        intent.putExtra("player_position", item.getPosition());
        startActivity(intent);
    }

    /***
     * Notification to reach the about page.
     */
    private final void createNotification(){
        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Create an intent for the notification
        final Intent launchNotifiactionIntent = new Intent(this, AboutActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        //build the notification
        Notification.Builder builder = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.about)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setContentText(getResources().getString(R.string.notification_desc))
                .setContentIntent(pendingIntent);

        //display the notification
        mNotification.notify(0, builder.build());
    }

    public class MyReceiver extends BroadcastReceiver {

        public static final String ACTION_RESP = "filter";

        @Override
        public void onReceive(Context context, Intent intent) {

            String text = intent.getStringExtra(JsonPullService.SOURCE_URL);
            if (text != null) {
                try {
                    JSONObject jsonObject = new JSONObject(text);
                    OnGetJson(jsonObject);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
