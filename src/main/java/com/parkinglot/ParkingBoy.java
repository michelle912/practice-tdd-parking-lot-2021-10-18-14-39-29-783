package com.parkinglot;

public interface ParkingBoy {
    Ticket parkCar(Car car) throws NoAvailablePositionException;

    Car fetchCar(Ticket ticket) throws UnrecognizedTicketException;
}
