package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_parkCar_given_valid_car_and_not_full_capacity() {
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        // when
        Ticket ticket = parkingLot.parkCar(car);

        // then
        assertNotNull(ticket);
    }

}