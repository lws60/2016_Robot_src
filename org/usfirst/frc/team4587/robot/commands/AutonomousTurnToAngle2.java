package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Gyro;

/**
 *
 */
public class AutonomousTurnToAngle2 extends Command {
	double m_startAngle;
	double m_desiredAngle;
	double m_expectedAngle;
	double m_degreesPerSecond;
	double m_speed;
	double m_tolerance;
	int m_count;
	

    public AutonomousTurnToAngle2(double angleDegrees, double degreesPerSecond, double speed, double tolerance) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_desiredAngle = angleDegrees;
    	m_degreesPerSecond = degreesPerSecond;
    	m_speed = speed;
    	m_tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startAngle = Gyro.getYaw();
    	m_count = 0;
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
    	if (m_count == 0)
    	{
    		Robot.getDriveBase().teleopDrive(0.0, m_speed * direction);
    	}
    	else
    	{
	    	
	    	m_expectedAngle = m_startAngle + (m_count * m_degreesPerSecond / 50);
	    	
	    	if (Math.abs(m_expectedAngle - m_startAngle) > Math.abs(m_desiredAngle - m_startAngle))
	    	{
	    		m_expectedAngle = m_desiredAngle;
	    	}
	    	
	    	if (Gyro.getYaw() - m_expectedAngle < -0.1)
	    	{
	    		m_speed += 0.05;
	    	}
	    	else if (Gyro.getYaw() - m_expectedAngle > 0.1)
	    	{
	    		m_speed -= 0.05;
	    	}
	    	
	    	if (m_speed < 0)
        	{
        		m_speed = 0.0;
        	}
        	else if (m_speed > 1)
        	{
        		m_speed = 1.0;
        	}
	    	
	    	Robot.getDriveBase().teleopDrive(0.0, m_speed * direction);
    	}
    	m_count += 1;
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
