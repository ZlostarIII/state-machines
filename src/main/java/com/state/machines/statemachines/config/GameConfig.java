package com.state.machines.statemachines.config;

import com.state.machines.statemachines.events.GameEvents;
import com.state.machines.statemachines.listener.GameStatesListener;
import com.state.machines.statemachines.states.GameStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class GameConfig extends EnumStateMachineConfigurerAdapter<GameStates, GameEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<GameStates, GameEvents> states) throws Exception {
        states.withStates()
                .initial(GameStates.EXPLORING_GOOD_HEALTH)
                .end(GameStates.DEFEAT)
                .states(EnumSet.allOf(GameStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<GameStates, GameEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(GameStates.EXPLORING_GOOD_HEALTH)
                .target(GameStates.UNDER_ATTACK_GOOD_HEALTH)
                .event(GameEvents.ATTACKED)
                .and()
                .withExternal()
                .source(GameStates.EXPLORING_GOOD_HEALTH)
                .target(GameStates.EXPLORING_GOOD_HEALTH)
                .event(GameEvents.FIND_CURE)
                .and()
                .withExternal()
                .source(GameStates.UNDER_ATTACK_GOOD_HEALTH)
                .target(GameStates.WIN)
                .event(GameEvents.BATTLE)
                .and()
                .withExternal()
                .source(GameStates.UNDER_ATTACK_GOOD_HEALTH)
                .target(GameStates.EXPLORING_GOOD_HEALTH)
                .event(GameEvents.RETREAT)
                .and()
                .withExternal()
                .source(GameStates.WIN)
                .target(GameStates.EXPLORING_BAD_HEALTH)
                .event(GameEvents.EXPLORE)
                .and()
                .withExternal()
                .source(GameStates.EXPLORING_BAD_HEALTH)
                .target(GameStates.EXPLORING_GOOD_HEALTH)
                .event(GameEvents.FIND_CURE)
                .and()
                .withExternal()
                .source(GameStates.EXPLORING_BAD_HEALTH)
                .target(GameStates.UNDER_ATTACK_BAD_HEALTH)
                .event(GameEvents.ATTACKED)
                .and()
                .withExternal()
                .source(GameStates.UNDER_ATTACK_BAD_HEALTH)
                .target(GameStates.EXPLORING_BAD_HEALTH)
                .event(GameEvents.RETREAT)
                .and()
                .withExternal()
                .source(GameStates.UNDER_ATTACK_BAD_HEALTH)
                .target(GameStates.DEFEAT)
                .event(GameEvents.BATTLE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<GameStates, GameEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new GameStatesListener())
        ;
    }

}
