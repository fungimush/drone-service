package com.musala.droneservice.utils;

import com.musala.droneservice.utils.enums.DroneStateTransition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneStateTransitionTest {

    @Test
    void nextState() {

        DroneStateTransition state = DroneStateTransition.IDLE;

        state = state.nextState();
        assertEquals(DroneStateTransition.LOADING, state);

        state = state.nextState();
        assertEquals(DroneStateTransition.LOADED, state);

        state = state.nextState();
        assertEquals(DroneStateTransition.DELIVERING, state);

        state = state.nextState();
        assertEquals(DroneStateTransition.DELIVERED, state);

        state = state.nextState();
        assertEquals(DroneStateTransition.RETURNING, state);

        state = state.nextState();
        assertEquals(DroneStateTransition.RETURNING, state);
    }

}

