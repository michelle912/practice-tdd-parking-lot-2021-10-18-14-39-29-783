package com.parkinglot;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_parkCar_given_valid_car_and_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot));

        // when
        Ticket ticket = parkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    public void should_park_to_first_car_park_when_parkCar_given_valid_car_and_first_car_park_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = parkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());
    }

    @Test
    public void should_park_to_second_car_park_when_parkCar_given_valid_car_and_first_car_park_full_capacity() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.parkCar(car1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = parkingBoy.parkCar(car2);

        // then
        assertNotNull(ticket);
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(9, parkingLot2.getCurrentCapacity());
    }

    @Test
    public void should_fetch_corresponding_car_when_fetchCar_given_valid_cars_and_cars_exist() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket1 = parkingBoy.parkCar(car1);
        Ticket ticket2 = parkingBoy.parkCar(car2);

        // when
        Car result1 = parkingBoy.fetchCar(ticket1);
        Car result2 = parkingBoy.fetchCar(ticket2);

        // then
        assertEquals(car1, result1);
        assertEquals(car2, result2);
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_unrecognized_ticket() throws Exception{
        // given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket invalidTicket = new Ticket();

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.fetchCar(invalidTicket));
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_used_ticket() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket = parkingBoy.parkCar(car);
        parkingBoy.fetchCar(ticket);

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.fetchCar(ticket));
    }
}
