package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Gyro;

/**
 *
 */
public class AutonomousDriveArc extends Command {
	double m_startAngle;
	double m_desiredAngle;
	double m_expectedAngle;
	double m_degreesPerSecond;
	double m_distance;
	double m_driveSpeed;
	double m_turnSpeed;
	double m_tolerance;
	int m_count;
	int m_startLeft;
	int m_startRight;
	

    public AutonomousDriveArc(double angleDegrees, double degreesPerSecond, double distance, double driveSpeed, double turnSpeed, double tolerance) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_desiredAngle = angleDegrees;
    	m_degreesPerSecond = degreesPerSecond;
    	m_distance = distance;
    	m_driveSpeed = driveSpeed;
    	m_turnSpeed = turnSpeed;
    	m_tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startLeft = Robot.getDriveBase().getEncoderLeft();
    	m_startRight = Robot.getDriveBase().getEncoderRight();
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
    		Robot.getDriveBase().teleopDrive(m_driveSpeed, m_turnSpeed * direction);
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
	    		m_turnSpeed += 0.05;
	    	}
	    	else if (Gyro.getYaw() - m_expectedAngle > 0.1)
	    	{
	    		m_turnSpeed -= 0.05;
	    	}
	    	
	    	if (m_turnSpeed < 0)
        	{
        		m_turnSpeed = 0.0;
        	}
        	else if (m_turnSpeed > 1)
        	{
        		m_turnSpeed = 1.0;
        	}
	    	
	    	Robot.getDriveBase().teleopDrive(m_driveSpeed, m_turnSpeed * direction);
    	}
    	m_count += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (m_startAngle > m_desiredAngle && m_distance > 0)
    	{	
       		return Gyro.getYaw() <= m_desiredAngle + m_tolerance && Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) >= m_distance;
    	}
        else if (m_startAngle > m_desiredAngle && m_distance < 0)
        {
        	return Gyro.getYaw() <= m_desiredAngle + m_tolerance && Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) <= m_distance;
        }
    	
    	if (m_startAngle < m_desiredAngle && m_distance > 0)
    	{
       		return Gyro.getYaw() >= m_desiredAngle - m_tolerance && Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) >= m_distance;
    	}
        else
       	{
       		return Gyro.getYaw() >= m_desiredAngle - m_tolerance && Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) <= m_distance;
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
