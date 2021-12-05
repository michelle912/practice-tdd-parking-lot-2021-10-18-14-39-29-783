package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.ParkingBoyNotUnderManagementException;
import com.parkinglot.Exception.UnrecognizedTicketException;

import java.util.ArrayList;
import java.util.List;

import static com.parkinglot.ParkingLotConstants.*;

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
        ParkingLot availableParkingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.getCurrentCapacity() > 0)
                .findFirst()
                .orElseThrow(() -> new NoAvailablePositionException(NO_AVAILABLE_POSITION_EXCEPTION_MSG));

        return availableParkingLot.parkCar(car);
    }

    @Override
    public Car fetchCar(Ticket ticket) throws UnrecognizedTicketException {
        ParkingLot correspondingLot = parkingLotList
                .stream()
                .filter(parkingLot -> parkingLot.isValidTicket(ticket))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedTicketException(UNRECOGNIZED_TICKET_EXCEPTION_MSG));

        return correspondingLot.fetchCar(ticket);
    }

    public Ticket parkCarByParkingBoy(Car car, ParkingBoy parkingBoy) throws Exception {
        if (!isParkingBoyUnderManagement(parkingBoy)) {
            throw new ParkingBoyNotUnderManagementException(PARKING_BOY_NOT_UNDER_MANAGEMENT_EXCEPTION_MSG);
        }

        return parkingBoy.parkCar(car);
    }

    public Car fetchCarByParkingBoy(Ticket ticket, ParkingBoy parkingBoy) throws Exception {
        if (!isParkingBoyUnderManagement(parkingBoy)) {
            throw new ParkingBoyNotUnderManagementException(PARKING_BOY_NOT_UNDER_MANAGEMENT_EXCEPTION_MSG);
        }

        return parkingBoy.fetchCar(ticket);
    }

    public void setParkingLot(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    private boolean isParkingBoyUnderManagement(ParkingBoy parkingBoy) {
        return parkingBoyList.contains(parkingBoy);
    }
}
