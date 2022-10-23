package log;

import org.example.logger.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private static final Logger LOGGER = Logger.getInstance();
    private static final String MESSAGE = "Test message LoggerTest";

    @Test
    void getInstance() {
        assertEquals(Logger.getInstance(), LOGGER);
    }
    @Test
    void log() {
        assertTrue(LOGGER.log(MESSAGE));
    }
}
