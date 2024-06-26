package com.mirea.kt.ribo.examjavaandroid.task15;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityTask15Binding;

import java.util.ArrayList;
import java.util.Date;

public class task15Activity extends AppCompatActivity {
    private DBmanagerMessage dBmanagerMessage;
    private ActivityTask15Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTask15Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dBmanagerMessage = new DBmanagerMessage(new SQLHelper(getBaseContext(),
                "message_database",null,1));
        ArrayList<Message> listMessage = dBmanagerMessage.getAllMessages();
        MessageAdapter adapter = new MessageAdapter(listMessage,
                getBaseContext(),dBmanagerMessage);
        binding.messageRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        binding.messageRec.setAdapter(adapter);
        if (listMessage.size()!=0){
            binding.collectMessage.setVisibility(View.GONE);
        }
        binding.collectMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMessage.add(new Message(31233,"sadsadsdsad",
                        "2024-12-23;122112",0));
                listMessage.add(new Message(33433,"rtsdfdsad",
                        "2024-12-23;122112",1));
                listMessage.add(new Message(312323,"sadasfxzsad",
                        "2024-12-23;122112",1));
                listMessage.add(new Message(3123333,"asdasfgdvzc",
                        "2024-12-23;122112",0));
                dBmanagerMessage.putMessage(
                        new Message(31233,"sadsadsdsad",
                                "2024-12-23;122112",0)
                );
                dBmanagerMessage.putMessage(
                        new Message(33433,"rtsdfdsad",
                                "2024-12-23;122112",1)
                );
                dBmanagerMessage.putMessage(
                        new Message(312323,"sadasfxzsad",
                                "2024-12-23;122112",1)
                );
                dBmanagerMessage.putMessage(
                        new Message(3123333,"asdasfgdvzc",
                                "2024-12-23;122112",0)
                );
                adapter.setMessages(listMessage);
            }
        });
    }
}