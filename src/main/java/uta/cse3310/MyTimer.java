package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    private int timeRemaining;
    private boolean isRunning;
    private boolean isStopped;
    private Timer timer;

    public MyTimer() // initalizing the timer & variables
    {
        this.timeRemaining = 0;
        this.isRunning = false;
        this.isStopped = true;
        this.timer = new Timer();
    }

    public void startTimer(int initialTime) // Starting the timer with the initial time with checking if the timer is
                                            // stopped or running
    {
        if (isStopped && !isRunning) {
            timeRemaining = initialTime;
            isStopped = false;
            isRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (timeRemaining > 0) {
                            timeRemaining--;
                        } else {
                            stopTimer();
                        }
                    } catch (Exception e) // for expections
                    {
                        e.printStackTrace();
                    }
                }
            }, 0, 1000);
        }
    }

    public void stopTimer() // Stop the timer
    {
        if (isRunning) {
            timer.cancel();
            isStopped = true;
            isRunning = false;
        }
    }

    public void resetTimer() // Reset the timer and set the time remaining to 0
    {
        stopTimer();
        timeRemaining = 0;
    }

    public int getTimeRemaining() // Get the time remaining
    {
        return timeRemaining;
    }

    public boolean isRunning() // Check if the timer is running
    {
        return isRunning;
    }

    public boolean isStopped() // Check if the timer is stopped
    {
        return isStopped;

    }
}
