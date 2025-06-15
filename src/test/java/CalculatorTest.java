import logs.LoggingExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(LoggingExtension.class)
public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void initAll() {
        System.out.println("Start of the suite");
    }

    @BeforeEach
    public void init() {
        calculator = new Calculator();

    }

    @AfterEach
    public void tearDown() {
        System.out.println("Finish of the test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Finish of the suite");
    }

    @Nested
    @DisplayName("Addition logic tests")
    public class AdditionTest {
        @ParameterizedTest
        @CsvSource({
                "2, 3, 5",
                "10, -5, 5",
                "0, 0, 0",
                "-2, -4, -6"
        })
        public void testAdd(int a, int b, int result) {
            assertThat(calculator.add(a, b), equalTo(result));
        }

        @Disabled("Disabled until some bug is fixed")
        @Test
        void testDisabled() {
            assertThat(calculator.add(0, 0), is(0));
        }
    }

    @Nested
    @DisplayName("Dividing logic tests")
    public class DividingTest {
        @ParameterizedTest
        @CsvSource({
                "0, 2, 0",
                "10, -5, -2",
                "3, 3, 1"
        })
        public void testDivide(int a, int b, int result) {
            assertThat(result, notNullValue());
            assertThat(calculator.divide(a, b), equalTo(result));
        }

        @Test
        @DisplayName("Test exception after dividing on zero")
        public void divideOnZero() {
            assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
        }
    }

    @Nested
    @DisplayName("Multiply logic test")
    public class MultipilyingTests {
        @ParameterizedTest
        @DisplayName("Multiplying")
        @MethodSource("addData")
        void multiply(int a, int b, int result) {
            assertThat(calculator.multiply(a, b), instanceOf(Integer.class));
            assertThat(calculator.multiply(a, b), equalTo(result));
        }

        static Stream<org.junit.jupiter.params.provider.Arguments> addData() {
            return Stream.of(
                    org.junit.jupiter.params.provider.Arguments.of(1, 2, 2),
                    org.junit.jupiter.params.provider.Arguments.of(-1, 10, -10),
                    org.junit.jupiter.params.provider.Arguments.of(-3, -3, 9),
                    org.junit.jupiter.params.provider.Arguments.of(5, 0, 0)
            );
        }
    }

    @Nested
    @DisplayName("Is Positive function tests")
    public class IsPositiveTests {
        @TestFactory
        Stream<DynamicTest> dynamicIsPositiveTests() {
            return Stream.of(-3, 0, 1, 5).map(num ->
                    DynamicTest.dynamicTest("Check whether " + num + " positive",
                            () -> {
                                assertThat(calculator.isPositive(num), instanceOf(Boolean.class));
                                assertThat(calculator.isPositive(num), equalTo(num > 0));
                            }

                    )
            );
        }
    }

}



