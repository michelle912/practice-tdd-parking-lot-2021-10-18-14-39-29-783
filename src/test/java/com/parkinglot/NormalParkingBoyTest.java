package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NormalParkingBoyTest {

    @Test
    public void should_return_ticket_when_parkCar_given_valid_car_and_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot));

        // when
        Ticket ticket = normalParkingBoy.parkCar(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    public void should_park_to_first_car_park_when_parkCar_given_valid_car_and_first_car_park_not_full_capacity() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = normalParkingBoy.parkCar(car);

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
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = normalParkingBoy.parkCar(car2);

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
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket1 = normalParkingBoy.parkCar(car1);
        Ticket ticket2 = normalParkingBoy.parkCar(car2);

        // when
        Car result1 = normalParkingBoy.fetchCar(ticket1);
        Car result2 = normalParkingBoy.fetchCar(ticket2);

        // then
        assertEquals(car1, result1);
        assertEquals(car2, result2);
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_unrecognized_ticket() throws Exception{
        // given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket invalidTicket = new Ticket();

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> normalParkingBoy.fetchCar(invalidTicket));
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_used_ticket() throws Exception{
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        Ticket ticket = normalParkingBoy.parkCar(car);
        normalParkingBoy.fetchCar(ticket);

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> normalParkingBoy.fetchCar(ticket));
    }

    @Test
    public void should_throw_exception_when_fetchCar_given_all_full_capacity() throws Exception{
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        normalParkingBoy.parkCar(car1);
        normalParkingBoy.parkCar(car2);

        // when

        // then
        assertThrows(NoAvailablePositionException.class, () -> normalParkingBoy.parkCar(car3));
    }
}
