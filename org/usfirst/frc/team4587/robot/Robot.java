
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
import org.usfirst.frc.team4587.robot.commands.AutonomousPosition2;
import org.usfirst.frc.team4587.robot.commands.CrossDefenseAndShoot;
import org.usfirst.frc.team4587.robot.commands.LowbarAndShoot;
import org.usfirst.frc.team4587.robot.commands.Wait;
import org.usfirst.frc.team4587.robot.subsystems.ArmPiston;
import org.usfirst.frc.team4587.robot.subsystems.Catapult;
import org.usfirst.frc.team4587.robot.subsystems.DriveBase;
import org.usfirst.frc.team4587.robot.subsystems.Flashlight;
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

	private static Flashlight m_flashlight;
	public static Flashlight getFlashlight()
	{
		return m_flashlight;
	}

	private static CameraThread m_cameraThread;
	public static CameraThread getCameraThread()
	{
		return m_cameraThread;
	}
	private static ValueLogger  logger;

    Command autonomousCommand;
    SendableChooser chooser;
    SendableChooser chooserExtensionsUp;
    
    private static boolean m_extensionsUp;
    public static boolean isExtensionsUp()
    {
    	return m_extensionsUp;
    }
    
    private DigitalInput m_positionSwitchZero;
    private DigitalInput m_positionSwitchOne;
    private DigitalInput m_positionSwitchTwo;
    private DigitalInput m_positionSwitchThree;
    private DigitalInput m_defenseSwitchZero;
    private DigitalInput m_defenseSwitchOne;
    private DigitalInput m_defenseSwitchTwo;
    private DigitalInput m_defenseSwitchThree;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    
    	m_robot = this;
        Bling.initialize();
    	Parameters.readValues();
        m_iHaveACamera  = Parameters.getBoolean("Camera is Installed",false);
        m_iAmARealRobot = Parameters.getBoolean("RoboRIO is Mounted in a Robot",false);

        m_defenseSwitchZero = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_0));
		m_defenseSwitchOne = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_1));
		m_defenseSwitchTwo = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_2));
		m_defenseSwitchThree = new DigitalInput(Gyro.getChannelFromPin(Gyro.PinType.DigitalIO, RobotMap.DEFENSE_SWITCH_3));
        
    	if ( m_iAmARealRobot ) {
			m_armPiston = new ArmPiston();
			m_catapult = new Catapult();
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
    	}
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
            logger.registerDataSource ( m_driveBase );
            logger.registerDataSource ( m_flashlight );
            logger.registerDataSource ( m_intake );
            logger.registerDataSource ( m_intakePiston );
            logger.registerDataSource ( m_lowGoalSolenoid );
            logger.registerDataSource ( m_oi );
        }
        
        if (m_iAmARealRobot){
        chooser = new SendableChooser();
        chooser.addDefault("Do Nothing Auto", new Wait(0));
        chooser.addObject("Drive Straight", new AutonomousDriveForTime());
        chooser.addObject("Drive Straight Distance arm/intake down", new AutonomousDriveUnderPortcullis());
        chooser.addObject("Lowbar drive and shoot", new AutonomousDriveAndShoot());
        chooser.addObject("Position 2", new CrossDefenseAndShoot(20));
        chooser.addObject("Position 3", new CrossDefenseAndShoot(0));
        chooser.addObject("Position 4", new CrossDefenseAndShoot(-20));
        chooser.addObject("Position 5", new CrossDefenseAndShoot(-45));
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        }
/*
        chooserExtensionsUp = new SendableChooser();
        chooserExtensionsUp.addDefault("Arm/Intake Up", true);
        chooserExtensionsUp.addObject("Arm/Intake Down", false);
        
        SmartDashboard.putData("Auto Extensions Up/Down", chooserExtensionsUp);*/
    }
    
    private int getFieldPosition()
    {
    	return 1 * (m_positionSwitchZero.get() ? 1:0)
    		 + 2 * (m_positionSwitchOne.get() ? 1:0)
    		 + 4 * (m_positionSwitchTwo.get() ? 1:0)
  			 + 8 * (m_positionSwitchThree.get() ? 1:0);
    }
    
    private int getFieldDefense()
    {
    	return 1 * (m_defenseSwitchZero.get() ? 1:0)
    		 + 2 * (m_defenseSwitchOne.get() ? 1:0)
    		 + 4 * (m_defenseSwitchTwo.get() ? 1:0)
  			 + 8 * (m_defenseSwitchThree.get() ? 1:0);
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
    	
    
    	//m_driveBase.getLeftController().setMaxLowerPerInterval(1.0);
    	//m_driveBase.getRightController().setMaxLowerPerInterval(1.0);
    	//m_extensionsUp = (boolean)chooser.getSelected();

    	if ( m_iAmARealRobot ) {
    		//autonomousCommand = (Command) chooser.getSelected();
    		int position = getFieldPosition();
    		int defense = getFieldDefense();
    		if (position == 1)
    		{
    			autonomousCommand = new LowbarAndShoot();
    		}
    		else if (position > 1 && position < 6)
    		{
    			//switch for defenses
    		}
    		autonomousCommand = new AutonomousPosition2(getFieldDefense());
    		System.out.println(autonomousCommand);
            //autonomousCommand.start();
            
    		Scheduler.getInstance().add(autonomousCommand);
    		
    		
    		
    	}
    }

    public void autonomousPeriodic() 
    {
    	SmartDashboard.putBoolean("Position Switch 0", m_defenseSwitchZero.get());
    	SmartDashboard.putBoolean("Position Switch 1", m_defenseSwitchOne.get());
    	SmartDashboard.putBoolean("Position Switch 2", m_defenseSwitchTwo.get());
    	SmartDashboard.putBoolean("Position Switch 3", m_defenseSwitchThree.get());
    	SmartDashboard.putNumber("Field Position", getFieldDefense());
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
    	if (m_iAmARealRobot){
    		if (m_intake.getIntakeSwitch() == false)
    		{
    			Bling.sendData((byte) 35);
    			m_intake.setBallIsLoaded(true);
    		}
    	}
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
    	logger.logBooleanValue( "IMU_Connected", Gyro.IMU_Connected() );
    	logger.logBooleanValue( "IMU_IsCalibrating", Gyro.IMU_IsCalibrating() );
    }
}
