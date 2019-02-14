package com.state.machines.statemachines;

import com.state.machines.statemachines.events.GameEvents;
import com.state.machines.statemachines.states.GameStates;
import com.state.machines.statemachines.utils.LoggingUtils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

@Configuration
@SpringBootApplication
@EnableStateMachine
public class StateMachinesApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggingUtils.LOGGER;

	private final StateMachine<GameStates, GameEvents> stateMachine;

	@Autowired
	public StateMachinesApplication(StateMachine<GameStates, GameEvents> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(StateMachinesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.sendEvent(GameEvents.BATTLE); // Wrong, as this event is not acceptable in the initial state
		LOGGER.info("You tried battling a nonexisting attacker!");

		// Enter an epic battle scenario
		LOGGER.info("You are under attack");
		stateMachine.sendEvent(GameEvents.ATTACKED);
		
		LOGGER.info("You chose to retreat");
		stateMachine.sendEvent(GameEvents.RETREAT);
		
		LOGGER.info("You were attacked again");
		stateMachine.sendEvent(GameEvents.ATTACKED);
		
		LOGGER.info("You choose to battle this time");
		stateMachine.sendEvent(GameEvents.BATTLE);
		
		LOGGER.info("You won, but you are harmed! Anyway, you are back exploring the land");
		stateMachine.sendEvent(GameEvents.EXPLORE);
		
		LOGGER.info("You just found a cure and your health is restored");
		stateMachine.sendEvent(GameEvents.FIND_CURE);
		
		LOGGER.info("A ferotious attacker appears again");
		stateMachine.sendEvent(GameEvents.ATTACKED);
		
		LOGGER.info("You defeat him again");
		stateMachine.sendEvent(GameEvents.BATTLE);
		
		LOGGER.info("Harmed again but back on track");
		stateMachine.sendEvent(GameEvents.EXPLORE);
		
		LOGGER.info("Would those monsters stop appearing");
		stateMachine.sendEvent(GameEvents.ATTACKED);
		
		LOGGER.info("You try to retreat");
		stateMachine.sendEvent(GameEvents.RETREAT);
		
		LOGGER.info("But the monster reappers");
		stateMachine.sendEvent(GameEvents.ATTACKED);
		
		LOGGER.info("You choose to fight with him but you are too weak and you lose :(");
		stateMachine.sendEvent(GameEvents.BATTLE);
		
	}
}
