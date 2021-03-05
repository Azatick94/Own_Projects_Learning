package com.gridnine.testing;

import java.util.List;

public interface FlightsFilterable {

    public FlightsDataManager filterFlightsBeforeCurrentDate();

    public FlightsDataManager filterFlightsWithArrivalSegmentsBeforeDeparture();

    public FlightsDataManager filterFlightsAtGroundMoreThan2Hours();

}
