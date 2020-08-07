package com.tissaniffo.nations.managers;

import com.tissaniffo.nations.Nations;
import com.tissaniffo.nations.listeners.PlayerListeners;

public class ListenerManager {

    private Nations nations;

    public ListenerManager(Nations plugin){
        nations = plugin;
    }

    public void loadListeners(){
        nations.addListener(new PlayerListeners());
    }

}
