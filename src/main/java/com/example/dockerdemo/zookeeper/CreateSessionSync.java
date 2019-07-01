package com.example.dockerdemo.zookeeper;


import org.apache.zookeeper.*;

import java.io.IOException;

public class CreateSessionSync implements Watcher {
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent event) {
        if (event.getState().equals(Event.KeeperState.SyncConnected)) {
            doBus();
        }
        System.out.println("Received: " + event.toString());
    }

    private void doBus() {
        try {
            if (null != zooKeeper.exists("/note_scot/note_scot_a", false)) {
                System.out.println("exist path: /note_scot/note_scot_a");
                return;
            }
            String path = zooKeeper.create("/note_scot/note_scot_a", "aa".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            System.out.println("zookeeper return: " + path);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("192.168.159.129:2181", 5000, new CreateSessionSync());
            System.out.println("state: " + zooKeeper.getState());
            String path = zooKeeper.create("/test_path", "test_data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("path: " + path);
            Thread.sleep(5000);
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
