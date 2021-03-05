package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightsDataManager implements FlightsFilterable {

    private List<Flight> lstFlights;

    public FlightsDataManager(List<Flight> lstFlights) {
        this.lstFlights = lstFlights;
    }

    @Override
    public FlightsDataManager filterFlightsBeforeCurrentDate() {
        // 1) Отфильтровать и вывести перелеты с вылетом до текущего момента времени
        List<Flight> lstFilteredFlights = new ArrayList<>();
        for (Flight flight: this.lstFlights) {
            LocalDateTime firstDepartureDate =  flight.getSegments().get(0).getDepartureDate();
            if (firstDepartureDate.isBefore(LocalDateTime.now()))
                lstFilteredFlights.add(flight);
        }
        setLstFlights(lstFilteredFlights);
        return this;
    }

    @Override
    public FlightsDataManager filterFlightsWithArrivalSegmentsBeforeDeparture() {
        List<Flight> lstFilteredFlights = new ArrayList<>();
        for (Flight flight: lstFlights) {
            List<Segment> segments = flight.getSegments();
            for (Segment segment: segments) {
                LocalDateTime departureDate =  segment.getDepartureDate();
                LocalDateTime arrivalDate =  segment.getArrivalDate();
                if (arrivalDate.isBefore(departureDate)) {
                    lstFilteredFlights.add(flight);
                    continue;
                }
            }
        }
        setLstFlights(lstFilteredFlights);
        return this;
    }

    @Override
    public FlightsDataManager filterFlightsAtGroundMoreThan2Hours() {
        List<Flight> lstFilteredFlights = new ArrayList<>();
        for (Flight flight: lstFlights) {
            List<Segment> segments = flight.getSegments();
            if (segments.size()>1) {
                int sum = 0;
                // итерируем по сегментам во flight и суммируем часы
                for (int i = 0; i < segments.size()-1; i++) {
                    Segment currentSegment = segments.get(i);
                    Segment nextSegment = segments.get(i+1);
                    if (nextSegment.getDepartureDate().isAfter(currentSegment.getArrivalDate())) {
                        long diff = ChronoUnit.HOURS.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate());
                        sum+=diff;
                    }
                }
                // если в сумме больше 2 часов, то добавляем в список.
                if (sum>2)
                    lstFilteredFlights.add(flight);
            }
        }
        setLstFlights(lstFilteredFlights);
        return this;
    }

    public void setLstFlights(List<Flight> lstFlights) {
        this.lstFlights = lstFlights;
    }

    public List<Flight> getLstFlights() {
        return lstFlights;
    }


}









