import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {

    static final Logger log = getLogger(lookup().lookupClass());
    private final Calculator calc = new Calculator();

    @ParameterType("\\d+")
    public int number(String number) {
        return Integer.parseInt(number);
    }

    @Given("a calculator I just turned on")
    public void setup() {
        log.debug("Calculator initialized.");
    }

    @When("I add {number} and {number}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I subtract {number} from {number}")
    public void subtract(int arg1, int arg2) {
        log.debug("Subtracting {} from {}", arg2, arg1);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {number} by {number}")
    public void multiply(int arg1, int arg2) {
        log.debug("Multiplying {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {number} by {number}")
    public void divide(int arg1, int arg2) {
        log.debug("Dividing {} by {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @Then("an error is thrown")
    public void error() {
        Exception exception = assertThrows(ArithmeticException.class, calc::value);
        log.debug("Error thrown: {}", exception.getMessage());
    }

    @Then("the result is {number}")
    public void the_result_is(int expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value.doubleValue(), "Incorrect calculation result");
    }
}