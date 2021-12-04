package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    @Test
    public void should_park_to_first_lot_when_parkCar_given_same_number_empty_positions() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = smartParkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());

    }

    @Test
    public void should_park_to_second_lot_when_parkCar_given_second_has_more_empty_positions() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = smartParkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(1, parkingLot1.getCurrentCapacity());
        assertEquals(9, parkingLot2.getCurrentCapacity());

    }

    @Test
    public void should_fetch_corresponding_car_when_fetchCar_given_valid_cars_and_cars_exist() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket1 = smartParkingBoy.parkCar(car1);
        Ticket ticket2 = smartParkingBoy.parkCar(car2);

        // when
        Car result1 = smartParkingBoy.fetchCar(ticket1);
        Car result2 = smartParkingBoy.fetchCar(ticket2);

        // then
        assertEquals(car1, result1);
        assertEquals(car2, result2);
    }

}