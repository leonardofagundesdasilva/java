package java17;

import java.lang.foreign.*;

public class ForeignFunctionAndMemoryAPI {

    public static void main(String[] args) {
        // Allocate off-heap memory (a chunk of memory outside the JVM's heap)
        //try (MemorySegment segment = MemorySegment.allocateNative(4)) {
            // Write a value to the allocated memory (4 bytes for an integer)
            //MemoryAccess.setInt(segment, 0, 1234);

            // Read the value back from the memory
            //int value = MemoryAccess.getInt(segment, 0);

            // Print the value to verify
            //System.out.println("Value in off-heap memory: " + value);
        //} // Automatic clean-up after the try-with-resources block
    }
}
