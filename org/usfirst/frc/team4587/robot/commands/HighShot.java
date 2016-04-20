package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Bling;

/**
 *
 */
public class HighShot extends Command {

	int counter;
	boolean canShoot;
	
    public HighShot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getCatapult());
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Bling.sendData((byte)36);
    	
    	canShoot = true;
    	if(Robot.getArmPiston().getArmPiston() == false)
    	{
    		canShoot = false;
    	}
    	if(Robot.getIntakePiston().getIntakePiston() == false)
    	{
    		canShoot = false; 
    	}
    	if(canShoot)
	    {
	    	Robot.getCatapult().setCatapultOne(true);
	    	Robot.getCatapult().setCatapultTwo(true);
	    	Robot.getCatapult().setCatapultThree(true);
	    	counter = 0;
	    	Robot.getIntake().setBallIsLoaded(false);
	    }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	counter += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(canShoot == false)
        {
        	return true;
        }
    	if (counter >= 15)
        {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(canShoot)
    	{
	    	Robot.getCatapult().setCatapultOne(false);
	    	Robot.getCatapult().setCatapultTwo(false);
	    	Robot.getCatapult().setCatapultThree(false);
    	}
    	Robot.getIntake().setBallIsLoaded(false);

    	Bling.sendData((byte)34);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
