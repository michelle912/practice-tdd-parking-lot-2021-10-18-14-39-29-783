package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {

    @Test
    public void should_park_to_first_lot_when_parkCar_given_same_number_empty_positions() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = smartParkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());

    }

    @Test
    public void should_fetch_corresponding_car_when_fetchCar_given_valid_cars_and_cars_exist() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket1 = superSmartParkingBoy.parkCar(car1);
        Ticket ticket2 = superSmartParkingBoy.parkCar(car2);

        // when
        Car result1 = superSmartParkingBoy.fetchCar(ticket1);
        Car result2 = superSmartParkingBoy.fetchCar(ticket2);

        // then
        assertEquals(car1, result1);
        assertEquals(car2, result2);
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_unrecognized_ticket() throws Exception{
        // given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket invalidTicket = new Ticket();

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> superSmartParkingBoy.fetchCar(invalidTicket));
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_used_ticket() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        superSmartParkingBoy.fetchCar(ticket);

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> superSmartParkingBoy.fetchCar(ticket));
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_all_full_capacity() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        superSmartParkingBoy.parkCar(car1);
        superSmartParkingBoy.parkCar(car2);

        // when

        // then
        assertThrows(NoAvailablePositionException.class, () -> superSmartParkingBoy.parkCar(car3));
    }

    @Test
    public void should_park_to_second_lot_when_parkCar_given_second_has_more_empty_positions() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = smartParkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(5, parkingLot1.getCurrentCapacity());
        assertEquals(9, parkingLot2.getCurrentCapacity());

    }
}