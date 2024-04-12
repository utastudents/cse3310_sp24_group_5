package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyTimerTest {
    private MyTimer timer;

    @Before
    public void setUp() { // Initialize a new MyTimer object before each test
        timer = new MyTimer();
    }

    @Test
    public void testStartTimer() { // Test starting the timer
        assertFalse(timer.isRunning()); // Initially, the timer should not be running
        timer.startTimer(10); // Start the timer with 10 seconds
        assertTrue(timer.isRunning()); // After starting, the timer should be running
    }

    @Test
    public void testStopTimer() { // Test stopping the timer
        timer.startTimer(10); // Start the timer
        assertTrue(timer.isRunning()); // Ensure the timer is running
        timer.stopTimer(); // Stop the timer
        assertFalse(timer.isRunning()); // After stopping, the timer should not be running
    }

    @Test
    public void testResetTimer() { // Test resetting the timer
        timer.startTimer(10); // Start the timer
        timer.resetTimer(); // Reset the timer

        assertFalse(timer.isRunning()); // After resetting, the timer should not be running
        assertTrue(timer.isStopped()); // After resetting, the timer should be stopped
        assertEquals(0, timer.getTimeRemaining()); // After resetting, the time remaining should be 0
    }

    @Test
    public void testGetTimeRemaining() { // Test getting the time remaining
        timer.startTimer(10); // Start the timer with 10 seconds
        assertEquals(10, timer.getTimeRemaining()); // After starting, the time remaining should be 10 seconds
    }

    @Test
    public void testIsRunning() { // Test checking if the timer is running
        assertFalse(timer.isRunning()); // Initially, the timer should not be running
        timer.startTimer(10); // Start the timer
        assertTrue(timer.isRunning()); // After starting, the timer should be running
    }

    @Test
    public void testIsStopped() { // Test checking if the timer is stopped
        assertTrue(timer.isStopped()); // Initially, the timer should be stopped
        timer.startTimer(10); // Start the timer
        assertFalse(timer.isStopped()); // After starting, the timer should not be stopped
    }
}
