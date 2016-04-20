package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.Gyro;

/**
 *
 */
public class AutomaticAim extends Command {

	private static double m_speed;
	private static double m_expectedAngle;
	private static int count;
	
    public AutomaticAim(double expectedAngle, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setInterruptible(true);
    	requires(Robot.getDriveBase());
    	m_speed = speed;
    	m_expectedAngle = expectedAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count += 1;
    	SmartDashboard.putNumber("A Aim time", count);
    	Robot.getCameraThread().run();
    	if (Robot.getCameraThread().weHaveAShot())
    	{
    		Robot.getDriveBase().teleopDrive(0.0, 0.0);
    	}
    	else if (Robot.getCameraThread().foundTarget())
    	{
    		if(Robot.getCameraThread().sideRatio() > 1)
    		{
    			Robot.getDriveBase().teleopDrive(0.0, m_speed * -1);
    		}
    		else
    		{
    			Robot.getDriveBase().teleopDrive(0.0, m_speed);
    		}
    	}
    	else
    	{
    		if (Gyro.getYaw() < m_expectedAngle)
        	{
    			Robot.getDriveBase().teleopDrive(0.0, m_speed);
        	}
        	else
        	{
        		Robot.getDriveBase().teleopDrive(0.0, m_speed * -1);
        	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return count >= 200 || Robot.getCameraThread().weHaveAShot();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
