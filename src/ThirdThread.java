import java.io.IOException;
/* Pyae Phyo Kyaw - 45238952 */
/**
 * This thread writes chars from buffer to a string and write that string to a file
 */
public class ThirdThread extends CircularBuffer implements Runnable {

    private String file;

    ThirdThread(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            A1Writer a1Writer = new A1Writer(file);
            char d;
            String allText = "";
            //live and run until Thread 1 dies
            while (t1Flag) {
                //Given algorithm condition and check if tabs and spaces are edited
                if (in != out && spaceUpdated) {
                    //atomic statements
                    synchronized (this) {
                        d = buffer[out];
                        if (d != '\uFFFF') {
                            allText += d;
                        }
                    }
                    out = (out + 1) % N;
                } else {
                    Thread.yield();
                }

            }

            /**
             * The Thread 2 method for removing consecutive spaces is not perfect.
             * It has some glitch where two spaces concat each other when one buffer has space on last index and
             * another buffer has space on first index
             * If you don't want to remove all consecutive spaces, comment the below code.
             */
            allText = allText.replaceAll(" +", " ");
            //After two other threads die, this is the last code to run from Thread 3
            //write the string to file, and line break every 80 chars
            for (int i = 0; i < allText.length(); i++) {
                char c = allText.charAt(i);
                if (i % 80 == 0 && i != 0) {
                    a1Writer.lineBreak();
                }
                a1Writer.write(c);
            }

            //CLose a1writer
            a1Writer.close();


        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }
}