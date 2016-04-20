package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *			NOT NECESSARY ANYMORE - OUTDATED
 */
public class Wait extends Command {

	int m_count;
	int m_limit;
    public Wait(int limit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_limit = limit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_count += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (m_count >= m_limit)
        {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
