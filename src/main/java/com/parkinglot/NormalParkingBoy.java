package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedTicketException;

import java.util.List;

import static com.parkinglot.ParkingLotConstants.NO_AVAILABLE_POSITION_EXCEPTION_MSG;
import static com.parkinglot.ParkingLotConstants.UNRECOGNIZED_TICKET_EXCEPTION_MSG;

public class NormalParkingBoy implements ParkingBoy{
    private List<ParkingLot> parkingLotList;

    public NormalParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLotList = parkingLot;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        ParkingLot availableParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.getCurrentCapacity() > 0)
                .findFirst()
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION_EXCEPTION_MSG));

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
