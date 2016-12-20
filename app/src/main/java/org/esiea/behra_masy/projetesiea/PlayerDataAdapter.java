package org.esiea.behra_masy.projetesiea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/***
 * Adapter for the players
 */
public class PlayerDataAdapter extends RecyclerView.Adapter<PlayerDataAdapter.ViewHolder> {
    private ArrayList<Player> players;
    private Context context;
    private OnPlayerClickListener listener;

    /***
     * Constructeur
     *
     * @param context
     * @param players  player list
     * @param listener listener for the click on an item of the list.
     */
    public PlayerDataAdapter(Context context, ArrayList<Player> players, OnPlayerClickListener listener) {
        this.players = players;
        this.context = context;
        this.listener = listener;
    }

    /***
     * @param viewGroup
     * @param i
     * @return viewHolder of the player list
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_player_layout, viewGroup, false);
        TextView text = (TextView) view.findViewById(R.id.tv_player);
        text.setTextSize(40);
        text.setMinHeight(300);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.players.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    public interface OnPlayerClickListener {
        void onItemClick(Player item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_player;

        public ViewHolder(View view) {
            super(view);

            tv_player = (TextView) view.findViewById(R.id.tv_player);
        }

        /***
         * Binding the list on the player
         */
        public void bind(final Player item, final OnPlayerClickListener listener) {
            tv_player.setText(item.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}