package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowbarTwoBall extends CommandGroup {
    
    public  LowbarTwoBall() {
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
		addParallel(new StartIntakeMotors(1.0));
		addSequential(new Wait(20));
		addSequential(new ToggleIntakePiston());
		addSequential(new Wait(75));
		addSequential(new AutonomousDriveStraightDistance(10, 0.6));
		addSequential(new AutonomousTurnToAngle2(-12, 15, 0.5, 2.5));
		addParallel(new StartIntakeMotors(0.0));
		addSequential(new AutonomousDriveStraightDistance(20, 0.6));
		addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 2.5));
		addSequential(new AutonomousDriveStraightDistance(65, 0.6));
		addSequential(new ToggleIntakePiston());
		addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 0.5));
		addSequential(new AutonomousDriveStraightDistance(60, 0.6));
		addSequential(new AutonomousTurnToAngle2(35, 15, 0.5, 2.5));
		addSequential(new ArmDown());
		addSequential(new LowerIntake());
    	addSequential(new Wait(35));
		addSequential(new Aim2(0.3, 13, 0.1, 1,0));
		addSequential(new Wait(35));
		addSequential(new HighShot());
		addSequential(new Wait(15));
		//addSequential(new AutonomousDriveStraightDistance(-7, -0.6));
		addSequential(new AutonomousTurnToAngle2(2, 10, 0.3, 10));
		addSequential(new Wait(15));
		addSequential(new AutonomousTurnToAngle2(0, 7, 0.25, 1));
		addSequential(new AutonomousDriveStraightDistance(-110, -0.5));
		addSequential(new AutonomousDriveStraightDistance(-10, -0.2));
		addParallel(new StartIntakeMotors(1.0));
		addSequential(new AutonomousDriveStraightDistance(-35, -0.5));
		addSequential(new Wait(75));
		addParallel(new StartIntakeMotors(0.0));
		addSequential(new AutonomousTurnToAngle2(0, 7, 0.25, 1));
		addSequential(new AutonomousDriveStraightDistance(65, 0.6));
		addSequential(new ToggleIntakePiston());
		addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 0.5));
		addSequential(new AutonomousDriveStraightDistance(50, 0.6));
    }
}
