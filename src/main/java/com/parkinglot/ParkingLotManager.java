package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager implements ParkingBoy{
    private List<ParkingBoy> parkingBoyList;
    private List<ParkingLot> parkingLotList;

    public ParkingLotManager() {
        parkingBoyList = new ArrayList<>();
        parkingLotList = new ArrayList<>();
    }

    public ParkingLotManager(List<ParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    @Override
    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        return null;
    }

    @Override
    public Car fetchCar(Ticket ticket) throws UnrecognizedTicketException {
        return null;
    }

    public Ticket parkCarByParkingBoy(Car car, ParkingBoy parkingBoy) {
        return null;
    }

    public void setParkingLot(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }
}
