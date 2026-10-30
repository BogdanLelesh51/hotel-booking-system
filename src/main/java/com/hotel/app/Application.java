package com.hotel.app;

import com.hotel.model.Booking;
import com.hotel.model.Guest;
import com.hotel.model.Room;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.InMemoryBookingRepository;
import com.hotel.service.BookingService;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {

        Properties config = new Properties();
        try (InputStream input = Application.class.getResourceAsStream("/hotel.properties")) {
            if (input == null) {
                throw new IllegalStateException("Resource hotel.properties was not found");
            }
            config.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read hotel.properties", e);
        }

        System.out.println("Отель: " + config.getProperty("hotel.name"));

        BookingRepository repository =
                new InMemoryBookingRepository();

        BookingService service =
                new BookingService(repository);

        Guest guest1 =
                new Guest(1, "Иван");

        Guest guest2 =
                new Guest(2, "Анна");

        Room room =
                new Room(101, "Double");

        Booking booking1 = new Booking(
                1,
                guest1,
                room,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 15)
        );

        Booking booking2 = new Booking(
                2,
                guest2,
                room,
                LocalDate.of(2026, 9, 12),
                LocalDate.of(2026, 9, 17)
        );

        service.createBooking(booking1);

System.out.println("Первое бронирование создано");

try {
    service.createBooking(booking2);
    System.out.println("Второе бронирование создано");
} catch (IllegalStateException e) {
    System.out.println("Ошибка бронирования: " + e.getMessage());
}
    }
}
