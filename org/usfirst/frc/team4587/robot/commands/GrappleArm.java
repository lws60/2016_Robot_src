package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrappleArm extends Command {
	int count;
	public boolean isInterruptible()
	{
		return false;
	}
	
    public GrappleArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getGrappleHook());
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	if (DriverStation.getInstance().getMatchTime() <= 30)
    	{
    		Robot.getGrappleHook().setGrappleArm(!Robot.getGrappleHook().getGrappleArm());
    	}
    	else
    	{
    		count = 1000;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 15;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
