package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import utility.Parameters;

/**
 *
 */
public class AutonomousDriveUnderPortcullis extends CommandGroup {
    
    public  AutonomousDriveUnderPortcullis() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	//if (Robot.isExtensionsUp())
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
    	//}
    	addSequential(new AutonomousDriveStraightDistance(
        		Parameters.getInt("Leg 1 Distance (inches)", 252), 
        		Parameters.getDouble("Leg 1 Speed", 0.5)));
    	/*addSequential(new ToggleIntakePiston());
        addSequential(new AutonomousTurnToAngle(
        		Parameters.getDouble("Turn 1 Angle (degrees)", 45.0), 
        		Parameters.getDouble("Turn 1 Speed", 0.6),
        		Parameters.getDouble("Turn 1 Tolerance (degrees)", 5.0)));*/
//=======
    	/*addSequential(new Wait(100));
    	addSequential(new AutonomousDriveStraight(
        		Parameters.getInt("Leg 1 Distance (inches)", 100), 
        		Parameters.getDouble("Leg 1 Speed", 0.7)));*/
        //addSequential(new Wait(100));
        /*addSequential(new AutonomousTurnToAngle(
        		Parameters.getDouble("Turn 1 Angle (degrees)", 0.0), 
        		Parameters.getDouble("Turn 1 Speed", 0.6)));
>>>>>>> eb5f5f34782b099289539a89384ebb0225304c58
        //addSequential(new Wait(100));
        //addSequential(new AutonomousDriveStraight(
        		//Parameters.getInt("Leg 2 Distance (inches)", 24), 
        		//Parameters.getDouble("Leg 2 Speed", 0.6)));
        //addSequential(new Wait(100));
        /*addSequential(new AutonomousTurnToAngle(
        		Parameters.getDouble("Turn 2 Angle (degrees)", 45.0), 
        		Parameters.getDouble("Turn 2 Speed", 0.6),
<<<<<<< HEAD
        		Parameters.getDouble("Turn 2 Tolerance (degrees)", 5.0)));*/
        /*addSequential(new ToggleIntakePiston());
        addSequential(new Wait(50));
        addSequential(new HighShot());
=======*/
        		//Parameters.getDouble("Turn 2 Tolerance (degrees)", 5.0);
       // addSequential(new Wait(100));
        //addSequential(new HighShot());
//>>>>>>> eb5f5f34782b099289539a89384ebb0225304c58
        // these will run in order.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
//<<<<<<< HEAD
}
//=======

//>>>>>>> eb5f5f34782b099289539a89384ebb0225304c58
