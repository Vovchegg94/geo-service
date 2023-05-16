import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    GeoServiceImpl geoServiceImpl;

    @BeforeEach
    public void createGeoServiceObject() {
        geoServiceImpl = new GeoServiceImpl();

    }

    @AfterEach
    public void deleteGeoServiceObject() {
        geoServiceImpl = null;

    }

    public static Stream<Arguments> addTestParameters() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.12.32", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.7.43.78", new Location("New York", Country.USA, null, 0)),
                Arguments.of("8.8.8.8", null));
    }

    @ParameterizedTest
    @MethodSource("addTestParameters")
    public void byIpTest(String argument, Location expected) {
        GeoServiceImpl test = new GeoServiceImpl();
        Location result = test.byIp(argument);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void byCoordinatesTest() {
        Class<RuntimeException> expected = RuntimeException.class;
        Executable executable = () -> geoServiceImpl.byCoordinates(3324.223, 2342.231);
        Assertions.assertThrowsExactly(expected, executable);
    }
}
