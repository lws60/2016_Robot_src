package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.CameraThread;
import utility.Gyro;

/**
 *
 */
public class Aim extends Command {

	private CameraThread m_cameraThread;
	private boolean m_onTarget;
	private boolean m_moving;
	private double m_desiredYaw;
	private double m_tolerance;
	private double m_speed;
	
	private int count;
	
    public Aim(double speed, double tolerance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_speed = speed;
    	m_tolerance = tolerance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_cameraThread = new CameraThread();
    	m_cameraThread.start();
    	m_onTarget = false;
    	m_moving = false;
    	m_desiredYaw = Gyro.getYaw();
    	
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (m_moving)
    	{
    		double direction;
        	if (Math.abs(Gyro.getYaw() - m_desiredYaw) < m_tolerance)
        	{
        		direction = 0;
        		m_moving = false;
        		m_cameraThread = new CameraThread();
        		m_cameraThread.start();
        		Robot.getDriveBase().teleopDrive(0.0, 0.0);
        	}
        	else
        	{
	        	if (Gyro.getYaw() < m_desiredYaw)
	        	{
	        		direction = 1;
	        	}
	        	else
	        	{
	        		direction = -1;
	        	}
	        	Robot.getDriveBase().teleopDrive(0.2, m_speed * direction);
        	}
    	}
    	else
    	{
	    	if (m_cameraThread.isAlive() == false)
	    	{
	    		if (m_cameraThread.weHaveAShot())
	    		{
	    			m_onTarget = true;
	    		}
	    		else
	    		{
	    			double pixels = m_cameraThread.shotAngleError();
	    			m_desiredYaw = Gyro.getYaw() - pixels * 54 / 640;
	    			m_moving = true;
	    		}
	    	}
    	}
    	SmartDashboard.putNumber("Desired Yaw", m_desiredYaw);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
