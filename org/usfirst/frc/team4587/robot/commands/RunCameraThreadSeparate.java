package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.CameraThread;

/**
 *
 */
public class RunCameraThreadSeparate extends Command {

	private CameraThread m_cameraThread;
	private int m_intervals;
    public RunCameraThreadSeparate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_intervals = 0;
    	m_cameraThread = new CameraThread();
    	m_cameraThread.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_intervals ++;
    	if (m_cameraThread.isAlive() == false)
    	{
    		SmartDashboard.putNumber("Camera Intervals", m_intervals);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return !m_cameraThread.isAlive();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
