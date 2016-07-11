package org.usfirst.frc.team4587.robot;

import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	
	/*			PWM Motors			*/
	public static final int MOTOR_RIGHT_DRIVETRAIN = 0;
	public static final int	MOTOR_LEFT_DRIVETRAIN = 1;
	public static final int MOTOR_INTAKE = 2;
    public static final int MOTOR_INTAKE_TWO = 3;
	
	/*			Solenoids			*/
	public static final int SOLENOID_INTAKE = 1;
	public static final int SOLENOID_LOWGOAL = 2;
	public static final int SOLENOID_ARM = 3;
	public static final int SOLENOID_CATAPULT_1 = 4;
	public static final int SOLENOID_CATAPULT_2 = 5;
	public static final int SOLENOID_CATAPULT_3 = 6;
	public static final int SOLENOID_GRAPPLE_ARM = 7;
	public static final int SOLENOID_GRAPPLE_FIRE = 0;
    
	public static final int SOLENOID_WINCH_ENGAGE = 2;
	/*			DIO Sensors			*/
	public static final int SWITCH_SHOOTER_EXIT = 0;	// D E L E T E   M E
    //public static final int SWITCH_INTAKE_LIMIT = 1;
    public static final int ENCODER_SHOOTER_A = 2;		// D E L E T E   M E
    public static final int ENCODER_SHOOTER_B = 3;		// D E L E T E   M E
    public static final int ENCODER_INTAKE_A = 4;		// D E L E T E   M E
    public static final int ENCODER_INTAKE_B = 5;		// D E L E T E   M E
    public static final int ENCODER_RIGHT_DRIVE_A = 8;
    public static final int ENCODER_RIGHT_DRIVE_B = 9;
    public static final int ENCODER_LEFT_DRIVE_A = 6;
    public static final int ENCODER_LEFT_DRIVE_B = 7;

    public static final int POSITION_SWITCH_0 = 0;
    public static final int POSITION_SWITCH_1 = 1;
    public static final int POSITION_SWITCH_2 = 2;
    public static final int POSITION_SWITCH_3 = 3;
    public static final int DEFENSE_SWITCH_0 = 4;
    public static final int DEFENSE_SWITCH_1 = 5;
    public static final int DEFENSE_SWITCH_2 = 6;
    public static final int DEFENSE_SWITCH_3 = 7;
    
    public static final int BACK_UP_SWITCH_0 = 0;
    public static final int BACK_UP_SWITCH_1 = 1;
    public static final int BACK_UP_SWITCH_2 = 2;
    public static final int TWO_BALL_SWITCH_0 = 3;
    public static final int TWO_BALL_SWITCH_1 = 4;
    public static final int TWO_BALL_SWITCH_2 = 5;
    
    public static final int BACK_UP_SWITCH_3 = 6;
    public static final int TWO_BALL_SWITCH_3 = 7;
    
    public static final int LOW_BAR = 0;
    public static final int ROUGH_TERRAIN = 1;
    public static final int ROCK_WALL = 2;
    public static final int MOAT = 3;
    public static final int CHEVAL = 4;
    public static final int PORTCULLIS = 5;
    public static final int RAMPARTS = 6;
    
    
    /*          Relays              */
    public static final int             FLASHLIGHT_CHANNEL = 0;
    public static final Relay.Direction FLASHLIGHT_DIRECTION = Relay.Direction.kForward;
}
