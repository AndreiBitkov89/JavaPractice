import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Calculator Epic")
@Feature("Arithmetic Operations")
public class calculatorTests {
    Calculator calculator = new Calculator();

    @Story("Addition")
    @Description("Testing the addition of two numbers")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"7, 2, 9", "1, 0, 1", "1.2, 0.3, 1.5", "100, -2, 98"})
    void testAdd(float a, float b, float result) {
        assertEquals(result, calculator.add(a, b));
    }

    @Story("Deleting")
    @Description("Testing the deleting")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"1, 2, -1", "1, 0, 1", "0, 0.3, -0.3", "100, -2, 102"})
    void testDelete(float a, float b, float result) {
        assertEquals(result, calculator.delete(a, b));
    }

    @Story("Dividing")
    @Description("Testing the dividing two numbers")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"10, 2, 5", "10, 4, 2.5", "100, -2, -50"})
    void testDivide(float a, float b, float result) {
        assertEquals(result, calculator.divide(a, b));
    }

    @Story("Dividing")
    @Description("Testing the dividing on Null")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void testDivideOnNull() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(4, 0));
    }

    @Story("Multiplying")
    @Description("Testing the multiplying  two numbers")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"0, 2, 0", "2, 0, 0", "10, 3, 30", "3.1, 1.1, 3.41", "-1, 5, -5"

    })
    void testMultiply(float a, float b, float result) {
        assertEquals(result, calculator.multiply(a, b));
    }

}
