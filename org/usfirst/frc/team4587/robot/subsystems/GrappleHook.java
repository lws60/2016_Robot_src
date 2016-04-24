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

public class GrappleHook extends Subsystem implements LogDataSource{
	private Solenoid grappleArm;
	private Solenoid grappleFire;
	private Solenoid winchEngage;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public GrappleHook()
	{
		grappleArm = new Solenoid(RobotMap.SOLENOID_GRAPPLE_ARM);
		grappleFire = new Solenoid(RobotMap.SOLENOID_GRAPPLE_FIRE);
		winchEngage = new Solenoid(RobotMap.SOLENOID_WINCH_ENGAGE);
	}
	
	public boolean getGrappleArm()
	{
		return grappleArm.get();
	}
	
	public boolean getGrappleFire()
	{
		return grappleFire.get();
	}
	
	public boolean getWinchEngage()
	{
		return winchEngage.get();
	}
	
	public void setGrappleArm(boolean x)
	{
		grappleArm.set(x);
	}

	public void setGrappleFire(boolean x)
	{
		grappleFire.set(x);
	}
    
	public void setWinchEngage(boolean x)
	{
		winchEngage.set(x);
	}
	
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue ( "Grapple Arm Value", grappleArm.get() );
    	logger.logBooleanValue ( "Grapple Fire Value", grappleFire.get() );
    	logger.logBooleanValue ( "Winch Engage Value", winchEngage.get() );
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

