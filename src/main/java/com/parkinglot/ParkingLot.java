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

    public Ticket parkCar(Car car) {
        if (!hasAvailableSlot()) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketToCarMap.put(ticket, car);
        return ticket;
    }

    public Car fetchCar(Ticket ticket) {
        if (!isValidTicket(ticket)) {
            return null;
        }

        Car carToFetch = ticketToCarMap.get(ticket);
        return ticketToCarMap.remove(ticket);
    }

    private boolean hasAvailableSlot() {
        return ticketToCarMap.size() < maxCapacity;
    }

    private boolean isValidTicket(Ticket ticket) {
        return ticketToCarMap.containsKey(ticket);
    }

}
