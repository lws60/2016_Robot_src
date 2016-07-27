package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.CameraThread;
import utility.Gyro;

/**
 *
 */
public class Aim2 extends Command {

	private CameraThread m_cameraThread;
	private boolean m_onTarget;
	private boolean m_moving;
	private double m_desiredYaw;
	private double m_lastYaw;
	private double m_intervalTolerance;
	private double m_tolerance;
	private double m_speed;
	private double m_degreesPerSecond;
	private double m_percentDegreesToSlow;
	
	private static boolean m_finishedAiming;
	public static boolean finishedAiming()
	{
		return m_finishedAiming;
	}
	
	private int count;
	
	private Command m_wait;
	
    public Aim2(double speed, double degreesPerSecond, double intervalTolerance, double tolerance, double percentDegreesToSlow) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_speed = speed;
    	m_degreesPerSecond = degreesPerSecond;
    	m_intervalTolerance = intervalTolerance;
    	m_tolerance = tolerance;
    	m_percentDegreesToSlow = percentDegreesToSlow;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_cameraThread = new CameraThread();
    	m_cameraThread.start();
    	m_onTarget = false;
    	m_moving = false;
    	m_desiredYaw = Gyro.getYaw();
    	
    	m_finishedAiming = false;
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
	        	
	        	if (((Gyro.getYaw() - m_lastYaw) * direction) - (m_degreesPerSecond / 50) > m_intervalTolerance)
	        	{
	        		m_speed -= 0.05;
	        	}
	        	else if (((Gyro.getYaw() - m_lastYaw) * direction) - (m_degreesPerSecond / 50) < (m_intervalTolerance * -1))
	        	{
	        		m_speed += 0.05;
	        	}
	        	
	        	if (Math.abs((Gyro.getYaw() - m_lastYaw) + Gyro.getYaw() / Math.abs(m_desiredYaw)) >= m_percentDegreesToSlow)
	        	{
	        		m_speed -= 0.007;
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
	        	m_lastYaw = Gyro.getYaw();
        	}
    	}
    	else
    	{
	    	if (m_cameraThread.isAlive() == false)
	    	{
	    		if (m_cameraThread.weHaveAShot())
	    		{
	    			if (Gyro.getYaw() >= CameraThread.desiredLeftYaw() && Gyro.getYaw() <= CameraThread.desiredRightYaw())
	    			{
	    				m_onTarget = true;
	    				m_finishedAiming = true;
	    			}
	    			else
	    			{
	    				m_desiredYaw = CameraThread.desiredCenterYaw();
		    			m_moving = true;
	    			}
	    		}
	    		else
	    		{
	    			double pixels = m_cameraThread.shotAngleError();
	    			//m_desiredYaw = CameraThread.yawWhenPicture() - pixels * 54 / 640;
	    			m_desiredYaw = CameraThread.yawWhenPicture() - pixels * 0.075; //try with 61 / 640
	    			if (Math.abs(Gyro.getYaw() - m_desiredYaw) < m_tolerance)
	    			{
	    				m_onTarget = true;
	    				m_finishedAiming = true;
	    			}
	    			else
	    			{
	    				m_moving = true;
	    			}
	    		}
	    	}
    	}
    	SmartDashboard.putNumber("Desired Yaw", m_desiredYaw);
    	
    	System.out.println("speed: " + m_speed);
    	System.out.println("desired yaw: " + m_desiredYaw);
    	System.out.println("is moving: " + m_moving);
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
