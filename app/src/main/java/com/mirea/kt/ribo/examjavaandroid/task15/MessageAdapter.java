package com.mirea.kt.ribo.examjavaandroid.task15;

import android.content.Context;
import android.media.Image;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.kt.ribo.examjavaandroid.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> messages;
    private Context context;
    private DBmanagerMessage dBmanager;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card,
                parent,false);
        return new ViewHolder(view);
    }

    public MessageAdapter(ArrayList<Message> messages, Context context, DBmanagerMessage dBmanager) {
        this.messages = messages;
        this.context = context;
        this.dBmanager = dBmanager;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.text.setText(message.getText());
        holder.dateTime.setText(message.getDatetime());
        if (message.getWatched()==1){
            holder.watched.setImageDrawable(context.getDrawable(R.drawable.watched_icon));
        }else {
            holder.watched.setImageDrawable(context.getDrawable(R.drawable.watched_gray));
        }
        holder.watched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getWatched()==1){
                    dBmanager.changeWatched(message.getId(),0);
                    message.setWatched(0);
                    holder.watched.setImageDrawable(context.getDrawable(R.drawable.watched_gray));
                    notifyDataSetChanged();
                }else{
                    dBmanager.changeWatched(message.getId(),1);
                    message.setWatched(1);
                    holder.watched.setImageDrawable(context.getDrawable(R.drawable.watched_icon));
                    notifyDataSetChanged();
                }
            }
        });
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView text,dateTime;
        private final ImageView watched;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_message_view);
            dateTime = itemView.findViewById(R.id.datetime_text_view);
            watched = itemView.findViewById(R.id.watched_status);
        }
    }
}
