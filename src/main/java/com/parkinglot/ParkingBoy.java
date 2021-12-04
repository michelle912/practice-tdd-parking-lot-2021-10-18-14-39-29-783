package com.parkinglot;

import java.util.List;
import java.util.stream.Collectors;

import static com.parkinglot.ParkingLotConstants.UNRECOGNIZED_TICKET_EXCEPTION_MSG;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLotList = parkingLot;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        ParkingLot availableParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.getCurrentCapacity() > 0)
                .findFirst()
                .orElse(null);

        if (availableParkingLot == null) {
            return null;
        }

        return availableParkingLot.parkCar(car);
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedTicketException {
        ParkingLot correspondingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isValidTicket(ticket))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedTicketException(UNRECOGNIZED_TICKET_EXCEPTION_MSG));

        return correspondingLot.fetchCar(ticket);
    }


}
