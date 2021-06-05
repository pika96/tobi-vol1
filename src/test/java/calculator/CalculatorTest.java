package calculator;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @Test
    void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum("src/test/java/calculator/numbers.txt");

        assertThat(sum).isEqualTo(10);

    }
}
