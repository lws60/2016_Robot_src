package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Gyro;

/**
 *
 */
public class AutonomousTurnToAngle extends Command {
	double m_startAngle;
	double m_desiredAngle;
	double m_speed;
	double m_tolerance;
	

    public AutonomousTurnToAngle(double angleDegrees, double speed, double tolerance) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_desiredAngle = angleDegrees;
    	m_speed = speed;
    	m_tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startAngle = Gyro.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double direction;
    	if (Math.abs(Gyro.getYaw() - m_desiredAngle) < m_tolerance)
    	{
    		direction = 0;
    	}
    	else
    	if (m_startAngle < m_desiredAngle)
    	{
    		direction = 1;
    	}
    	else
    	{
    		direction = -1;
    	}
    	Robot.getDriveBase().teleopDrive(0.2, m_speed * direction);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (m_startAngle < m_desiredAngle)
    	{
    		return Gyro.getYaw() >= m_desiredAngle - m_tolerance;
    	}
    	else
    	{
    		return Gyro.getYaw() <= m_desiredAngle + m_tolerance;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.getDriveBase().teleopDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
