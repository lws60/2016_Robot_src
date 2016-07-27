package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.Gyro;

/**
 *
 */
public class AutonomousDriveStraightAtAngle extends Command {
	int m_startLeft = 0;
	int m_startRight = 0;
	double m_distanceInches;
	double m_speed;
	double m_degrees;
	double m_degreesTolerance;
	double m_turnSpeed;
	

    public AutonomousDriveStraightAtAngle(double distanceInches, double speed, double degrees, double degreesTolerance) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_distanceInches = distanceInches;
    	m_speed = speed;
    	m_degrees = degrees;
    	m_turnSpeed = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startLeft = Robot.getDriveBase().getEncoderLeft();
    	m_startRight = Robot.getDriveBase().getEncoderRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Math.abs(Gyro.getYaw() - m_degrees) <= m_degreesTolerance)
    	{
    		Robot.getDriveBase().teleopDrive(m_speed, m_turnSpeed);
    	}
    	else if (Gyro.getYaw() > m_degrees)
    	{
    		if (m_turnSpeed > -0.1)
    		{
    			m_turnSpeed = -0.275;
    		}
    		else
    		{
    			m_turnSpeed -= 0.0025;
    		}
    		Robot.getDriveBase().teleopDrive(m_speed, m_turnSpeed);
    	}
    	else
    	{
    		if (m_turnSpeed < 0.1)
    		{
    			m_turnSpeed = 0.275;
    		}
    		else
    		{
    			m_turnSpeed += 0.0025;
    		}
    		Robot.getDriveBase().teleopDrive(m_speed, m_turnSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("distancetraveled", Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight));
    	SmartDashboard.putNumber("startLeft", m_startLeft);
    	SmartDashboard.putNumber("startRight", m_startRight);
    	if(m_distanceInches > 0)
    	{
    		return Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) >= m_distanceInches;
    	}
    	else
    	{
    		return Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) <= m_distanceInches;
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
