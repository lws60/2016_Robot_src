package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *			NOT NECESSARY ANYMORE - OUTDATED
 */
public class SpinUpShooter extends Command 
{

    public SpinUpShooter()
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.getIntakeAndShooter());
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	//Robot.getIntakeAndShooter().initializeShootingCalculator();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//Robot.getIntakeAndShooter().updateShootingMotorConstantSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
