import DeliveryCalculator.CargoDimension;
import DeliveryCalculator.DeliveryCalculator;
import DeliveryCalculator.ServiceWorkload;
import logs.LoggingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(LoggingExtension.class)
public class DeliveryCalculatorTests {

    @Test
    public void shouldThrowError(){
        assertThrows(IllegalArgumentException.class, () -> DeliveryCalculator.calculateDeliveryCost(100, CargoDimension.LARGE, true, ServiceWorkload.HIGH));
        }

    @ParameterizedTest
    @CsvSource({
            "1, SMALL, true, NORMAL, 450",
            "2, LARGE, true, INCREASED, 660",
            "9, LARGE, false, HIGH, 420",
            "10, SMALL, true, VERY_HIGH, 800",
            "29, LARGE, true, NORMAL, 700",
            "30, SMALL, false, VERY_HIGH, 480",
            "31, LARGE, false, NORMAL, 500",
            "1000, LARGE, false, VERY_HIGH, 800",
    })
    public void shouldReturnCorrectCost(int distance, CargoDimension size, boolean isFragile, ServiceWorkload workload, int result) {
        assertThat(DeliveryCalculator.calculateDeliveryCost(distance, size, isFragile, workload ), equalTo(result));
    }

    @ParameterizedTest
    @CsvSource({
            "1, SMALL, NORMAL",
            "2, SMALL, VERY_HIGH",
            "10, SMALL, INCREASED",
            "30, SMALL, NORMAL",
            "9, LARGE, NORMAL",

    })
    public void shouldReturnMinCost(int distance, CargoDimension size, ServiceWorkload workload) {
        assertThat(DeliveryCalculator.calculateDeliveryCost(distance, size, false,workload ), equalTo(400));
    }
    }

