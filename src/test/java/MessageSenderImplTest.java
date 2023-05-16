import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import org.mockito.Mockito;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTest {

    Map<String, String> headersTest;

    @BeforeEach
    public void createHeadersTes() {
        headersTest = new HashMap<String, String>();
    }

    @AfterEach
    public void deleteHeadersTes() {
        headersTest = null;
    }

    public static Stream<Arguments> byIpTestParameters() {
        return Stream.of(
                Arguments.of("135.12.4.1", new Location("Berlin", Country.GERMANY, "Noname", 3), Country.GERMANY, "Welcome"),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15), Country.RUSSIA, "Добро пожаловать"),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32), Country.USA, "Welcome"),
                Arguments.of("155.4.12.32", new Location("San-Paulo", Country.BRAZIL, "Pele", 42), Country.BRAZIL, "Welcome")
        );
    }

    @ParameterizedTest
    @MethodSource("byIpTestParameters")
    public void sendTest(String byIpArgument, Location byIpResult, Country countryExpected, String messageExpected) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(byIpArgument))
                .thenReturn(byIpResult);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(countryExpected))
                .thenReturn(messageExpected);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        headersTest.put(MessageSenderImpl.IP_ADDRESS_HEADER, byIpArgument);
        String result = messageSender.send(headersTest);
        Assertions.assertEquals(messageExpected, result);
    }
}


