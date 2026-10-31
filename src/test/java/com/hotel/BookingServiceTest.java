package com.hotel;

import com.hotel.model.Booking;
import com.hotel.model.Guest;
import com.hotel.model.Room;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.InMemoryBookingRepository;
import com.hotel.service.BookingService;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingServiceTest {

    @Test
    void shouldCreateBookingSuccessfully() {

        BookingRepository repository =
                new InMemoryBookingRepository();

        BookingService service =
                new BookingService(repository);

        Guest guest =
                new Guest(1, "Иван");

        Room room =
                new Room(101, "Double");

        Booking booking = new Booking(
                1,
                guest,
                room,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 15)
        );

        service.createBooking(booking);

        assertEquals(1, repository.findAll().size());
    }
@Test
void shouldThrowExceptionWhenDatesOverlapForSameRoom() {

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

    Booking firstBooking = new Booking(
            1,
            guest1,
            room,
            LocalDate.of(2026, 9, 10),
            LocalDate.of(2026, 9, 15)
    );

    Booking secondBooking = new Booking(
            2,
            guest2,
            room,
            LocalDate.of(2026, 9, 12),
            LocalDate.of(2026, 9, 17)
    );

    service.createBooking(firstBooking);

    assertThrows(IllegalStateException.class, () -> {
        service.createBooking(secondBooking);
    });
   assertEquals(1, repository.findAll().size());
}
@Test
void shouldAllowSameDatesForDifferentRooms() {

    BookingRepository repository =
            new InMemoryBookingRepository();

    BookingService service =
            new BookingService(repository);

    Guest guest1 =
            new Guest(1, "Иван");

    Guest guest2 =
            new Guest(2, "Анна");

    Room room1 =
            new Room(101, "Double");

    Room room2 =
            new Room(102, "Double");

    Booking firstBooking = new Booking(
            1,
            guest1,
            room1,
            LocalDate.of(2026, 9, 10),
            LocalDate.of(2026, 9, 15)
    );

    Booking secondBooking = new Booking(
            2,
            guest2,
            room2,
            LocalDate.of(2026, 9, 10),
            LocalDate.of(2026, 9, 15)
    );

    service.createBooking(firstBooking);

    service.createBooking(secondBooking);

    assertEquals(2, repository.findAll().size());
}
@Test
void shouldAllowBookingStartingOnPreviousCheckoutDate() {
    BookingRepository repository = new InMemoryBookingRepository();
    BookingService service = new BookingService(repository);

    Room room = new Room(101, "Double");

    Booking firstBooking = new Booking(
            1,
            new Guest(1, "Иван"),
            room,
            LocalDate.of(2026, 9, 10),
            LocalDate.of(2026, 9, 15)
    );

    Booking secondBooking = new Booking(
            2,
            new Guest(2, "Анна"),
            room,
            LocalDate.of(2026, 9, 15),
            LocalDate.of(2026, 9, 20)
    );

    service.createBooking(firstBooking);
    service.createBooking(secondBooking);

    assertEquals(2, repository.findAll().size());
}
}