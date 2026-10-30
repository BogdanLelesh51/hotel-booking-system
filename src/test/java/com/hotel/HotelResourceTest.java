package com.hotel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HotelResourceTest {

    @Test
    void configurationIsAvailableOnClasspath() throws IOException {
        try (InputStream input = getClass().getResourceAsStream("/hotel.properties")) {
            assertNotNull(input);
            Properties properties = new Properties();
            properties.load(input);
            assertEquals("Hotel Booking System", properties.getProperty("hotel.name"));
        }
    }
}
