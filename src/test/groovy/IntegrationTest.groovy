import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import mw.api.SpockApplication
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.SocketUtils
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

@SpringBootTest(classes = [SpockApplication],properties = "application.environment=integration")
class IntegrationTest extends Specification {

    private WIREMOCK_PORT=8282
    /**
     * Wybrano losowy otwarty port.
     */
    int port = WIREMOCK_PORT//SocketUtils.findAvailableTcpPort();
    /**
     * Do tworzenia zaślepek używamy biblioteki WireMock
     */
    WireMockServer wireMockServer;

    /**
     * W metodzie setup przed każdym testem uruchamiamy serwer WireMocka
     * na danym porcie oraz mapujemy, że statyczne wywołanie metod
     * z biblioteki WireMock zostanie wykonane wobec serwera działającego na
     * wspomnianym porcie.
     */
    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor(port);
    }

    /**
     * Po każdym teście zatrzymujemy serwer WireMock.
     */
    void cleanup() {
        wireMockServer.stop();
    }
}
