package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;


public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void createLocalizationServiceObject() {
        localizationService = new LocalizationServiceImpl();

    }

    @AfterEach
    public void deleteLocalizationServiceObject() {
        localizationService = null;

    }

    @Test
    public void localeRussiaTest() {
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"GERMANY", "USA", "BRAZIL"})
    public void localeTest(Country country) {
        String result = localizationService.locale(country);
        Assertions.assertEquals("Welcome", result);
    }
}
