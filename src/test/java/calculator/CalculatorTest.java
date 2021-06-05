package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    private Calculator calculator;
    private String numFilepath;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        numFilepath = "src/test/java/calculator/numbers.txt";
    }

    @Test
    void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(numFilepath)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(numFilepath)).isEqualTo(24);
    }

    @Test
    void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(numFilepath)).isEqualTo("1234");
    }
}
