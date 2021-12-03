package com.parkinglot;

import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
