package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *			NOT NECESSARY ANYMORE - OUTDATED
 */
public class ToggleElevatorPiston extends Command {

    public ToggleElevatorPiston() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.getElevatorPiston());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.getElevatorPiston().setElevatorPiston(!Robot.getElevatorPiston().getElevatorPiston());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
