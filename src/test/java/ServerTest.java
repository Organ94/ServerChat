import org.example.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {

    private static final Config CONFIG = Config.getInstance();
    protected String host;
    protected int port;

    @BeforeEach
    void setUp() {
        final String path = "./config/setting.properties";
        try (FileReader reader = new FileReader(path)) {
            Properties prop = new Properties();
            prop.load(reader);

            host = prop.getProperty("host");
            port = Integer.parseInt(prop.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getInstance() {
        assertEquals(Config.getInstance(), CONFIG);
    }

    @Test
    void getPort() {
        assertEquals(CONFIG.getPort(), port);
    }

    @Test
    void getHost() {
        assertEquals(CONFIG.getHost(), host);
    }
}
