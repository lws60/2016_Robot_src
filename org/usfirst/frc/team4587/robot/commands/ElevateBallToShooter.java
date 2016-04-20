package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 		NOT NECESSARY ANYMORE - OUTDATED
 */
public class ElevateBallToShooter extends Command 
{	
	private boolean shooter_is_ready;
	
    public ElevateBallToShooter() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.getIntakeAndShooter());
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	//shooter_is_ready = Robot.getIntakeAndShooter().shooterIsReady();
    	//if(shooter_is_ready)
    	//{
    	//	Robot.getIntakeAndShooter().initializeElevatorCalculator();
    	//}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(shooter_is_ready)
    	{
	    	//Robot.getIntakeAndShooter().updateElevatorMotorConstantSpeed();
	    	//Robot.getIntakeAndShooter().updateShootingMotorConstantSpeed();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        //return (shooter_is_ready == false) || Robot.getIntakeAndShooter().ballHasExited();
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
