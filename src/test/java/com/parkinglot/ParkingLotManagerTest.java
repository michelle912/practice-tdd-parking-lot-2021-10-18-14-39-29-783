package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.ParkingBoyNotUnderManagementException;
import com.parkinglot.Exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotManagerTest {

    @Test
    public void should_park_car_in_parkingboy_car_park_when_parkCarByParkingBoy_given_valid_parkingBoy_valid_car_not_full_capacity() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(normalParkingBoy));

        // when
        Ticket ticket = parkingLotManager.parkCarByParkingBoy(car, normalParkingBoy);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());

    }


    @Test
    public void should_throw_exception_when_parkCarByParkingBoy_given_invalid_parkingBoy() {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(Collections.emptyList());
        ParkingLotManager parkingLotManager = new ParkingLotManager();

        // when

        // then
        assertThrows(ParkingBoyNotUnderManagementException.class, () -> parkingLotManager.parkCarByParkingBoy(car, normalParkingBoy)); // FIXME:

    }

    @Test
    public void should_throw_exception_when_parkCarByParkingBoy_given_valid_parkingBoy_valid_car_full_capacity() throws Exception {
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(normalParkingBoy));
        parkingLotManager.parkCarByParkingBoy(car1, normalParkingBoy);

        // when

        // then
        assertThrows(NoAvailablePositionException.class, () -> parkingLotManager.parkCarByParkingBoy(car2, normalParkingBoy));

    }

    @Test
    public void should_fetch_car_in_parkingboy_car_park_when_fetchCarByParkingBoy_given_valid_ticket_and_car_exist_in_parkingboy_car_park() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(normalParkingBoy));
        Ticket ticket = parkingLotManager.parkCarByParkingBoy(car, normalParkingBoy);

        // when
        Car result = parkingLotManager.fetchCarByParkingBoy(ticket, normalParkingBoy);

        // then
        assertNotNull(result);

    }

    @Test
    public void should_throw_exception_when_fetchCarByParkingBoy_given_invalid_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(normalParkingBoy));

        // when
        Ticket ticket = new Ticket();

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingLotManager.fetchCarByParkingBoy(ticket, normalParkingBoy));

    }

    @Test
    public void should_throw_exception_when_fetchCarByParkingBoy_given_used_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        NormalParkingBoy normalParkingBoy = new NormalParkingBoy(List.of(parkingLot1, parkingLot2));
        ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(normalParkingBoy));
        Ticket ticket = parkingLotManager.parkCarByParkingBoy(car, normalParkingBoy);
        parkingLotManager.fetchCarByParkingBoy(ticket, normalParkingBoy);

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingLotManager.fetchCarByParkingBoy(ticket, normalParkingBoy));

    }

    @Test
    public void should_park_car_in_own_car_park_when_parkCar_given_valid_car_not_full_capacity() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = parkingLotManager.parkCar(car);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());

    }

    @Test
    public void should_fetch_car_in_own_car_park_when_fetchCar_given_valid_ticket_and_car_exist() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1, parkingLot2));
        Ticket ticket = parkingLotManager.parkCar(car);

        // when
        Car result = parkingLotManager.fetchCar(ticket);

        // then
        assertEquals(car, result);

    }

    @Test
    public void should_park_to_own_second_car_park_when_parkCar_given_valid_car_first_car_park_full_capacity() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1, parkingLot2));
        parkingLotManager.parkCar(car1);
        assertEquals(0, parkingLot1.getCurrentCapacity());
        assertEquals(10, parkingLot2.getCurrentCapacity());

        // when
        Ticket ticket = parkingLotManager.parkCar(car2);

        // then
        assertNotNull(ticket);
        assertEquals(9, parkingLot2.getCurrentCapacity());

    }

    @Test
    public void should_throw_exception_when_parkCar_given_valid_car_full_capacity() throws Exception {
        // given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1));
        parkingLotManager.parkCar(car1);

        // when

        // then
        assertThrows(NoAvailablePositionException.class, () -> parkingLotManager.parkCar(car2));

    }

    @Test
    public void should_throw_exception_when_fetchCar_given_invalid_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1, parkingLot2));

        // when
        Ticket ticket = new Ticket();

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingLotManager.fetchCar(ticket));

    }

    @Test
    public void should_throw_exception_when_fetchCar_given_used_ticket() throws Exception {
        // given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        parkingLotManager.setParkingLot(List.of(parkingLot1, parkingLot2));
        Ticket ticket = parkingLotManager.parkCar(car);
        parkingLotManager.fetchCar(ticket);

        // when

        // then
        assertThrows(UnrecognizedTicketException.class, () -> parkingLotManager.fetchCar(ticket));

    }

}