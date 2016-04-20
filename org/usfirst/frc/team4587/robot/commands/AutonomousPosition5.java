package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import utility.Parameters;

/**
 *
 */
public class AutonomousPosition5 extends CommandGroup {
    
    public  AutonomousPosition5(long defense) {
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
    	if (defense == RobotMap.CHEVAL)
    	{
    		addSequential(new AutonomousDriveStraightDistance(40, 0.6));
    		addSequential(new Wait(25));
        	addSequential(new ToggleArmPiston());
        	addSequential(new Wait(25));
        	addSequential(new AutonomousDriveStraightDistance(100, 0.6));
    	}
    	else if (defense == RobotMap.PORTCULLIS)
    	{
    		addSequential(new ToggleArmPiston());
    		addSequential(new Wait(25));
    		addSequential(new AutonomousDriveStraightDistance(
            		Parameters.getInt("Leg 1 Distance (robot travel units)", 150), 
            		Parameters.getDouble("Leg 1 Speed", 0.4)));
    	}
    	else 
    	{
    		addSequential(new AutonomousDriveStraight(90, 0.7));
    		addSequential(new Wait(25));
    		addSequential(new ToggleArmPiston());
    		
    	}
    	addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(-30, 0.8, 2.5));
    	addSequential(new AutonomousDriveStraightDistance(45, 0.7));
    	addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToRelativeAngle(5, 0.8, 2.5));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston());
    	addSequential(new Wait(50));
    	addSequential(new HighShot());
    }
}
