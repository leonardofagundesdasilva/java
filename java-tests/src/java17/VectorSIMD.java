package java17;

import jdk.incubator.vector.FloatVector;

/* ### Vector API (Second Incubator) (JEP 414)
 *
 * It's designed to perform SIMD (Single Instruction, Multiple Data) operations, which greatly improve performance by processing multiple elements in parallel.
 */
public class VectorSIMD {
    public static void main(String[] args) {
        FloatVector floatVector = FloatVector.fromArray(FloatVector.SPECIES_128, new float[]{1.0f, 2.0f, 3.0f, 4.0f}, 0);
        FloatVector floatVector2 = floatVector.mul(2.0f);

        System.out.println("Vector: " + floatVector);
        System.out.println("Vector * 2.0: " + floatVector2);
    }
}