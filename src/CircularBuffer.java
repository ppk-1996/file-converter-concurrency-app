

/* Pyae Phyo Kyaw - 45238952 */

/**
 * Circular Buffer class contains static variables to allow threads to communicate characters
 **/
class CircularBuffer {
    //Give algorithm
    static int N = 20;
    static int out = 0;
    static int in = 0;
    static char[] buffer = new char[20];

    //To ensure Thread 3 doesn't write before updating the tabs and spaces in Thread 2
    static boolean spaceUpdated = false;

    //To check if the T1 is still alive
    static boolean t1Flag = true;


}
