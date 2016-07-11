package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowbarOneBall extends CommandGroup {
    
    public  LowbarOneBall() {
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
    	addSequential(new ArmDown());
		addSequential(new LowerIntake());
    	addSequential(new Wait(25));
		addSequential(new AutonomousDriveStraightDistance(100, 0.6));
		addSequential(new ToggleIntakePiston());
		addSequential(new AutonomousDriveStraightDistance(80, 0.6));
		addSequential(new AutonomousTurnToAngle2(35, 15, 0.5, 2.5));
		addSequential(new ArmDown());
		addSequential(new LowerIntake());
    	addSequential(new Wait(35));
		addSequential(new Aim2(0.3, 13, 0.1, 1,0));
		addSequential(new Wait(35));
		addSequential(new HighShot());
    }
}
