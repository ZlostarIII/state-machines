package com.state.machines.statemachines.listener;

import com.state.machines.statemachines.events.GameEvents;
import com.state.machines.statemachines.states.GameStates;
import com.state.machines.statemachines.utils.LoggingUtils;
import org.slf4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class GameStatesListener implements StateMachineListener<GameStates, GameEvents> {

    private static final Logger LOGGER = LoggingUtils.LOGGER;

    @Override
    public void stateChanged(State<GameStates, GameEvents> from, State<GameStates, GameEvents> to) {
        LOGGER.info("State changed from {} to {}", LoggingUtils.getStateInfo(from), LoggingUtils.getStateInfo(to));
    }

    @Override
    public void stateEntered(State<GameStates, GameEvents> state) {
        LOGGER.info("Entered state {}", LoggingUtils.getStateInfo(state));
    }

    @Override
    public void stateExited(State<GameStates, GameEvents> state) {
        LOGGER.info("Exited state {}", LoggingUtils.getStateInfo(state));
    }

    @Override
    public void eventNotAccepted(Message<GameEvents> event) {
        LOGGER.error("Event not accepted at this state: {}", event.getPayload());
    }

    @Override
    public void transition(Transition<GameStates, GameEvents> transition) {
        LOGGER.info("Transition from state {} to state {}", transition.getSource(), transition.getTarget());
    }

    @Override
    public void transitionStarted(Transition<GameStates, GameEvents> transition) {

    }

    @Override
    public void transitionEnded(Transition<GameStates, GameEvents> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<GameStates, GameEvents> stateMachine) {
        LOGGER.info("Machine started: {}", stateMachine);
    }

    @Override
    public void stateMachineStopped(StateMachine<GameStates, GameEvents> stateMachine) {
        LOGGER.info("Machine stopped: {}", stateMachine);
    }

    @Override
    public void stateMachineError(StateMachine<GameStates, GameEvents> stateMachine, Exception exception) {
        LOGGER.info("Machine error: {}", stateMachine);
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {

    }

    @Override
    public void stateContext(StateContext<GameStates, GameEvents> stateContext) {

    }
}
