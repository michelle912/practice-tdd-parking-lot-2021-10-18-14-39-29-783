package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int DEFAULT_CAPACITY = 10;
    private int maxCapacity;
    Map<Ticket, Car> ticketToCarMap = new HashMap<>();

    public ParkingLot() {
        maxCapacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Ticket parkCar(Car car) throws NoAvailablePositionException{
        if (!hasAvailableSlot()) {
            throw new NoAvailablePositionException("No available position.");
        }
        Ticket ticket = new Ticket();
        ticketToCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) {
        if (!isValidTicket(ticket)) {
            return null;
        }

        return ticketToCarMap.remove(ticket);
    }

    private boolean hasAvailableSlot() {
        return ticketToCarMap.size() < maxCapacity;
    }

    private boolean isValidTicket(Ticket ticket) {
        return ticketToCarMap.containsKey(ticket);
    }

}
