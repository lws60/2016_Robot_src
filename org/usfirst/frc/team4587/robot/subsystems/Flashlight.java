package org.usfirst.frc.team4587.robot.subsystems;

import org.usfirst.frc.team4587.robot.RobotMap;

import utility.LogDataSource;
import utility.ValueLogger;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flashlight extends Subsystem implements LogDataSource {
    private Relay m_flashlightRelay;
    
    public void setFlashlightState ( boolean trueForOn_falseForOff )
    {
    	if ( trueForOn_falseForOff == true )
    	{
    		m_flashlightRelay.set ( Relay.Value.kOn );
    	}
    	else
    	{
    		m_flashlightRelay.set ( Relay.Value.kOff );
    	}
    }
    public boolean getFlashlightState ()
    {
    	return m_flashlightRelay.get() == Relay.Value.kOn;
    }
    
    public Flashlight()
    {
    	m_flashlightRelay = new Relay ( RobotMap.FLASHLIGHT_CHANNEL, RobotMap.FLASHLIGHT_DIRECTION );
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue("Flashlight State",getFlashlightState());
    }
}

