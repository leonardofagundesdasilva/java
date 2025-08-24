package java17;

public class PatternMatching {
    public static void main(String[] args) {
        System.out.println(checkType(42));
        System.out.println(checkType("Hello"));
        System.out.println(checkType(3.14));
        System.out.println(checkType(true));
        System.out.println(checkType(new RuntimeException()));
    }

    static String checkType(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Double d -> "Double: " + d;
            case Boolean b -> "Boolean: " + b;
            default -> "Unknown type";
        };
    }
}
