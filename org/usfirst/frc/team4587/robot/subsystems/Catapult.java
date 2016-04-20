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
public class Catapult extends Subsystem implements LogDataSource {
	private Solenoid catapultOne;
	private Solenoid catapultTwo;
	private Solenoid catapultThree;

	public Catapult()
	{
		catapultOne = new Solenoid(RobotMap.SOLENOID_CATAPULT_1);
		catapultTwo = new Solenoid(RobotMap.SOLENOID_CATAPULT_2);
		catapultThree = new Solenoid(RobotMap.SOLENOID_CATAPULT_3);
	}
	
	public boolean getCatapultOne()
	{
		return catapultOne.get();
	}
	
	public boolean getCatapultTwo()
	{
		return catapultTwo.get();
	}
	
	public boolean getCatapultThree()
	{
		return catapultThree.get();
	}
	
	public void setCatapultOne(boolean x)
	{
		catapultOne.set(x);
	}

	public void setCatapultTwo(boolean x)
	{
		catapultTwo.set(x);
	}

	public void setCatapultThree(boolean x)
	{
		catapultThree.set(x);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logBooleanValue ( "Catapult One Value", catapultOne.get() );
    	logger.logBooleanValue ( "Catauplt Two Value", catapultTwo.get() );
    	logger.logBooleanValue ( "Catauplt Three Value", catapultThree.get() );
    }
	
    // 2016-03-13: Methods after here should eventually be deleted...

	public String getValues()
	{
		StringBuffer sb = new StringBuffer();
		Parameters.displayBoolean("Catapult One Value", catapultOne.get(), sb);
		Parameters.displayBoolean("Catapult Two Value", catapultTwo.get(), sb);
		Parameters.displayBoolean("Catapult Three Value", catapultThree.get(), sb);
		return sb.toString();
	}
	
	public String getHeaders()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Catapult One Value");
		sb.append(",Catapult Two Value");
		sb.append(",Catapult Three Value");
		return sb.toString();
	}
}

