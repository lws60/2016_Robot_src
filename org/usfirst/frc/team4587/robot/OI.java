package org.usfirst.frc.team4587.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import utility.JoyButton;
import utility.LogDataSource;
import utility.Parameters;
import utility.ValueLogger;

import org.usfirst.frc.team4587.robot.commands.Aim;
import org.usfirst.frc.team4587.robot.commands.Aim2;
import org.usfirst.frc.team4587.robot.commands.AutomaticAim;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveArc;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveArc2;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveStraightAtAngle;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveStraightDistance;
import org.usfirst.frc.team4587.robot.commands.AutonomousTurnToAngle;
import org.usfirst.frc.team4587.robot.commands.AutonomousTurnToAngle2;
import org.usfirst.frc.team4587.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team4587.robot.commands.GrappleArm;
import org.usfirst.frc.team4587.robot.commands.GrappleFire;
import org.usfirst.frc.team4587.robot.commands.HighShot;
import org.usfirst.frc.team4587.robot.commands.LowShot;
import org.usfirst.frc.team4587.robot.commands.LowerAndHoldArm;
import org.usfirst.frc.team4587.robot.commands.PulseLowGoalSolenoid;
import org.usfirst.frc.team4587.robot.commands.RunCameraThread;
import org.usfirst.frc.team4587.robot.commands.RunCameraThreadSeparate;
import org.usfirst.frc.team4587.robot.commands.StartIntakeMotors;
import org.usfirst.frc.team4587.robot.commands.StopIntakeMotors;
import org.usfirst.frc.team4587.robot.commands.TeleopAimBot;
import org.usfirst.frc.team4587.robot.commands.ToggleArmPiston;
import org.usfirst.frc.team4587.robot.commands.ToggleFlashlight;
import org.usfirst.frc.team4587.robot.commands.ToggleIntakePiston;
import org.usfirst.frc.team4587.robot.commands.WinchEngage;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements LogDataSource {
	Joystick  stick1;
	Button	  buttonA1, buttonB1, buttonX1, buttonY1, leftBumper1, rightBumper1;
	JoyButton leftTrigger1, rightTrigger1;
	Joystick  stick2;
	Button	  buttonA2, buttonB2, buttonX2, buttonY2, leftBumper2, rightBumper2;
	JoyButton leftTrigger2, rightTrigger2;
	
    public OI()
    {
    	stick1			= new Joystick(1);
    	buttonA1		= new JoystickButton(stick1, 1);
    	buttonB1		= new JoystickButton(stick1, 2);
    	buttonX1		= new JoystickButton(stick1, 3);
    	buttonY1		= new JoystickButton(stick1, 4);
    	leftBumper1 	= new JoystickButton(stick1, 5);
    	leftTrigger1	= new JoyButton(stick1, JoyButton.JoyDir.DOWN, 2);
    	rightBumper1	= new JoystickButton(stick1, 6);
    	rightTrigger1	= new JoyButton(stick1, JoyButton.JoyDir.DOWN, 3);
    	
    	stick2			= new Joystick(2);
    	buttonA2		= new JoystickButton(stick2, 1);
    	buttonB2		= new JoystickButton(stick2, 2);
    	buttonX2		= new JoystickButton(stick2, 3);
    	buttonY2		= new JoystickButton(stick2, 4);
    	leftBumper2 	= new JoystickButton(stick2, 5);
    	leftTrigger2	= new JoyButton(stick2, JoyButton.JoyDir.DOWN, 2);
    	rightBumper2	= new JoystickButton(stick2, 6);
    	rightTrigger2	= new JoyButton(stick2, JoyButton.JoyDir.DOWN, 3);

    	//  D R I V E R   C O N T R O L L E R

    	if ( Robot.iAmARealRobot()) {
    		buttonB1.whenPressed(new DriveWithJoysticks());
    		buttonA1.whenPressed(new RunCameraThread());
    		//buttonX1.whenPressed(new Aim2(0.3, 10, 0.1, 1, 0.9));
    		buttonX1.whenPressed(new TeleopAimBot());
    		//buttonX1.whenPressed(new AutonomousDriveArc2(-50, -500, -1, -0.4, -0.8));
    		//buttonY1.whenPressed(new AutonomousDriveArc2(50, 500, 1, 0.4, 0.8));
    		//buttonY1.whenPressed(new AutonomousDriveStraightAtAngle(400, 0.7, 0, 0.1));
	    	leftBumper1.whenPressed(new ToggleArmPiston());
	    	rightBumper1.whenPressed(new LowShot());
    		rightTrigger1.whenPressed(new HighShot());
    	}
    	else 
    	{
    		buttonA1.whenPressed(new RunCameraThread());
    		buttonB1.whenPressed(new RunCameraThreadSeparate());

    		//buttonX1.whenPressed(new Aim(0.6, 2.5));
    	}

    	//  I N T A K E   C O N T R O L L E R

    	if ( Robot.iAmARealRobot()) {
	    	//buttonA2.whenPressed(new AutoIntakeBall());
	    	buttonA2.whenPressed(new ToggleFlashlight());
	    	buttonB2.whenPressed(new StopIntakeMotors());
	    	buttonX2.whenPressed(new StartIntakeMotors(Parameters.getDouble("Intake Motor Eject Speed", -1.0)));
	    	buttonY2.whenPressed(new StartIntakeMotors(Parameters.getDouble("Intake Motor Input Speed", 1.0)));
	    	rightBumper2.whenPressed(new ToggleIntakePiston());
	    	leftBumper2.whenPressed(new PulseLowGoalSolenoid(1000));
	    	//leftTrigger2.whenPressed(new GrappleArm());
	    	//rightTrigger2.whenPressed(new GrappleFire());
    	}
    }
    
    public double getTurn()
    {
    	return stick1.getRawAxis(4);
    }
    
    public double getDrive()
    {
    	return stick1.getRawAxis(1) * -1;
    }
    
    public void rumble( float value )
    {
    	stick1.setRumble(Joystick.RumbleType.kRightRumble, value);
    }
    
    public void gatherValues ( ValueLogger logger )
    {
    	logger.logDoubleValue("Driving Joystick Value", getDrive());
    	logger.logDoubleValue("Turning Joystick Value", getTurn());
    }
}
