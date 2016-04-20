package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import utility.Parameters;

/**
 *
 */
public class AutonomousDriveForTime extends CommandGroup {
    
    public  AutonomousDriveForTime() {
        // Add Commands here:
    	/*/if (Robot.isExtensionsUp())
    	//{
    		if (Robot.getArmPiston().getArmPiston() == false)
    		{
    			addSequential(new ToggleArmPiston());
    		}
    		if (Robot.getIntakePiston().getIntakePiston() == false)
    		{
    			addSequential(new ToggleIntakePiston());
    		}
    		addSequential(new Wait(100));
    	//}*/
    	
    	addSequential(new AutonomousDriveStraight(
        		Parameters.getInt("Leg 1 Distance (inches)", 150), 
        		Parameters.getDouble("Leg 1 Speed", 0.7)));/*
    	addSequential(new AutonomousDriveStraightISH(100, 0.5, 0.0));
        //addSequential(new Wait(100));
        /*addSequential(new AutonomousTurnToAngle(
        		Parameters.getDouble("Turn 1 Angle (degrees)", 0.0), 
        		Parameters.getDouble("Turn 1 Speed", 0.6)));*/
        //addSequential(new Wait(100));
        //addSequential(new AutonomousDriveStraight(
        		//Parameters.getInt("Leg 2 Distance (inches)", 24), 
        		//Parameters.getDouble("Leg 2 Speed", 0.6)));
    	/*
        addSequential(new Wait(50));
        addSequential(new AutonomousTurnToAngle(
        		Parameters.getDouble("Turn 2 Angle (degrees)", 60.0), 
        		Parameters.getDouble("Turn 2 Speed", 0.8),
        		Parameters.getDouble("Turn 2 Tolerance (degrees)", 5.0)));
        addSequential(new Wait(100));
        
        addSequential(new ToggleIntakePiston());
        addSequential(new Wait(100));
        addSequential(new ToggleIntakePiston());
        addSequential(new Wait(50));
        
        addSequential(new HighShot());*/
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
