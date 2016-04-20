package org.usfirst.frc.team4587.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.LogDataSource;
import utility.Parameters;
import utility.RampedSpeedController;
import utility.RampedSpeedController.ControllerType;
import utility.ValueLogger;

import org.usfirst.frc.team4587.robot.RobotMap;
import org.usfirst.frc.team4587.robot.commands.IntakeAndShooterIdle;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/*
 * 		NOT NECESSARY ANYMORE - OUTDATED
 */
public class IntakeAndShooter extends Subsystem
{
    /*private RampedSpeedController m_intakeMotor, m_shootingMotor, m_elevatorMotor;
    private Encoder m_elevatorTach, m_shootingTach;
    private DigitalInput m_shooterExit, m_intakeLimit;

    private boolean ball_is_loaded = false;
    private boolean ball_has_exited = false;
    private boolean armed = false;
    
    private int m_ballShotCount;
    
    private ConstantSpeedOutputCalculator m_ElevatorCalculator, m_shootingCalculator;

    public boolean hasBallLoaded()
    {
        if ( ball_is_loaded ) 
        {
        	return true;
        }
        if ( m_intakeLimit.get() == false ) {
            ball_is_loaded = true;
            ball_has_exited = false;
        }
        return ball_is_loaded;
    }
    
    public boolean ballHasExited()
    {
    	if(ball_has_exited)
    	{
    		return true;
    	}
    	if(m_ballShotCount < 0)
    	{
    		if(m_shooterExit.get() == false)
    		{
    			m_ballShotCount = 0;
    		}
    		return false;
    	}
    	m_ballShotCount += 1;
    	if (m_ballShotCount >= 10)
    	{
    		ball_has_exited = true;
    		ball_is_loaded = false;
    		armed = false;
    	}
    	return ball_has_exited;
    }

    public IntakeAndShooter()
    {    	
    	m_ElevatorCalculator = new ConstantSpeedOutputCalculator();
    	m_shootingCalculator = new ConstantSpeedOutputCalculator();
    	
        m_intakeMotor = new RampedSpeedController(ControllerType.Talon,RobotMap.MOTOR_INTAKE);
        m_elevatorMotor = new RampedSpeedController(ControllerType.Talon, RobotMap.MOTOR_ELEVATOR);
        m_shootingMotor = new RampedSpeedController(ControllerType.Talon,RobotMap.MOTOR_SHOOTING);
        
        m_elevatorTach = new Encoder(RobotMap.ENCODER_INTAKE_A, RobotMap.ENCODER_INTAKE_B);
        m_shootingTach = new Encoder(RobotMap.ENCODER_SHOOTER_A, RobotMap.ENCODER_SHOOTER_B);

        m_intakeLimit = new DigitalInput(RobotMap.SWITCH_INTAKE_LIMIT);
        m_shooterExit = new DigitalInput(RobotMap.SWITCH_SHOOTER_EXIT);
        
        m_ballShotCount = -1;
    }
    
    public void initialize()
    {
    	ball_is_loaded = false;
    	ball_has_exited = false;

        m_intakeMotor.setMaxRaisePerInterval ( Parameters.getDouble("Intake Motor Max Raise",0.2) );
        m_intakeMotor.setMaxLowerPerInterval ( Parameters.getDouble("Intake Motor Max Lower",0.4) );
        m_intakeMotor.setPositiveDeadband    ( Parameters.getDouble("Intake Motor Positive Deadband",0.0) );
        m_intakeMotor.setNegativeDeadband    ( Parameters.getDouble("Intake Motor Negative Deadband",0.0) );
        m_intakeMotor.setDesiredSetting(0.0);

        m_shootingMotor.setMaxRaisePerInterval ( Parameters.getDouble("Shooting Motor Max Raise",0.2) );
        m_shootingMotor.setMaxLowerPerInterval ( Parameters.getDouble("Shooting Motor Max Lower",0.4) );
        m_shootingMotor.setPositiveDeadband    ( Parameters.getDouble("Shooting Motor Positive Deadband",0.0) );
        m_shootingMotor.setNegativeDeadband    ( Parameters.getDouble("Shooting Motor Negative Deadband",0.0) );
        m_shootingMotor.setDesiredSetting(0.0);

        m_elevatorMotor.setMaxRaisePerInterval ( Parameters.getDouble("Elevator Motor Max Raise",0.2) );
        m_elevatorMotor.setMaxLowerPerInterval ( Parameters.getDouble("Elevator Motor Max Lower",0.4) );
        m_elevatorMotor.setPositiveDeadband    ( Parameters.getDouble("Elevator Motor Positive Deadband",0.0) );
        m_elevatorMotor.setNegativeDeadband    ( Parameters.getDouble("Elevator Motor Negative Deadband",0.0) );
        m_elevatorMotor.setDesiredSetting(0.0);
    }
    
    public double getElevatorTachRate()
    {
    	return m_elevatorTach.getRate() * -1;
    }
    
    public double getShootingTachRate()
    {
    	return m_shootingTach.getRate();
    }

    */protected void initDefaultCommand() 
    {
        setDefaultCommand(new IntakeAndShooterIdle());
    }/*

    public void setIntakeMotorTarget ( double x )
    {
        m_intakeMotor.setDesiredSetting(x);
    }
    
    public void setElevatorMotorTarget( double x )
    {
    	m_elevatorMotor.setDesiredSetting(x);
    }
    
    public void setShootingMotorTarget ( double x )
    {
    	m_shootingMotor.setDesiredSetting(x * -1);
    }
    
    public void updateIntakeMotor()
    {
    	m_intakeMotor.updateMotorLevel();
    }
    
    public void updateElevatorMotor()
    {
    	m_elevatorMotor.updateMotorLevel();
    }
    
    public void updateShootingMotor() 
	{
    	m_shootingMotor.updateMotorLevel();
	}
    
    public boolean getIntakeLimitSwitch()
    {
    	return m_intakeLimit.get();
    }
    
    public boolean getShooterExitSwitch()
    {
    	return m_shooterExit.get();
    }
    
    public boolean shooterIsReady()
    {
    	return armed && m_shootingCalculator.initialRise() == false;
    }
    
    public void disarm()
    {
    	armed = false;
    }
    
    public int getElevatorTachEncoder()
    {
    	return m_elevatorTach.get();
    }
    
    public int getShootingTachEncoder()
    {
    	return m_shootingTach.get();
    }
    
    public double getIntakeMotorDesiredLevel()
    {
    	return m_intakeMotor.getDesiredSetting();
    }
    
    public double getIntakeMotorLastLevel()
    {
    	return m_intakeMotor.getLastSetting();
    }
    
    public double getIntakeMotorLevel()
    {
    	return m_intakeMotor.getMotorLevel();
    }
    
    public double getShootingMotorDesiredLevel()
    {
    	return m_shootingMotor.getDesiredSetting();
    }
    
    public double getShootingMotorLastLevel()
    {
    	return m_shootingMotor.getLastSetting();
    }
    
    public double getShootingMotorLevel()
    {
    	return m_shootingMotor.getMotorLevel();
    }
    
    public void initializeElevatorCalculator()
    {
    	m_ballShotCount = -1;
    	int maxDelta = Parameters.getInt("Elevator Motor Max Delta", 56);
    	int minDelta = Parameters.getInt("Elevator Motor Min Delta", 52); 
    	double outputScalar = Parameters.getDouble("Elevator Motor Output Scalar", 120.0);
    	double nominalOutput = Parameters.getDouble("Elevator Motor Nominal Output", 0.85);
    	m_ElevatorCalculator.initialize(maxDelta, minDelta, getElevatorTachEncoder(), outputScalar, nominalOutput);
    }
    
    public void initializeShootingCalculator()
    {
    	int maxDelta = Parameters.getInt("Shooting Motor Max Delta", 38); 
    	int minDelta = Parameters.getInt("Shooting Motor Min Delta", 34);
    	double outputScalar = Parameters.getDouble("Shooting Motor Output Scalar" , 120.0);
    	double nominalOutput = Parameters.getDouble("Shooting Motor Nominal Output", 0.85);
    	m_shootingCalculator.initialize(maxDelta, minDelta, getShootingTachEncoder(), outputScalar, nominalOutput);
    	armed = true;
    }
    
    public void updateElevatorMotorConstantSpeed()
    {
    	setIntakeMotorTarget(m_ElevatorCalculator.getOutput(getElevatorTachEncoder()));
    	m_intakeMotor.updateMotorLevel();
    }
    
    public void updateShootingMotorConstantSpeed()
    {
    	setShootingMotorTarget(m_shootingCalculator.getOutput(getShootingTachEncoder()));
    	m_shootingMotor.updateMotorLevel();
    }
    
    private class ConstantSpeedOutputCalculator
    {
    	private int m_lastReading, m_lastDelta, m_maxDelta, m_minDelta;
    	private double m_outputScalar, m_nominalOutput, m_lastOutput;
    	private boolean m_initialRise;
    	
    	public void initialize(int maxDelta, int minDelta, int currentReading, double outputScalar, double nominalOutput)
    	{
    		m_lastReading = currentReading;
    		m_lastDelta = 0;
    		m_maxDelta = maxDelta;
    		m_minDelta = minDelta;
    		m_outputScalar = outputScalar;
    		m_nominalOutput = nominalOutput;
    		m_initialRise = true;
    		m_lastOutput = 1.0;
    	}
    	
    	public double getOutput(int currentReading)
    	{
	    	int delta = currentReading - m_lastReading;
	    	if(m_initialRise)
	    	{
	    		if(delta >= m_minDelta)
	    		{
	    			m_initialRise = false;
	    			m_lastDelta = (m_maxDelta + m_minDelta) / 2;
	    			m_lastOutput = m_nominalOutput;
	    		}
	    		else
	    		{
	    			m_lastOutput = 1.0;
	    		}
	    	}
	    	else
	    	{
		    	if(delta < m_minDelta && m_lastDelta < m_minDelta)
		    	{
		    		m_lastOutput = Math.min(m_lastOutput + (((m_minDelta - delta) / m_outputScalar)), 1.0);
		    	}
		    	else if(delta > m_maxDelta && m_lastDelta > m_maxDelta)
		    	{
		    		m_lastOutput = Math.max(m_lastOutput - (((delta - m_maxDelta) / m_outputScalar)), 0.0);
		    	}
	    	}
	    	m_lastDelta = delta;
	    	m_lastReading = currentReading;
	    	return m_lastOutput;
	    }
    	
    	public boolean initialRise()
    	{
    		return m_initialRise;
    	}
    }
    
    public void gatherValues( ValueLogger logger)
    {
    	logger.logBooleanValue("Intake Limit Switch", getIntakeLimitSwitch());
    	m_intakeMotor.gatherValues(logger);
    }
    
    public String getValues()
    {
    	StringBuffer sb = new StringBuffer();
    	Parameters.displayBoolean("Intake Limit Switch: ", getIntakeLimitSwitch(), sb);
    	Parameters.displayBoolean("Shooter Exit Switch: ", getShooterExitSwitch(), sb);
    	Parameters.displayBoolean("Is Shooter Ready", shooterIsReady(), sb);
    	Parameters.displayInt("Elevators Tachometer Encoder: ", getElevatorTachEncoder(), sb);
    	Parameters.displayInt("Shooting Tachometer Encoder: ", getShootingTachEncoder(), sb);
    	Parameters.displayDouble("Elevator Tachometer Encoder Rate: ", getElevatorTachRate(), sb);
    	Parameters.displayDouble("Shooting Tachometer Encoder Rate: ", getShootingTachRate(), sb);
        sb.append(",").append(m_intakeMotor.getValues("Intake Motor"))
          .append(",").append(m_shootingMotor.getValues("Shooting Motor"))
          .append(",").append(m_elevatorMotor.getValues("Elevator Motor"))
        ;
    	return sb.toString().substring(1);
    }
    public String getHeaders()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("Intake Limit Switch");
    	sb.append(",Shooter Exit Switch");
    	sb.append(",Is Shooter Ready");
    	sb.append(",Elevator Tachometer Encoder");
    	sb.append(",Shooting Tachometer Encoder");
    	sb.append(",Elevator Tachometer Encoder Rate");
    	sb.append(",Shooting Tachometer Encoder Rate");
        sb.append(m_intakeMotor.getHeaders("Intake Motor"));
        sb.append(m_shootingMotor.getHeaders("Shooting Motor"));
        sb.append(m_elevatorMotor.getHeaders("Elevator Motor"));
    	return sb.toString();
    } */
}
