package org.usfirst.frc.team4587.robot.subsystems;

import org.usfirst.frc.team4587.robot.RobotMap;
import org.usfirst.frc.team4587.robot.commands.IntakeIdle;

import utility.LogDataSource;
import utility.Parameters;
import utility.RampedSpeedController;
import utility.ValueLogger;
import utility.RampedSpeedController.ControllerType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem implements LogDataSource {
    private RampedSpeedController m_intakeMotor;
    private RampedSpeedController m_intakeMotorTwo;
    public double getIntakeMotorTarget()
    {
    	return m_intakeMotor.getDesiredSetting() * -1;
    }
    public void setIntakeMotorTarget(double x)
    {
    	m_intakeMotor.set(-1 * x);
    	m_intakeMotorTwo.set(-1 * x);
    }
    public double getIntakeMotorActual()
    {
    	return m_intakeMotor.getMotorLevel() * -1;
    }
    public void updateIntakeMotor()
    {
    	m_intakeMotor.updateMotorLevel();
    	m_intakeMotorTwo.updateMotorLevel();
    }

    private DigitalInput m_intakeSwitch;
    /*public boolean getIntakeSwitch()
    {
    	return m_intakeSwitch.get();
    }*/

    private boolean ball_is_loaded = false;
    /*public boolean isBallLoaded()
    {
    	if ( ball_is_loaded == false ) {
    		if ( getIntakeSwitch() == false ) {
    			ball_is_loaded = true;
    		}
    	}
    	return ball_is_loaded;
    }*/
    public void setBallIsLoaded ( boolean x )
    {
    	ball_is_loaded = x;
    }

    public Intake()
    {    	
        m_intakeMotor = new RampedSpeedController(ControllerType.Talon,RobotMap.MOTOR_INTAKE);
        m_intakeMotor.setName("Intake");
        
        m_intakeMotorTwo = new RampedSpeedController(ControllerType.Talon,RobotMap.MOTOR_INTAKE_TWO);
        m_intakeMotorTwo.setName("Intake Two");

        //m_intakeSwitch = new DigitalInput(RobotMap.SWITCH_INTAKE_LIMIT);
    }
    
    public void initialize()
    {
    	ball_is_loaded = false;

        m_intakeMotor.setMaxRaisePerInterval ( Parameters.getDouble("Intake Motor Max Raise",0.2) );
        m_intakeMotor.setMaxLowerPerInterval ( Parameters.getDouble("Intake Motor Max Lower",0.4) );
        m_intakeMotor.setPositiveDeadband    ( Parameters.getDouble("Intake Motor Positive Deadband",0.0) );
        m_intakeMotor.setNegativeDeadband    ( Parameters.getDouble("Intake Motor Negative Deadband",0.0) );
        m_intakeMotor.setDesiredSetting(0.0);
        
        m_intakeMotorTwo.setMaxRaisePerInterval ( Parameters.getDouble("Intake Motor Max Raise",0.2) );
        m_intakeMotorTwo.setMaxLowerPerInterval ( Parameters.getDouble("Intake Motor Max Lower",0.4) );
        m_intakeMotorTwo.setPositiveDeadband    ( Parameters.getDouble("Intake Motor Positive Deadband",0.0) );
        m_intakeMotorTwo.setNegativeDeadband    ( Parameters.getDouble("Intake Motor Negative Deadband",0.0) );
        m_intakeMotorTwo.setDesiredSetting(0.0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new IntakeIdle());
    }
    
    public void gatherValues( ValueLogger logger)
    {
    	//logger.logBooleanValue("Intake Switch", getIntakeSwitch());
    	m_intakeMotor.gatherValues(logger);
    	m_intakeMotorTwo.gatherValues(logger);
    }

}