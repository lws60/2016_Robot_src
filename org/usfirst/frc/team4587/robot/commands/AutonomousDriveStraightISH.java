package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDriveStraightISH extends Command {
	int m_startLeft = 0;
	int m_startRight = 0;
	double m_distanceInches;
	double m_speed;
	double m_turn;
	

    public AutonomousDriveStraightISH(double distanceInches, double speed, double turn) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.getDriveBase());
    	m_distanceInches = distanceInches;
    	m_speed = speed;
    	m_turn = turn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startLeft = Robot.getDriveBase().getEncoderLeft();
    	m_startRight = Robot.getDriveBase().getEncoderRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.getDriveBase().teleopDrive(m_speed, m_turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.getDriveBase().straightDistanceTraveled(m_startLeft, m_startRight) >= m_distanceInches;
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
