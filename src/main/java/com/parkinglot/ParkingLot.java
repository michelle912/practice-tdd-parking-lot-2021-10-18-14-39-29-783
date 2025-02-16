package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

import static com.parkinglot.ParkingLotConstants.*;

public class ParkingLot {


    private int maxCapacity;
    Map<Ticket, Car> ticketToCarMap = new HashMap<>();

    public ParkingLot() {
        maxCapacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException {
        if (!hasAvailableSlot()) {
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION_EXCEPTION_MSG);
        }
        Ticket ticket = new Ticket();
        ticketToCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) throws UnrecognizedTicketException {
        if (!isValidTicket(ticket)) {
            throw new UnrecognizedTicketException(UNRECOGNIZED_TICKET_EXCEPTION_MSG);
        }

        return ticketToCarMap.remove(ticket);
    }

    private boolean hasAvailableSlot() {
        return ticketToCarMap.size() < maxCapacity;
    }

    protected boolean isValidTicket(Ticket ticket) {
        return ticketToCarMap.containsKey(ticket);
    }

    public int getCurrentCapacity() {
        return maxCapacity - ticketToCarMap.size();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAvailablePositionRate() {
        return getCurrentCapacity() / maxCapacity;
    }
}
