package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_parkCar_given_valid_car_and_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        // when
        Ticket ticket = parkingLot.parkCar(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_null_when_parkCar_given_valid_car_and_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.parkCar(car);

        // when
        Car incomingCar = new Car();

        // then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.parkCar(incomingCar));
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_return_car_when_fetchCar_given_valid_ticket_and_car_exists() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = parkingLot.parkCar(car);

        // when
        Car result = parkingLot.fetchCar(ticket);

        // then
        assertEquals(car, result);
    }

    @Test
    public void should_return_corresponding_car_when_fetchCar_given_valid_tickets_and_cars_exist() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket1 = parkingLot.parkCar(car1);
        Ticket ticket2 = parkingLot.parkCar(car2);

        // when
        Car result1 = parkingLot.fetchCar(ticket1);
        Car result2 = parkingLot.fetchCar(ticket2);

        // then
        assertEquals(car1, result1);
        assertEquals(car2, result2);
    }

    @Test
    public void should_return_null_when_fetchCar_given_invalid_ticket() {
        // given
        ParkingLot parkingLot = new ParkingLot();
        Ticket invalidTicket = new Ticket();

        // when
        Car result = parkingLot.fetchCar(invalidTicket);

        // then
        assertNull(result);
    }

    @Test
    public void should_return_null_when_fetchCar_given_used_ticket_and_car_not_exist() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = parkingLot.parkCar(car);
        parkingLot.fetchCar(ticket);

        // when
        Car result = parkingLot.fetchCar(ticket);

        // then
        assertNull(result);
    }

}