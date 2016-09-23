package com.quadrolord.epicbattle.logic.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Goorus on 19.09.2016.
 */
public class EventListenerItem {

    public ValueEventListener listener;

    public Object owner;

    public DatabaseReference reference;

}
