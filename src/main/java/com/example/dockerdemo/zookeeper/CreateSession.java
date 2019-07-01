package com.example.dockerdemo.zookeeper;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class CreateSession implements Watcher {
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent event) {
        if (event.getState().equals(Event.KeeperState.SyncConnected)) {
            System.out.println("Do business");
        }
        System.out.println("Received: " + event.toString());
    }

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("192.168.159.129:2181", 5000, new CreateSession());
            System.out.println("state: " + zooKeeper.getState());
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
