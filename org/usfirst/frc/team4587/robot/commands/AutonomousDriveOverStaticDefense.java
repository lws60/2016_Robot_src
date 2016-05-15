package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Gyro;
import utility.Parameters;

/**
 *
 */
public class AutonomousDriveOverStaticDefense extends Command {
	private double m_speed;
	private double m_pitch;
	private double m_roll;
	private double m_tolerance;
	private int m_toleranceTime;
	private int m_count;

    public AutonomousDriveOverStaticDefense(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_speed = speed;
    	m_tolerance = Parameters.getDouble("Bounce Tolerance", 2.5);
    	m_toleranceTime = Parameters.getInt("Obstacle Clear Time", 10);
    	m_count = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.getDriveBase().teleopDrive(m_speed, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_pitch = Gyro.getPitch();
    	m_roll = Gyro.getRoll();
    	if (Math.abs(m_pitch) <= m_tolerance && Math.abs(m_roll) <= m_tolerance)
    	{
    		m_count += 1;
    	}
    	else
    	{
    		m_count = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_count >= m_toleranceTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.getDriveBase().teleopDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
