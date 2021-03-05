package com.gridnine.testing;

import com.gridnine.testing.util.TestFlightBuilder;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FlightsDataManagerTest {

    LocalDateTime date11 = LocalDateTime.now().minusDays(2);
    LocalDateTime date12 = LocalDateTime.now().minusDays(1);
    LocalDateTime date13 = LocalDateTime.now();
    LocalDateTime date14 = LocalDateTime.now().plusDays(1);
    LocalDateTime date15 = LocalDateTime.now().plusDays(3);

    @org.junit.jupiter.api.Test
    void filterFlightsBeforeCurrentDate(){
        // проверка 1 фильтра
        List<Flight> flights = new ArrayList<>();
        flights.add(TestFlightBuilder.createFlight(date11, date12));
        flights.add(TestFlightBuilder.createFlight(date13, date12));
        flights.add(TestFlightBuilder.createFlight(date14, date15));

        FlightsDataManager flightsManager = new FlightsDataManager(flights);
        List<Flight> filteredFlights = flightsManager.filterFlightsBeforeCurrentDate().getLstFlights();

        Assertions.assertEquals(2, filteredFlights.size());
    }

    @org.junit.jupiter.api.Test
    void  filterFlightsWithArrivalSegmentsBeforeDeparture(){
        // проверка 2 фильтра
        List<Flight> flights = new ArrayList<>();
        flights.add(TestFlightBuilder.createFlight(date12, date11, date14, date15));
        flights.add(TestFlightBuilder.createFlight(date11, date12, date13, date15));

        FlightsDataManager flightsManager = new FlightsDataManager(flights);
        List<Flight> filteredFlights = flightsManager.filterFlightsWithArrivalSegmentsBeforeDeparture().getLstFlights();

        Assertions.assertEquals(1, filteredFlights.size());
    }

    @org.junit.jupiter.api.Test
    void  filterFlightsAtGroundMoreThan2Hours(){
        // проверка 3 фильтра
        List<Flight> flights = new ArrayList<>();
        flights.add(TestFlightBuilder.createFlight(date11, date12, date12, date13));
        flights.add(TestFlightBuilder.createFlight(date11, date12, date12, date13, date13, date14));
        flights.add(TestFlightBuilder.createFlight(date11, date12, date13, date14));
        flights.add(TestFlightBuilder.createFlight(date11, date12, date12.plusHours(3), date14));

        FlightsDataManager flightsManager = new FlightsDataManager(flights);
        List<Flight> filteredFlights = flightsManager.filterFlightsAtGroundMoreThan2Hours().getLstFlights();

        Assertions.assertEquals(2, filteredFlights.size());
    }

    @org.junit.jupiter.api.Test
    void filterBeforeCurrentDateANDFlightsMoreThan2Hours() {

        // проверка последовательности из 2 фильтров.
        List<Flight> flights = new ArrayList<>();
        flights.add(TestFlightBuilder.createFlight(date14, date15, date15, date15.plusHours(1))); // не проходит по 1 фильтру
        flights.add(TestFlightBuilder.createFlight(date11, date12, date12.plusHours(1), date13)); // не проходит по 3 фильтру (длительность=1час)
        flights.add(TestFlightBuilder.createFlight(date11, date12, date12.plusHours(1), date13, date13.plusHours(2), date14)); // проходит по 1 и 3 фильтру

        FlightsDataManager flightsManager = new FlightsDataManager(flights);
        List<Flight> filteredFlights = flightsManager.filterFlightsBeforeCurrentDate().filterFlightsAtGroundMoreThan2Hours().getLstFlights();
        Assertions.assertEquals(1, filteredFlights.size());

    }







}