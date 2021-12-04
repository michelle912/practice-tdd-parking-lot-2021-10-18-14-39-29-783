package com.parkinglot;

import java.util.Comparator;
import java.util.List;

import static com.parkinglot.ParkingLotConstants.NO_AVAILABLE_POSITION_EXCEPTION_MSG;
import static com.parkinglot.ParkingLotConstants.UNRECOGNIZED_TICKET_EXCEPTION_MSG;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLotList;

    public SmartParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLotList = parkingLot;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        ParkingLot availableParkingLot = parkingLotList
                .stream()
                .max(Comparator.comparingInt(ParkingLot::getCurrentCapacity))
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION_EXCEPTION_MSG));

        return availableParkingLot.parkCar(car);
    }
}
