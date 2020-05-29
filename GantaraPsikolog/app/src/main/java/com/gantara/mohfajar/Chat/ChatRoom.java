package com.gantara.mohfajar.Chat;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Moh_Fajar on 14/07/2017.
 */

public class ChatRoom extends AtletChat {

    private String lastChatDate;
    private String lastChat;
    private int unreadCount;
    private boolean lastStatus;
    private String id_chatroom;

    private ArrayList<Chat> chatList;

    public ChatRoom(Cursor cursor) {
        super(cursor);
    }

    public ChatRoom(JSONObject jsonObject) {
        super(jsonObject);
    }

    public String getLastChatDate() {
        return lastChatDate;
    }

    public void setLastChatDate(String lastChatDate) {
        this.lastChatDate = lastChatDate;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public boolean getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(boolean lastStatus) {
        this.lastStatus = lastStatus;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public ArrayList<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(ArrayList<Chat> chatList) {
        this.chatList = chatList;
    }

    public ChatRoom(){
        super();
    }

    protected static class AtletComparator implements Comparator<ChatRoom> {
        @Override
        public int compare(ChatRoom o1, ChatRoom o2) {
            return o2.getLastChatDate().compareTo(o1.getLastChatDate());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.lastChatDate);
        dest.writeString(this.lastChat);
        dest.writeInt(this.unreadCount);
        dest.writeByte(this.lastStatus ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.chatList);
    }

    protected ChatRoom(Parcel in) {
        super(in);
        this.lastChatDate = in.readString();
        this.lastChat = in.readString();
        this.unreadCount = in.readInt();
        this.lastStatus = in.readByte() != 0;
        this.chatList = in.createTypedArrayList(Chat.CREATOR);
    }

    public static final Parcelable.Creator<ChatRoom> CREATOR = new Parcelable.Creator<ChatRoom>() {
        @Override
        public ChatRoom createFromParcel(Parcel source) {
            return new ChatRoom(source);
        }

        @Override
        public ChatRoom[] newArray(int size) {
            return new ChatRoom[size];
        }
    };
}
