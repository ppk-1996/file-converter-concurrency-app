import java.io.IOException;
/* Pyae Phyo Kyaw - 45238952 */
/**
 * This thread read the file and put characters into circular buffer, when buffer is full, put the thread to ready queue
 */

public class FirstThread extends CircularBuffer implements Runnable {
    private String file;

    FirstThread(String file) {
        this.file = file;
    }

    @Override
    public void run() {

        try {
            A1Reader a1Reader = new A1Reader(file);
            //d is to get return data from a1Reader.read()
            int d = 0;
            //the loop will stop when all characters are read
            while (t1Flag) {
                //when eof is reached read() method returns -1
                while (d != -1) {
                    //Mutually exclusive, Starvation Free algorithm
                    if (out != (in + 1) % N) {
                        //Atomic statements
                        synchronized (this) {
                            d = a1Reader.read();
                            //replace end of line with space
                            if (d == '\n') {
                                buffer[in] = ' ';
                            } else {
                                buffer[in] = (char) d;
                            }
                        }
                            in = (in + 1) % N;

                    } else {
                        // Thread 3 can only run when spaceUpdated is true.
                        // We need to run Thread 2 first to edit out tabs and spaces
                        spaceUpdated = false;
                        //put thread to ready queue
                        Thread.yield();
                    }

                }
                t1Flag = false;
            }
            //close a1Reader
            a1Reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

