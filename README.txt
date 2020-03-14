Pyae Phyo Kyaw - 45238952
File Converter using Circular Buffer
 
File Converter
Main class, start three threads, thread 1,2,3

CircularBuffer
Class to maintain the characters and communicate between threads.
Contain char array of size 20.

 
FirstThread 
Reads characters from the input file using the class A1Reader.
Each character is checked and insert into buffer. \n are replaced with spaces.
I specifically use the Circular buffer with size 20 as well as the given algorithm.
Other specification are in the comments.

Second Thread
Update the buffer to replace tabs with a single space and update consecutive spaces with \uFFF (null)

Third Thread
Write lines of 80 chars to output file using the provided class A1Writer.
Whenever the thread is run, it checks whether Thread 2 has already run (i.e. if the chars in buffer are updated) and in!=out (i.e. not empty)
When the condition met, it first write the chars to a string. After every thread dies, the last job for Thread 3 is to write the text string to a output file with linebreak every 80 chars.


 


