package com.gantara.mohfajar.Chat;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gantara.mohfajar.R;
import com.gantara.mohfajar.Config;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends Fragment {

    List<ChatRoom> chatRooms;
    DbChat dbChat;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static boolean isRunning = false;
    Receiver mReceiver;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChatFragment(){

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChatFragment newInstance(int columnCount) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        isRunning = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbChat = new DbChat(getActivity());
        mReceiver = new Receiver();
        chatRooms = new ArrayList<>();
        getActivity().registerReceiver(mReceiver, new IntentFilter("message_intent"));

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    private void loadChats(){
        chatRooms.clear();
        chatRooms = dbChat.getAtletsWithChat();
        Collections.sort(chatRooms, new ChatRoom.AtletComparator());
        ((MyChatRecyclerViewAdapter)recyclerView.getAdapter()).reloadItems(chatRooms);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Load Chat");
        progressDialog.setMessage("Silahkan tunggu...");
        progressDialog.setCancelable(false);

        progressDialog.show();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyChatRecyclerViewAdapter(chatRooms, mListener));
            loadChats();
        }
        progressDialog.dismiss();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Chat item);
    }


    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String id_penerima = intent.getStringExtra("id_penerima");
            String id_pengirim = intent.getStringExtra("id_pengirim");
            String waktu = intent.getStringExtra("waktu");
            String message = intent.getStringExtra("message");

            loadChats();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==54){
            loadChats();
        }
    }

    private class MyChatRecyclerViewAdapter extends RecyclerView.Adapter<MyChatRecyclerViewAdapter.ViewHolder> {

        private List<ChatRoom> mValues;
        private final OnListFragmentInteractionListener mListener;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

        public MyChatRecyclerViewAdapter(List<ChatRoom> items, OnListFragmentInteractionListener listener) {
            mValues = items;
            mListener = listener;
        }

        public void reloadItems(List<ChatRoom> chatRooms){
            this.mValues = chatRooms;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_chat, parent, false);
            return new MyChatRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyChatRecyclerViewAdapter.ViewHolder holder, final int position) {

            if(mValues.get(position).getChatList().size() <= 0){
                holder.textViewCNama.setText(mValues.get(position).getNama());
                holder.textViewCIsi.setText(mValues.get(position).getLastChat());
            } else {
                holder.textViewCNama.setText(mValues.get(position).getNama());
                holder.textViewCWaktu.setText(mValues.get(position).getLastChatDate());
                holder.textViewCIsi.setText(mValues.get(position).getLastChat());
            }

            if(!mValues.get(position).getLastStatus()) {
                holder.textViewUnreadChat.setVisibility(View.VISIBLE);
                holder.textViewCIsi.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                holder.textViewCIsi.setTypeface(holder.textViewCIsi.getTypeface(),Typeface.BOLD_ITALIC);
            } else {
                holder.textViewUnreadChat.setVisibility(View.INVISIBLE);
                holder.textViewCIsi.setTextColor(ContextCompat.getColor(getActivity(),android.R.color.darker_gray));
                holder.textViewCIsi.setTypeface(Typeface.DEFAULT);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        Intent bukaChatActivity = new Intent(getContext(), ChatActivity.class);
                        bukaChatActivity.putExtra("id_chatroom",chatRooms.get(position).getId_atlet());
                        startActivityForResult(bukaChatActivity,54);
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });
            Picasso.get().load(""+Config.DATA_URL_PHOTO_PROFIL+"/"+chatRooms.get(position).getUser_name()+".png").resize(100, 100).centerCrop().into(holder.imageCircleCProfile);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public Chat mItem;

            public final ImageView imageCircleCProfile;
            public final TextView textViewCNama;
            public final TextView textViewCWaktu;
            public final TextView textViewCIsi;
            public final CircleImageView textViewUnreadChat;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                imageCircleCProfile = (ImageView) view.findViewById(R.id.imageCircleCProfile);
                textViewCNama = (TextView) view.findViewById(R.id.textViewCNama);
                textViewCWaktu = (TextView) view.findViewById(R.id.textViewCWaktu);
                textViewCIsi = (TextView) view.findViewById(R.id.textViewCIsi);
                textViewUnreadChat = (CircleImageView) view.findViewById(R.id.textViewCUnreadChats);
            }
        }
    }
}