package com.state.machines.statemachines.utils;

import com.state.machines.statemachines.events.GameEvents;
import com.state.machines.statemachines.states.GameStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class LoggingUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger("FIRST PERSON SHOOTER GAME");

    public static String getStateInfo(State<GameStates, GameEvents> state) {
        return state != null ? state.getId().name() : "EMPTY STATE";
    }

    public static String getTransitionInfo(Transition<GameStates, GameEvents> t) {
        return String.format("[%s: %s]",
                t.getSource() != null ? t.getSource().getId() : "EMPTY",
                t.getTarget() != null ? t.getTarget().getId() : "EMPTY"
        );
    }
}
