package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_parkCar_given_valid_car_and_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        // when
        Ticket ticket = parkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
    }
}
