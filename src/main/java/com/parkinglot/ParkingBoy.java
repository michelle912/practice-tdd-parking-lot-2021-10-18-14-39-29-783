package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedTicketException;

public interface ParkingBoy {
    Ticket parkCar(Car car) throws NoAvailablePositionException;

    Car fetchCar(Ticket ticket) throws UnrecognizedTicketException;
}
