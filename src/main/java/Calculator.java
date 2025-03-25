public class Calculator {

    public float add(float a, float b) {
        return a + b;
    }

    public float divide(float a, float b) {
        if (b == 0) {
            throw new IllegalArgumentException("Не может делить на ноль");
        }
        return a / b;
    }

    public float delete(float a, float b) {
        return a - b;
    }

    public float multiply(float a, float b) {
        return a * b;
    }
}
