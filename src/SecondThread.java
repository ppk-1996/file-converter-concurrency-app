/* Pyae Phyo Kyaw - 45238952 */
/**
 * This thread checks and edit the buffer when the buffer is full and it can't go to Thread 1 or 3
 */
public class SecondThread extends CircularBuffer implements Runnable {

    @Override
    public void run() {
        //This thread will loop as long as thread 1 is alive.
        while (t1Flag) {
            //Same condition as Thread 1
            if (out == (in + 1) % N) {
                //atomic statements
                synchronized (this) {
                    //For all characters in the buffer, check for tabs and replace with single space
                    // for consecutive spaces simply marks as uFFF
                    for (int i = 0; i < buffer.length; i++) {
                        if (i != 0 && buffer[i] == ' ' && (buffer[i - 1] == ' ' || buffer[i - 1] == '\uFFFF')) {
                            buffer[i] = '\uFFFF';
                        }
                        if (buffer[i] == '\t') {
                            buffer[i] = ' ';
                        }
                        spaceUpdated = true;
                    }
                }
            } else {
                Thread.yield();
            }


        }
    }
}

