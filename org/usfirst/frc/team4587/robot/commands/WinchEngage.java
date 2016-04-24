package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchEngage extends Command {

	int count;
    public WinchEngage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getGrappleHook());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	if (Robot.getGrappleHook().getGrappleArm())
    	{
    		Robot.getGrappleHook().setWinchEngage(!Robot.getGrappleHook().getWinchEngage());
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count += 1;
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count >= 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
