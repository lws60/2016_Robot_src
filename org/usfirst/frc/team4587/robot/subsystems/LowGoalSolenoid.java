package org.usfirst.frc.team4587.robot.subsystems;

import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import utility.LogDataSource;
import utility.ValueLogger;

/**
 *
 */
public class LowGoalSolenoid extends Subsystem implements LogDataSource {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid m_lowGoalSolenoid;

	public LowGoalSolenoid()
	{
		m_lowGoalSolenoid = new Solenoid(RobotMap.SOLENOID_LOWGOAL);
	}
	
	public boolean getLowGoalSolenoid()
	{
		return m_lowGoalSolenoid.get();
	}
	
	public void setLowGoalSolenoid(boolean x)
	{
		m_lowGoalSolenoid.set(x);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue ( "Low Goal Solenoid Value", m_lowGoalSolenoid.get() );
    }
}

