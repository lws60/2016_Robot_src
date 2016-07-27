package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDriveArc2 extends Command {
	int m_startLeft;
	int m_startRight;
	int m_lastLeft;
	int m_lastRight;
	int m_currentLeft;
	int m_currentRight;
	int m_leftCurrentVelocity;
	int m_rightCurrentVelocity;
	int m_leftDesiredVelocity;
	int m_rightDesiredVelocity;
	int m_leftDesiredVelocityChange;
	int m_rightDesiredVelocityChange;
	
	double m_encoderToMotor; //magic number
	
	double m_leftSpeed;
	double m_rightSpeed;
	double m_totalDistance;
	
	int m_count;

    public AutonomousDriveArc2(double totalDistance, int leftDesiredVelocity, int rightDesiredVelocity, double leftSpeed, double rightSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_totalDistance = totalDistance;
    	m_leftDesiredVelocity = leftDesiredVelocity;
    	m_rightDesiredVelocity = rightDesiredVelocity;
    	m_leftSpeed = leftSpeed;
    	m_rightSpeed = rightSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startLeft = Robot.getDriveBase().getEncoderLeft();
    	m_startRight = Robot.getDriveBase().getEncoderRight();
    	m_lastLeft = Robot.getDriveBase().getEncoderLeft();
    	m_lastRight = Robot.getDriveBase().getEncoderRight();
    	m_count = 0;
    	m_encoderToMotor = 0; //magic number
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_lastLeft = m_currentLeft;
    	m_lastRight = m_currentRight;
    	m_currentLeft = Robot.getDriveBase().getEncoderLeft();
    	m_currentRight = Robot.getDriveBase().getEncoderRight();
    	m_leftCurrentVelocity = m_currentLeft - m_lastLeft;
    	m_rightCurrentVelocity = m_currentRight - m_lastRight;	
    	m_leftDesiredVelocityChange = m_leftDesiredVelocity - m_leftCurrentVelocity;
       	m_rightDesiredVelocityChange = m_rightDesiredVelocity - m_rightCurrentVelocity;
    	if (m_count != 0)
    	{
    		if (m_leftCurrentVelocity < m_leftDesiredVelocity)
    		{
    			//increase m_leftSpeed
    			m_leftSpeed += m_leftDesiredVelocityChange * m_encoderToMotor;
    		}
    		else if (m_leftCurrentVelocity > m_leftDesiredVelocity)
    		{
    			//decrease m_leftSpeed
    			m_leftSpeed -= m_leftDesiredVelocityChange * m_encoderToMotor;
    		}
    		
    		if (m_rightCurrentVelocity < m_rightDesiredVelocity)
    		{
    			//increase m_rightSpeed
    			m_rightSpeed += m_rightDesiredVelocityChange * m_encoderToMotor;
    		}
    		else if (m_rightCurrentVelocity > m_rightDesiredVelocity)
    		{
    			//decrease m_rightSpeed
    			m_rightSpeed -= m_rightDesiredVelocityChange * m_encoderToMotor;
    		}
    	}
    	Robot.getDriveBase().getLeftController().setDesiredSetting(m_leftSpeed);
		Robot.getDriveBase().getRightController().setDesiredSetting(m_rightSpeed);
    	Robot.getDriveBase().getLeftController().updateMotorLevel();
    	Robot.getDriveBase().getRightController().updateMotorLevel();
    	m_count += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(m_totalDistance > 0)
    	{
    		return Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) >= m_totalDistance;
    	}
    	else
    	{
    		return Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) <= m_totalDistance;
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
