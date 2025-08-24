package java17;

public class SealedPermits {
    private sealed class Animal permits Dog, Cat {

    }

    private final class Dog extends Animal {

    }

    private non-sealed class Cat extends Animal {

    }
}
