package com.example.gerardodwyer.ps4collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.gerardodwyer.ps4collection.Games;

public class CustomAdapter extends BaseAdapter{

    Context mContext;
    ArrayList<Games> games = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Games> games) {
        mContext = context;
        this.games = games;
    }


    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int i) {
        return games.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_items, viewGroup, false);
        }

        Games tempGames = (Games) getItem(i);

        TextView list_id = (TextView)view.findViewById(R.id.list_id);
        TextView list_gameTitle = (TextView)view.findViewById(R.id.list_gameTitle);
        TextView list_releaseDate = (TextView)view.findViewById(R.id.list_releaseDate);
        ImageView list_thumb = (ImageView) view.findViewById(R.id.list_thumb);


        list_id.setText(tempGames.getId());
        list_gameTitle.setText(tempGames.getGameTitle());
        list_releaseDate.setText(tempGames.getReleaseDate());
//        list_thumb.setImageDrawable(tempGames.getImage());

        String URL = games.get(i).getThumb();

        list_thumb.setTag(URL);
        new DownloadImagesTask().execute(list_thumb);

        return view;
    }
}