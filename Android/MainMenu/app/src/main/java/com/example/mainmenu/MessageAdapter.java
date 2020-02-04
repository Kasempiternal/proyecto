package com.example.mainmenu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {


    Context context;
    List<Message> messages;
    DatabaseReference messagedb;


    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messagedb) {

        this.context = context;
        this.messages = messages;
        this.messagedb = messagedb;


    }


    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new MessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int position) {

        Message message = messages.get(position);
        if (message.getName().equals(AllMethods.name)) {
            holder.tvTitle.setText("You:" + message.getMessage());
            holder.tvTitle.setGravity(Gravity.START);
            holder.l.setBackgroundColor(Color.parseColor("#EF9E73"));
        } else {
            holder.tvTitle.setText(message.getName() + ":" + message.getMessage());
            holder.imageButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageButton imageButton;
        LinearLayout l;

        public MessageAdapterViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.ltitle);
            imageButton = itemView.findViewById(R.id.delete);
            l = itemView.findViewById(R.id.lmessage);


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messagedb.child(messages.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }

}
