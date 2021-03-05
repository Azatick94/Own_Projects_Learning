package com.gridnine.testing.util;


import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestFlightBuilder {

        public static Flight createFlight(final LocalDateTime... dates) {
            if ((dates.length % 2) != 0) {
                throw new IllegalArgumentException("you must pass an even number of dates");
            }

            List<Segment> segments = new ArrayList<>(dates.length / 2);
            for (int i = 0; i < (dates.length - 1); i += 2) {
                segments.add(new Segment(dates[i], dates[i + 1]));
            }
            return new Flight(segments);
        }
    }