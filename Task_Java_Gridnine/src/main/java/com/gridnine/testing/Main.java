package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // получение тестового набора
        List<Flight> lstFlights = FlightBuilder.createFlights();
//        printFlights(lstFlights);

        // Задания
        // 1) Отфильтровать и вывести перелеты с вылетом до текущего момента времени
        ConsoleHelper.printMessage("\n1) Отфильтровать и вывести перелеты с вылетом до текущего момента времени: ");
        FlightsDataManager flightsManager = new FlightsDataManager(lstFlights);
        List<Flight> lstFilteredFlights1 = flightsManager.filterFlightsBeforeCurrentDate().getLstFlights();
        printFlights(lstFilteredFlights1);

        // 2) Имеются сегменты с датой прилёта раньше даты вылета
        ConsoleHelper.printMessage("\n2) Имеются сегменты с датой прилёта раньше даты вылета: ");
        flightsManager = new FlightsDataManager(lstFlights);
        List<Flight> lstFilteredFlights2 = flightsManager.filterFlightsWithArrivalSegmentsBeforeDeparture().getLstFlights();
        printFlights(lstFilteredFlights2);

        // 3) Общее время, проведенное на земле превышает два часа. (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
        ConsoleHelper.printMessage("\n3) Общее время, проведенное на земле превышает два часа: ");
        flightsManager = new FlightsDataManager(lstFlights);
        List<Flight> lstFilteredFlights3 = flightsManager.filterFlightsAtGroundMoreThan2Hours().getLstFlights();
        printFlights(lstFilteredFlights3);

    }


    public static void printFlights(List<Flight> lstFlights) {
        // Принтуем сообщения о полетах.
        int i=1;
        for (Flight flight: lstFlights) {
            ConsoleHelper.printMessage("Flight №"+i+":");
            List<Segment> segments = flight.getSegments();
            for (Segment segment: segments)
                ConsoleHelper.printMessage("departureDate: "+segment.getDepartureDate()+", arrivalDate: "+segment.getArrivalDate());
            i++;
        }
    }

}
