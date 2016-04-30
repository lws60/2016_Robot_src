package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousTest extends CommandGroup {
    
    public  AutonomousTest() {
    	// rough terrain addSequential(new AutonomousDriveStraightDistance(135, 0.6));
    	// rock wall addSequential(new AutonomousDriveStraightDistance(210, 0.9));
    	
    	addSequential(new AutonomousDriveStraightDistance(100, 0.5));
    	
    	//cheval
        /*addSequential(new AutonomousDriveStraightDistance(58, 0.2));
		addSequential(new ToggleArmPiston());
		addSequential(new Wait(25));
		addSequential(new AutonomousDriveStraightDistance(150, 0.6));
		addSequential(new Wait(25));*/
		
		//position 3/4
		/*addSequential(new Aim(0.7, 1));
		addSequential(new ToggleIntakePiston());
		addSequential(new Wait(25));
		addSequential(new HighShot());*/
    	
    	//position 2
		/*addSequential(new ToggleArmPiston());
		addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(90, 0.8, 2.5));
    	addSequential(new Wait(25));
    	addSequential(new AutonomousDriveStraightDistance(45, 0.7));
    	addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(20, 0.8, 2.5));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston());
    	addSequential(new Wait(50));
    	addSequential(new HighShot());*/
		
    	
        /*addSequential(new Wait(25));
		addSequential(new ToggleArmPiston());
		addSequential(new Wait(25));
		addSequential(new AutonomousTurnToAngle(0, 0.7, 1));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston()); 
    	addSequential(new Wait(50));
    	addSequential(new HighShot());*/
    	
    	// position 5
		/*addSequential(new AutonomousDriveStraight(-25, 0.5));
		addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(-15, 0.6, 2.5));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston());
    	addSequential(new Wait(25));
    	addSequential(new HighShot());*/
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
