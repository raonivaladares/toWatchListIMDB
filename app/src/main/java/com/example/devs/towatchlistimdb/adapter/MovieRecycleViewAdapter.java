package com.example.devs.towatchlistimdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devs.towatchlistimdb.R;
import com.example.devs.towatchlistimdb.models.Movie;

import java.util.List;

public class MovieRecycleViewAdapter extends RecyclerView.Adapter<MovieRecycleViewAdapter.DataObjectHolder> {
        private List<Movie> movies;
        private static MyClickListener myClickListener;

        public void setOnItemClickListener(MyClickListener myClickListener) {
            this.myClickListener = myClickListener;
        }

        public MovieRecycleViewAdapter(final List<Movie> movies) {
            this.movies = movies;
        }

        @Override
        public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);

            DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
            return dataObjectHolder;
        }

        @Override
        public void onBindViewHolder(DataObjectHolder holder, int position) {
            holder.label.setText(movies.get(position).getTitle());
            holder.dateTime.setText(movies.get(position).getYear());
        }

        public Movie getItem(int index) {
            return movies.get(index);
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public interface MyClickListener {
            void onItemClick(int position, View v);
        }

        public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView label;
            TextView dateTime;

            public DataObjectHolder(View itemView) {
                super(itemView);
                label = (TextView) itemView.findViewById(R.id.textViewName);
                dateTime = (TextView) itemView.findViewById(R.id.textViewYear);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                 myClickListener.onItemClick(getPosition(), v);
            }
        }
    }
