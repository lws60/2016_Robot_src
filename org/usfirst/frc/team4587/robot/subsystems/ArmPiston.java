package org.usfirst.frc.team4587.robot.subsystems;

import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import utility.LogDataSource;
import utility.Parameters;
import utility.ValueLogger;

/**
 *
 */
public class ArmPiston extends Subsystem implements LogDataSource {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Solenoid armPiston;

	public ArmPiston()
	{
		armPiston = new Solenoid(RobotMap.SOLENOID_ARM);
	}
	
	public boolean getArmPiston()
	{
		return armPiston.get();
	}
	
	public void setArmPiston(boolean x)
	{
		armPiston.set(x);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue ( "Arm Piston Value", armPiston.get() );
    }

    // 2016-03-13: Methods after here should eventually be deleted...
	
	public String getValues()
	{
		StringBuffer sb = new StringBuffer();
		Parameters.displayBoolean("Arm Piston Value", armPiston.get(), sb);
		return sb.toString();
	}
	
	public String getHeaders()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Arm Piston Value");
		return sb.toString();
	}
}

