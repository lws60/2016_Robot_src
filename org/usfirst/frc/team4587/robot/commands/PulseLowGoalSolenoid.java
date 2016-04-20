package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import utility.Bling;

/**
 *
 */
public class PulseLowGoalSolenoid extends Command {

	private int count;
	private int maxCount;
	
    public PulseLowGoalSolenoid(int extensionTimeMSEC) 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires( Robot.getLowGoalSolenoid() );
    	maxCount = extensionTimeMSEC / 20;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {

    	Bling.sendData((byte)36);
    	
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(count == 0)
    	{
    		Robot.getLowGoalSolenoid().setLowGoalSolenoid(!Robot.getLowGoalSolenoid().getLowGoalSolenoid());
    	}
    	else if(count == maxCount)
    	{
    		Robot.getLowGoalSolenoid().setLowGoalSolenoid(!Robot.getLowGoalSolenoid().getLowGoalSolenoid());
    	}
    	++count;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return count > maxCount;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.getIntake().setBallIsLoaded(false);

    	Bling.sendData((byte)34);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
