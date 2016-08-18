
package org.usfirst.frc.team4587.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4587.robot.commands.AutonomousDriveAndShoot;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveForTime;
import org.usfirst.frc.team4587.robot.commands.AutonomousDriveUnderPortcullis;
import org.usfirst.frc.team4587.robot.commands.AutonomousIRI;
import org.usfirst.frc.team4587.robot.commands.AutonomousPosition2;
import org.usfirst.frc.team4587.robot.commands.AutonomousPosition34;
import org.usfirst.frc.team4587.robot.commands.AutonomousPosition5;
import org.usfirst.frc.team4587.robot.commands.AutonomousTest;
import org.usfirst.frc.team4587.robot.commands.AutonomousWorlds;
import org.usfirst.frc.team4587.robot.commands.CrossDefenseAndShoot;
import org.usfirst.frc.team4587.robot.commands.LowbarAndShoot;
import org.usfirst.frc.team4587.robot.commands.PortcullisTest;
import org.usfirst.frc.team4587.robot.commands.Wait;
import org.usfirst.frc.team4587.robot.subsystems.ArmPiston;
import org.usfirst.frc.team4587.robot.subsystems.Catapult;
import org.usfirst.frc.team4587.robot.subsystems.DriveBase;
import org.usfirst.frc.team4587.robot.subsystems.Flashlight;
import org.usfirst.frc.team4587.robot.subsystems.Flashlight2;
import org.usfirst.frc.team4587.robot.subsystems.GrappleHook;
import org.usfirst.frc.team4587.robot.subsystems.Intake;
import org.usfirst.frc.team4587.robot.subsystems.IntakePiston;
import org.usfirst.frc.team4587.robot.subsystems.LowGoalSolenoid;

import utility.Bling;
import utility.CameraThread;
import utility.Gyro;
import utility.LogDataSource;
import utility.Parameters;
import utility.ValueLogger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements LogDataSource {
	// Set this false when running on a standalone RIO, with no robot components attached.
	// Set it true when running on the real robot.
	private static boolean m_iAmARealRobot = true;
	public static boolean iAmARealRobot() { return m_iAmARealRobot; }

        private static boolean m_iHaveACamera = true;
        public static boolean iHaveACamera() { return m_iHaveACamera; }

	private static Robot m_robot;
	public static Robot getInstance()
	{
		return m_robot;
	}

	private static OI m_oi;
	public static OI getOI()
	{
		return m_oi;
	}

	private static Intake m_intake;
	public static Intake getIntake()
	{
		return m_intake;
	}
    
	private static DriveBase m_driveBase;
	public static DriveBase getDriveBase()
	{
		return m_driveBase;
	}

	private static IntakePiston m_intakePiston;
	public static IntakePiston getIntakePiston()
	{
		return m_intakePiston;
	}

	private static LowGoalSolenoid m_lowGoalSolenoid;
	public static LowGoalSolenoid getLowGoalSolenoid()
	{
		return m_lowGoalSolenoid;
	}

	private static ArmPiston m_armPiston;
	public static ArmPiston getArmPiston()
	{
		return m_armPiston;
	}

	private static Catapult m_catapult;
	public static Catapult getCatapult()
	{
		return m_catapult;
	}
	
	private static GrappleHook m_grappleHook;
	public static GrappleHook getGrappleHook()
	{
		return m_grappleHook;
	}

	private static Flashlight m_flashlight;
	public static Flashlight getFlashlight()
	{
		return m_flashlight;
	}
	
	private static Flashlight2 m_flashlight2;
	public static Flashlight2 getFlashlight2()
	{
		return m_flashlight2;
	}

	private static CameraThread m_cameraThread;
	public static CameraThread getCameraThread()
	{
		return m_cameraThread;
	}
	private static ValueLogger  logger;

    Command autonomousCommand;
    
    private static boolean m_extensionsUp;
    public static boolean isExtensionsUp()
    {
    	return m_extensionsUp;
    }
    
    private static DigitalInput m_positionSwitchZero;
    private static DigitalInput m_positionSwitchOne;
    private static DigitalInput m_positionSwitchTwo;
    private static DigitalInput m_positionSwitchThree;
    private static DigitalInput m_defenseSwitchZero;
    private static DigitalInput m_defenseSwitchOne;
    private static DigitalInput m_defenseSwitchTwo;
    private static DigitalInput m_defenseSwitchThree;
    
    private static DigitalInput m_backUpSwitchZero;
    private static DigitalInput m_backUpSwitchOne;
    private static DigitalInput m_backUpSwitchTwo;
    //private static DigitalInput m_backUpSwitchThree;
    private static DigitalInput m_twoBallSwitchZero;
    private static DigitalInput m_twoBallSwitchOne;
    private static DigitalInput m_twoBallSwitchTwo;
    //private static DigitalInput m_twoBallSwitchThree;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    
    	m_robot = this;
        Bling.initialize();
    	Parameters.readValues();
        m_iHaveACamera  = Parameters.getBoolean("Camera is Installed",true);
        m_iAmARealRobot = Parameters.getBoolean("RoboRIO is Mounted in a Robot",true);

        /*m_defenseSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_0));
		m_defenseSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_1));
		m_defenseSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_2));
		m_defenseSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_3));
        
		m_positionSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_0));
		m_positionSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_1));
		m_positionSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_2));
		m_positionSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_3));
		*/
    	if ( m_iAmARealRobot ) {
			m_armPiston = new ArmPiston();
			m_catapult = new Catapult();
			//m_grappleHook = new GrappleHook();
			m_driveBase = new DriveBase();
			m_flashlight = new Flashlight();
	    	m_intake = new Intake();
			m_intakePiston = new IntakePiston();
			m_lowGoalSolenoid = new LowGoalSolenoid();
			
			m_defenseSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_0));
			m_defenseSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_1));
			m_defenseSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_2));
			m_defenseSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_3));
			
			m_positionSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_0));
			m_positionSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_1));
			m_positionSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_2));
			m_positionSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_3));
			
			m_backUpSwitchZero = new DigitalInput(RobotMap.BACK_UP_SWITCH_0);
			m_backUpSwitchOne = new DigitalInput(RobotMap.BACK_UP_SWITCH_1);
			m_backUpSwitchTwo = new DigitalInput(RobotMap.BACK_UP_SWITCH_2);
			//m_backUpSwitchThree = new DigitalInput(RobotMap.BACK_UP_SWITCH_3);
			
			m_twoBallSwitchZero = new DigitalInput(RobotMap.TWO_BALL_SWITCH_0);
			m_twoBallSwitchOne = new DigitalInput(RobotMap.TWO_BALL_SWITCH_1);
			m_twoBallSwitchTwo = new DigitalInput(RobotMap.TWO_BALL_SWITCH_2);
			//m_twoBallSwitchThree = new DigitalInput(RobotMap.TWO_BALL_SWITCH_3);
			
			SmartDashboard.putNumber("defense number: ", getFieldDefense());
			SmartDashboard.putNumber("position number: ", getFieldPosition());
    	}
    	/*m_defenseSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_0));
		m_defenseSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_1));
		m_defenseSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_2));
		m_defenseSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_3));
		
		m_positionSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_0));
		m_positionSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_1));
		m_positionSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_2));
		m_positionSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.POSITION_SWITCH_3));
		
		m_backUpSwitchZero = new DigitalInput(RobotMap.BACK_UP_SWITCH_0);
		m_backUpSwitchOne = new DigitalInput(RobotMap.BACK_UP_SWITCH_1);
		m_backUpSwitchTwo = new DigitalInput(RobotMap.BACK_UP_SWITCH_2);
		//m_backUpSwitchThree = new DigitalInput(RobotMap.BACK_UP_SWITCH_3);
		
		m_twoBallSwitchZero = new DigitalInput(RobotMap.TWO_BALL_SWITCH_0);
		m_twoBallSwitchOne = new DigitalInput(RobotMap.TWO_BALL_SWITCH_1);
		m_twoBallSwitchTwo = new DigitalInput(RobotMap.TWO_BALL_SWITCH_2);
		//m_twoBallSwitchThree = new DigitalInput(RobotMap.TWO_BALL_SWITCH_3);*/
		
		SmartDashboard.putNumber("defense number: ", getFieldDefense());
		SmartDashboard.putNumber("position number: ", getFieldPosition());
    	m_oi = new OI();
        // Always create OI last, since it creates Command objects that depend on
        // the subsystems already having been created.
        //m_oi = new OI();

        if ( m_iHaveACamera ) {
            CameraServer.getInstance().setQuality(50);
            CameraThread.initializeOnce();
            m_cameraThread = new CameraThread();
        }

        if ( m_iAmARealRobot ) {
            logger = new ValueLogger("/home/lvuser/dump",10);
            logger.registerDataSource ( this );
            logger.registerDataSource ( m_armPiston );
            logger.registerDataSource ( m_catapult );
            //logger.registerDataSource ( m_grappleHook );
            logger.registerDataSource ( m_driveBase );
            logger.registerDataSource ( m_flashlight );
            logger.registerDataSource ( m_intake );
            logger.registerDataSource ( m_intakePiston );
            logger.registerDataSource ( m_lowGoalSolenoid );
            logger.registerDataSource ( m_oi );
        }
        
        
    }
    
    public static int getFieldPosition()
    {
    	return 15 - (1 * (m_positionSwitchZero.get() ? 1:0)
    		 + 2 * (m_positionSwitchOne.get() ? 1:0)
    		 + 4 * (m_positionSwitchTwo.get() ? 1:0)
  			 + 8 * (m_positionSwitchThree.get() ? 1:0));
    }
    
    public static int getFieldDefense()
    {
    	return 15 - (1 * (m_defenseSwitchZero.get() ? 1:0)
    		 + 2 * (m_defenseSwitchOne.get() ? 1:0)
    		 + 4 * (m_defenseSwitchTwo.get() ? 1:0)
  			 + 8 * (m_defenseSwitchThree.get() ? 1:0));
    }

    public static int getFieldBackUp()
    {
    	return 7 - (1 * (m_backUpSwitchZero.get() ? 1:0)
    		 + 2 * (m_backUpSwitchOne.get() ? 1:0)
    		 + 4 * (m_backUpSwitchTwo.get() ? 1:0));
    }
    
    public static int getFieldTwoBall()
    {
    	return 7 - (1 * (m_twoBallSwitchZero.get() ? 1:0)
    		 + 2 * (m_twoBallSwitchOne.get() ? 1:0)
    		 + 4 * (m_twoBallSwitchTwo.get() ? 1:0));
    }
    
    public void disabledInit()
    {
    	initializeNewPhase(ValueLogger.DISABLED_PHASE);
    	Bling.sendData((byte)32);
    }

    public void disabledPeriodic() {
            Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	initializeNewPhase(ValueLogger.AUTONOMOUS_PHASE);
    	Bling.sendData((byte)33);
    	
    	if (m_iAmARealRobot ) {
    		//autonomousCommand = (Command) chooser.getSelected();
    		m_driveBase.getLeftController().setMaxLowerPerInterval(1.0);
    		m_driveBase.getRightController().setMaxLowerPerInterval(1.0);
    		
    		int position = getFieldPosition();
    		int defense = getFieldDefense();
    		/*if (position == 1)
    		{
    			autonomousCommand = new LowbarAndShoot();
    		}
    		else if (position > 1 && position < 6)
    		{
    			//switch for defenses
    			switch (position)
    			{
    			case 2:
    				autonomousCommand = new AutonomousPosition2(defense);
    				//System.out.println("position 2 defense: " + defense);
    				break;
    			case 3:
    				autonomousCommand = new AutonomousPosition34(defense);
    				//System.out.println("position 3 defense: " + defense);
    				break;
    			case 4:
    				autonomousCommand = new AutonomousPosition34(defense);
    				//System.out.println("position 4 defense: " + defense);
    				break;
    			case 5:
    				autonomousCommand = new AutonomousPosition5(defense);
    				//System.out.println("position 5 defense: " + defense);
    				break;
    			}
    		}*/
    		autonomousCommand = new AutonomousIRI();
    		System.out.println(autonomousCommand);
            //autonomousCommand.start();
            
    		Scheduler.getInstance().add(autonomousCommand);
    		
    		
    		
    	}
    }

    public void autonomousPeriodic() 
    {
    	SmartDashboard.putBoolean("Position Switch 0", m_positionSwitchZero.get());
    	SmartDashboard.putBoolean("Position Switch 1", m_positionSwitchOne.get());
    	SmartDashboard.putBoolean("Position Switch 2", m_positionSwitchTwo.get());
    	SmartDashboard.putBoolean("Position Switch 3", m_positionSwitchThree.get());
    	SmartDashboard.putNumber("Field Position", getFieldPosition());
    	SmartDashboard.putBoolean("Defense Switch 0", m_defenseSwitchZero.get());
    	SmartDashboard.putBoolean("Defense Switch 1", m_defenseSwitchOne.get());
    	SmartDashboard.putBoolean("Defense Switch 2", m_defenseSwitchTwo.get());
    	SmartDashboard.putBoolean("Defense Switch 3", m_defenseSwitchThree.get());
    	SmartDashboard.putNumber("Field Defense", getFieldDefense());
    	
    	SmartDashboard.putBoolean("Back Up Switch 0", m_backUpSwitchZero.get());
    	SmartDashboard.putBoolean("Back Up Switch 1", m_backUpSwitchOne.get());
    	SmartDashboard.putBoolean("Back Up Switch 2", m_backUpSwitchTwo.get());
    	//SmartDashboard.putBoolean("Back Up Switch 3", m_backUpSwitchThree.get());
    	SmartDashboard.putNumber("Field Back Up", getFieldBackUp());
    	SmartDashboard.putBoolean("Two Ball Switch 0", m_twoBallSwitchZero.get());
    	SmartDashboard.putBoolean("Two Ball Switch 1", m_twoBallSwitchOne.get());
    	SmartDashboard.putBoolean("Two Ball Switch 2", m_twoBallSwitchTwo.get());
    	//SmartDashboard.putBoolean("Two Ball Switch 3", m_twoBallSwitchThree.get());
    	SmartDashboard.putNumber("Field Two Ball", getFieldTwoBall());
    	
    	long start = System.nanoTime();
        Scheduler.getInstance().run();
        if ( logger != null ) logger.logValues(start);
    }

    public void teleopInit() {
    	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        //m_driveBase.getLeftController().setMaxLowerPerInterval(0.4);
    	//m_driveBase.getRightController().setMaxLowerPerInterval(0.4);

    	Bling.sendData((byte)34);
        initializeNewPhase(ValueLogger.TELEOP_PHASE);
    }
  
    public void teleopPeriodic() {
    	long start = System.nanoTime();
    
    	Scheduler.getInstance().run();
    	if ( logger != null ) logger.logValues(start);
    	/*if (m_iAmARealRobot){
    		if (m_intake.getIntakeSwitch() == false)
    		{
    			Bling.sendData((byte) 35);
    			m_intake.setBallIsLoaded(true);
    		}
    	}*/
    	
    	SmartDashboard.putBoolean("Position Switch 0", m_positionSwitchZero.get());
    	SmartDashboard.putBoolean("Position Switch 1", m_positionSwitchOne.get());
    	SmartDashboard.putBoolean("Position Switch 2", m_positionSwitchTwo.get());
    	SmartDashboard.putBoolean("Position Switch 3", m_positionSwitchThree.get());
    	SmartDashboard.putNumber("Field Position", getFieldPosition());
    	SmartDashboard.putBoolean("Defense Switch 0", m_defenseSwitchZero.get());
    	SmartDashboard.putBoolean("Defense Switch 1", m_defenseSwitchOne.get());
    	SmartDashboard.putBoolean("Defense Switch 2", m_defenseSwitchTwo.get());
    	SmartDashboard.putBoolean("Defense Switch 3", m_defenseSwitchThree.get());
    	SmartDashboard.putNumber("Field Defense", getFieldDefense());
    	
    	SmartDashboard.putBoolean("Back Up Switch 0", m_backUpSwitchZero.get());
    	SmartDashboard.putBoolean("Back Up Switch 1", m_backUpSwitchOne.get());
    	SmartDashboard.putBoolean("Back Up Switch 2", m_backUpSwitchTwo.get());
    	//SmartDashboard.putBoolean("Back Up Switch 3", m_backUpSwitchThree.get());
    	SmartDashboard.putNumber("Field Back Up", getFieldBackUp());
    	SmartDashboard.putBoolean("Two Ball Switch 0", m_twoBallSwitchZero.get());
    	SmartDashboard.putBoolean("Two Ball Switch 1", m_twoBallSwitchOne.get());
    	SmartDashboard.putBoolean("Two Ball Switch 2", m_twoBallSwitchTwo.get());
    	//SmartDashboard.putBoolean("Two Ball Switch 3", m_twoBallSwitchThree.get());
    	SmartDashboard.putNumber("Field Two Ball", getFieldTwoBall());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }

    private void initializeNewPhase ( String whichPhase )
    {
        if ( autonomousCommand != null ) {
            autonomousCommand.cancel();
            autonomousCommand = null;
        }
    	Parameters.readValues();
    	if ( m_iAmARealRobot ) {
            Robot.getDriveBase().initialize();
            Robot.getIntake().initialize();
    	}
        if ( m_iHaveACamera ) {
            CameraThread.initializeForPhase();
        }
        Gyro.reset();
        if ( logger != null ) logger.initializePhase(whichPhase);
    }

    public void gatherValues ( ValueLogger logger )
    {
    	logger.logDoubleValue ( "Gyro Yaw", Gyro.getYaw() );
    	logger.logDoubleValue ( "Gyro Pitch", Gyro.getPitch() );
    	logger.logDoubleValue ( "Gyro Roll", Gyro.getRoll() );	
    	logger.logBooleanValue( "IMU_Connected", Gyro.IMU_Connected() );
    	logger.logBooleanValue( "IMU_IsCalibrating", Gyro.IMU_IsCalibrating() );
    }
}
