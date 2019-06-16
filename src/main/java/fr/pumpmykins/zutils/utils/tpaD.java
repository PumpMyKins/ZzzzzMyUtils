package fr.pumpmykins.zutils.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class tpaD {

    public static void main(String[] args)
    {
        int i = 0;
        i++;
        Timer timer = new Timer(500, new TimeA(500));
        timer.start();

        try
        {
            Thread.sleep(5000); // 10000 ms, ça s'affiche 10-1sec fois
            // Le timer dure 10sec et s'affiche tous les 1sec
        } catch (InterruptedException e)
        {
        }
        timer.stop();
    }

}

class TimeA implements ActionListener {
    private int time;

    public TimeA(int init) {
        super();
        this.time = init;
    }

    public void actionPerformed(ActionEvent e) {
        this.time--;
       
    }
}