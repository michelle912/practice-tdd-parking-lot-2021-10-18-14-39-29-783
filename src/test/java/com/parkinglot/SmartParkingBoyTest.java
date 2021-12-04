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

}