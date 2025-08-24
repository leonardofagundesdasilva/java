package java17;

import java.util.random.RandomGenerator;

public class RandomGeneratorTest {
    static void main() {
        RandomGenerator generator = RandomGenerator.of("L128X256MixRandom");
        System.out.println(generator.nextInt(100));
    }
}
