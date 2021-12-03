package com.parkinglot;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        return parkingLot.parkCar(car);
    }
}
