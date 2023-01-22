package app;


import filesaver.CSVfileSaver;
import org.apache.commons.lang3.time.StopWatch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopwatchApp extends Thread {

    private StopWatch s = new StopWatch();
    private JLabel time;
    private boolean active = false;
    private List<String> timeStamps = new ArrayList<>();
    private List<ActionListener> listeners = new ArrayList<>();


    private CSVfileSaver saver;

    public StopwatchApp(JLabel time) {
        this.time = time;
        this.time.setText("00:00:00.000");

    }

    @Override
    public void run() {
        while(true) {
            while(active) {
                time.setText(s.toString());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTimer() {
        active = true;
        timeStamps.clear();
        raiseEventTimeStampsChanged();
        s.start();
    }

    public void end() {
        s.stop();
        s.reset();
        active = false;

        saver = new CSVfileSaver(timeStamps);
        try {
            System.out.println("here");
            saver.createCSVFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getTime() {
        return s.toString();
    }

    public void addTimeStamp() {
        timeStamps.add(s.toString());
        raiseEventTimeStampsChanged();
    }

    public int getTimeStampSize() {
        return timeStamps.size();
    }

    public List<String> getTimeStamps() {
        return timeStamps;
    }

    public void clearTimeStamps() {
        timeStamps.clear();
        raiseEventTimeStampsChanged();
    }

    private void raiseEventTimeStampsChanged(){
        for (ActionListener al : listeners) {
            al.actionPerformed(new ActionEvent(this, 1, "timestamp added"));
        }
    }

    public void addActionListener(ActionListener toAdd) {
        listeners.add(toAdd);
    }
}
