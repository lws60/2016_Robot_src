package org.usfirst.frc.team4587.robot.subsystems;

import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import utility.LogDataSource;
import utility.Parameters;
import utility.ValueLogger;

/**
 *
 */
public class IntakePiston extends Subsystem implements LogDataSource {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid intakePiston;

	public IntakePiston()
	{
		intakePiston = new Solenoid(RobotMap.SOLENOID_INTAKE);
	}
	
	public boolean getIntakePiston()
	{
		return intakePiston.get();
	}
	
	public void setIntakePiston(boolean x)
	{
		intakePiston.set(x);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue ( "Intake Piston Value", intakePiston.get() );
    }
	
    // 2016-03-13: Methods after here should eventually be deleted...

	public String getValues()
	{
		StringBuffer sb = new StringBuffer();
		Parameters.displayBoolean("Intake Piston Value", intakePiston.get(), sb);
		return sb.toString();
	}
	
	public String getHeaders()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Intake Piston Value");
		return sb.toString();
	}
}

