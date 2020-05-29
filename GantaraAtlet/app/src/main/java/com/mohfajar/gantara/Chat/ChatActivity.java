package com.mohfajar.gantara.Chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohfajar.gantara.R;
import com.mohfajar.gantara.API;
import com.mohfajar.gantara.Config;
import com.mohfajar.gantara.RunTime;
import com.mohfajar.gantara.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;
    private ArrayList<IsiChat> chats;
    private DbChat dbChat;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private static boolean isRunning = false;
    private Receiver mReceiver;
    private ImageButton btnSend;
    private EditText editMessage;
    DateFormat dfNoSecond;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager = SharedPrefManager.getInstance(this);
        handler = new Handler();
        dbChat = new DbChat(this);
        mReceiver = new Receiver();
        registerReceiver(mReceiver, new IntentFilter("message_intent"));

        btnSend = (ImageButton) findViewById(R.id.sendButton);
        editMessage = (EditText) findViewById(R.id.messageEdit);

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dfNoSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        recyclerView = (RecyclerView) findViewById(R.id.messagesList);

        chats = dbChat.getAthleteChat();
        adapter = new Adapter(this,chats);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() -1);

        isRunning = true;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editMessage.getText().toString().equals("")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            RunTime.logTime();
                            String date = df.format(Calendar.getInstance().getTime());
                            sendChat(sharedPrefManager.getPsikologID(), sharedPrefManager.getAtletID(), date, editMessage.getText().toString());
                            editMessage.setText("");
                        }
                    });
                }
            }
        });
    }

    private void sendChat(final String penerima, final String pengirim, String waktu, final String message) {
        Log.d("Chat","chat param penerima : " +penerima + ", pengirim : "+pengirim + ", waktu : "+waktu + " message : "+message);
        final IsiChat chat = new IsiChat(pengirim,penerima,waktu,message);
        adapter.addChat(chat);
        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
        StringRequest request = new StringRequest(Request.Method.POST, Config.DATA_URL_SEND_CHAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Chat","message : " +response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            if(status == 1) {
                                dbChat.save(chat);
                                RunTime.logTime();
                            }
                            else {
                                Toast.makeText(ChatActivity.this,"Gagal mengirim pesan",Toast.LENGTH_SHORT).show();
                                adapter.removeChat(chat);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ChatActivity.this,"Error mengirim pesan (JSON parsing failed)",Toast.LENGTH_SHORT).show();
                            adapter.removeChat(chat);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ChatActivity.this,"Gagal mengirim pesan",Toast.LENGTH_SHORT).show();
                adapter.removeChat(chat);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(API.PARAM_ID_PENERIMA,penerima);
                params.put(API.PARAM_ID_PENGIRIM,pengirim);
                params.put(API.PARAM_JENIS_PENERIMA,""+1);
                params.put(API.PARAM_MESSAGE,message);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
        unregisterReceiver(mReceiver);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean isRunning(){
        return isRunning;
    }


    class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

        private ArrayList<IsiChat> chats;
        private Context context;

        private static final int SELF = 10;

        public Adapter(Context context, ArrayList<IsiChat> chats){
            this.context = context;
            this.chats = chats;
        }

        public void addChat(IsiChat chat){
            chats.add(chat);
            notifyDataSetChanged();
        }

        public void removeChat(IsiChat chat){
            chats.remove(chat);
            notifyDataSetChanged();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if(viewType == SELF){
                view = LayoutInflater.from(context).inflate(R.layout.layout_chat_self,parent,false);
            }
            else view = LayoutInflater.from(context).inflate(R.layout.layout_chat_other,parent,false);

            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.message.setText(chats.get(position).getIsi());
            try {
                String waktu = dfNoSecond.format(dfNoSecond.parse(chats.get(position).getWaktu()));
                holder.date.setText(waktu);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemViewType(int position) {
            IsiChat chat = chats.get(position);
            if (chat.getPenerima().equals(sharedPrefManager.getAtletID())){
                return 0;
            }
            else return SELF;
        }

        @Override
        public int getItemCount() {
            return chats.size();
        }

        public class Holder extends RecyclerView.ViewHolder{

            TextView date;
            TextView message;

            public Holder(View itemView) {
                super(itemView);
                message = (TextView) itemView.findViewById(R.id.message);
                date = (TextView) itemView.findViewById(R.id.timestamp);
            }
        }
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String id_penerima = intent.getStringExtra("id_penerima");
            String id_pengirim = intent.getStringExtra("id_pengirim");
            String waktu = intent.getStringExtra("waktu");
            String message = intent.getStringExtra("message");

            IsiChat chat = new IsiChat(id_pengirim,id_penerima,waktu,message);
            adapter.addChat(chat);
            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);

        }
    }
}
