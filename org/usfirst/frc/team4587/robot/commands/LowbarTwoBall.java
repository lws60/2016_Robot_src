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
		addSequential(new Wait(10));
		addSequential(new LowerIntake());
		addSequential(new Wait(55));
		
		addSequential(new AutonomousDriveArc2(16, 500, 1, 0.8, 0.3));
		addSequential(new AutonomousDriveArc2(19, 500, 1, 0.2, 0.85));
		addParallel(new StartIntakeMotors(0.0));
		//addSequential(new Wait(99999999));
		/*addSequential(new AutonomousDriveStraightAtAngle(10, 0.8, 0, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(10, 0.6));
		addSequential(new AutonomousTurnToAngle2(-12, 7, 0.5, 2.54));
		addParallel(new StartIntakeMotors(0.0));
		addSequential(new AutonomousDriveStraightAtAngle(21, 0.8, -14, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(20, 0.6));
		addSequential(new AutonomousTurnToAngle2(0, 8.5, 0.45, 2.54));*/
		addSequential(new AutonomousDriveStraightAtAngle(45, 0.6, 1, 0.1));
		addSequential(new AutonomousDriveStraightAtAngle(20, 1.0, 1, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(65, 0.6));
		addSequential(new ToggleIntakePiston());
		//addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 0.5));
		addSequential(new AutonomousDriveStraightAtAngle(20, 1.0, 1, 0.1));
    	//addSequential(new Wait(20));
		//addSequential(new AutonomousDriveStraightDistance(60, 0.6));
		addSequential(new AutonomousDriveArc2(50, 500, 1, 0.35, 0.85));
		//addSequential(new AutonomousDriveStraightAtAngle(20, 0.6, 35, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(20, 0.6));
		addSequential(new ArmDown());
		addSequential(new LowerIntake());
    	addSequential(new Wait(20));
		addSequential(new Aim2(0.3, 13, 0.1, 1,0));
		addSequential(new Wait(10));
		addSequential(new HighShot());
		addSequential(new AutonomousDriveArc2(-25, -500, -1, -0.3, -0.85));
		//addSequential(new Wait(15));
		//addSequential(new AutonomousDriveStraightAtAngle(-10, -0.6, 0, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(-7, -0.6));
    	/*addSequential(new Wait(10));
		addSequential(new AutonomousTurnToAngle2(0, 15, 0.6, 1));
    	addSequential(new Wait(20));*/
		addSequential(new AutonomousDriveStraightAtAngle(-20, -0.6, 1, 0.1));
		addSequential(new AutonomousDriveStraightDistance(-40, -0.6));
		addParallel(new StartIntakeMotors(1.0));
		//addSequential(new AutonomousDriveStraightDistance(-10, -0.6));
		//addSequential(new AutonomousDriveStraightDistance(-110, -0.5));
		//addSequential(new AutonomousDriveStraightDistance(-10, -0.2));
		//  addSequential(new AutonomousDriveStraightAtAngle(-80, -0.6, 10, 0.1));

		addSequential(new AutonomousDriveArc2(-90, -500, -1, -0.55, -0.3));
		//addSequential(new AutonomousDriveStraightDistance(-35, -0.5));
		//addSequential(new Wait(25));
		//addSequential(new AutonomousTurnToAngle2(0, 7, 0.25, 1));
		addSequential(new AutonomousDriveArc2(55, -500, -1, 0.6, 0.8));
		//addSequential(new AutonomousDriveStraightAtAngle(55, 0.9, 1, 0.1));
		addParallel(new StartIntakeMotors(0.0));
		addSequential(new AutonomousDriveStraightAtAngle(35, 0.9, 1, 0.1));
		//addSequential(new AutonomousDriveStraightDistance(65, 0.6));
		addSequential(new ToggleIntakePiston());
		//addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 0.5));
		//addSequential(new AutonomousDriveStraightDistance(50, 0.6));
		//addSequential(new AutonomousTurnToAngle2(0, 15, 0.5, 0.5));
		addSequential(new AutonomousDriveStraightDistance(30, 0.8));

		addSequential(new AutonomousDriveArc2(50, 500, 1, 0.35, 0.85));
		/*addSequential(new Wait(20));
		addSequential(new AutonomousTurnToAngle2(35, 15, 0.5, 1));
		addSequential(new AutonomousDriveStraightDistance(20, 0.8));*/
		addSequential(new ArmDown());
		addSequential(new LowerIntake());
    	addSequential(new Wait(10));
		addSequential(new Aim2(0.3, 13, 0.1, 1,0));
		addSequential(new Wait(10));
		addSequential(new HighShot());
    }
}
