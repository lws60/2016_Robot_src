package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;
import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.Parameters;

/**
 *
 */
public class AutonomousWorlds extends CommandGroup {
	private int defense;
	private int position;
	private String defenseName;
    
    public  AutonomousWorlds() {
    	// rough terrain addSequential(new AutonomousDriveStraightDistance(135, 0.6));
    	// rock wall addSequential(new AutonomousDriveStraightDistance(210, 0.9));
    	
    	defense = Robot.getFieldDefense();
    	position = Robot.getFieldPosition();
    	//defense = RobotMap.CHEVAL;
    	//position = 3;
    	defenseName = "secret passage";
    	
    	switch (defense)
    	{
    	case RobotMap.CHEVAL:
	    		//cheval
	        addSequential(new AutonomousDriveStraightDistance(27, 0.5));
			addSequential(new ArmDown());
			addSequential(new Wait(75));
			addSequential(new AutonomousDriveStraightDistance(95, 0.7));
			addSequential(new Wait(25));
    		defenseName = "cheval";
			break;
    	case RobotMap.LOW_BAR:
    		//low bar
    		//addSequential(new LowbarAndShoot());
    		addSequential(new ArmDown());
    		addSequential(new Wait(20));
    		addSequential(new ToggleIntakePiston());
    		addParallel(new StartIntakeMotors(1.0));
    		addSequential(new Wait(75));
    		addParallel(new StartIntakeMotors(0.0));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.6));
    		addSequential(new AutonomousTurnToAngle(-12, 0.7, 2.5));
    		addSequential(new AutonomousDriveStraightDistance(12, 0.6));
    		addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
    		addSequential(new AutonomousDriveStraightDistance(95, 0.6));
    		addSequential(new AutonomousTurnToAngle(25, 0.7, 2.5));
    		addSequential(new ArmDown());
	    	addSequential(new Wait(35));
			addSequential(new Aim(0.7, 1));
			addSequential(new Wait(5));
			addSequential(new Aim(0.7, 1));
			addSequential(new Wait(35));
			addSequential(new HighShot());
			/*addSequential(new Wait(15));
    		addSequential(new AutonomousDriveStraightDistance(-7, -0.6));
    		addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
    		addSequential(new AutonomousDriveStraightDistance(-40, -0.5));
    		addSequential(new AutonomousDriveStraightDistance(-30, -0.2));
    		addParallel(new StartIntakeMotors(1.0));
    		addSequential(new Wait(75));
    		addParallel(new StartIntakeMotors(0.0));*/
    		
    		defenseName = "low bar";
    		break;
    	case RobotMap.ROCK_WALL:
    		addSequential(new AutonomousDriveStraightDistance(195, Parameters.getDouble("Rock Wall Speed", 0.9)));
    		/*addSequential(new AutonomousDriveStraightDistance(56, 0.7));
    		addSequential(new AutonomousDriveOverStaticDefense(0.7));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));*/
    		addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		defenseName = "rock wall";
    		break;
    	case RobotMap.ROUGH_TERRAIN:
    		//addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Rough Terrain Distance", 135), Parameters.getDouble("Rough Terrain Speed", 0.6)));
    		addSequential(new AutonomousDriveStraightDistance(56, 0.4));
    		addSequential(new AutonomousDriveOverStaticDefense(0.4));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));
    		addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		defenseName = "rough terrain";
    		break;
    	case RobotMap.MOAT:
    		//addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Moat Distance", 210), Parameters.getDouble("Moat Speed", 0.9)));
    		addSequential(new AutonomousDriveStraightDistance(56, 0.4));
    		addSequential(new AutonomousDriveOverStaticDefense(0.4));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));
    		addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		defenseName = "moat";
    		break;
    	case RobotMap.PORTCULLIS:
    		addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Portcullis Distance", 160), Parameters.getDouble("Portcullis Speed", 0.7)));
    		addSequential(new Wait(25));
    		defenseName = "portcullis";
    		break;
    	}
    	
		switch (position)
		{
		case 2:
	    	addSequential(new AutonomousTurnToAngle(90, 0.7, 2.5));
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousDriveStraightDistance(28, 0.7));
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousTurnToAngle(20, 0.8, 2.5));
	    	addSequential(new ArmDown());
	    	addSequential(new Wait(35));
	    	addSequential(new Aim(0.7, 1));
	    	addSequential(new ToggleIntakePiston());
	    	addSequential(new Wait(25));
	    	addSequential(new Aim(0.7, 1));
	    	addSequential(new HighShot());
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousDriveStraightDistance(-135, -0.7));
	    	break;
		case 3:
			addSequential(new AutonomousTurnToAngle(Parameters.getInt("Position 3 Turn Angle", 7), Parameters.getDouble("Position 3 Turn Speed", 0.65), Parameters.getDouble("Position 3 Turn Tolerance", 2.5)));
	    	addSequential(new ArmDown());
	    	addSequential(new Wait(35));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(25));
			addSequential(new Aim(0.7, 1));
			addSequential(new Wait(35));
			addSequential(new HighShot());
			addSequential(new Wait(25));
			addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
			//addParallel(new StartIntakeMotors(1.0));
			//addSequential(new AutonomousDriveStraightDistance(-162, -0.7));
			addSequential(new AutonomousDriveStraightDistance(-135, -0.7));
			/*addSequential(new Wait(20));
			addSequential(new ArmUp());
			addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
			addSequential(new Wait(20));
			addSequential(new ToggleIntakePiston());
			addParallel(new StartIntakeMotors(0.0));
			addSequential(new AutonomousDriveStraightDistance(180, Parameters.getDouble("Rock Wall Speed", 0.9)));
			addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		
    		addSequential(new AutonomousTurnToAngle(Parameters.getInt("Position 3 Turn Angle", 7), Parameters.getDouble("Position 3 Turn Speed", 0.65), Parameters.getDouble("Position 3 Turn Tolerance", 2.5)));
	    	addSequential(new ArmDown());
	    	addSequential(new Wait(25));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(15));
			addSequential(new Aim(0.7, 1));
			addSequential(new Wait(15));
			addSequential(new HighShot());*/
			break;
		case 4:
	    	addSequential(new ArmDown());
	    	addSequential(new Wait(50));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(75));
			addSequential(new HighShot());
			//addSequential(new Wait(25));
			//addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
			//addSequential(new AutonomousDriveStraightDistance(-140, -0.7));
			break;
		case 5:
			/*addSequential(new AutonomousDriveStraight(Parameters.getInt("Position 5 Back Up Distance", -20), Parameters.getDouble("Position 5 Back Up Speed", -0.5)));
			addSequential(new Wait(25));
	    	addSequential(new AutonomousTurnToAngle(Parameters.getInt("Position 5 Turn Angle", -15), Parameters.getDouble("Position 5 Turn Speed", 0.6), Parameters.getDouble("Position 5 Turn Tolerance", 2.5)));
	    	addSequential(new Aim(0.7, 1));
	    	addSequential(new ToggleIntakePiston());
	    	addSequential(new Wait(25));
	    	addSequential(new HighShot());*/
			addSequential(new AutonomousTurnToAngle(-90, 0.8, 2.5));
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousDriveStraightDistance(45, 0.7));
	    	addSequential(new Wait(25));
	    	addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
	    	addSequential(new ArmDown());
	    	addSequential(new Wait(50));
	    	addSequential(new Aim(0.7, 1));
	    	addSequential(new ToggleIntakePiston());
	    	addSequential(new Wait(75));
	    	addSequential(new HighShot());
		}
		//position 3/4
		SmartDashboard.putNumber("Position", position);
		SmartDashboard.putString("Defense", defenseName);
		/*addSequential(new Aim(0.7, 1));
		addSequential(new ToggleIntakePiston());
		addSequential(new Wait(25));
		addSequential(new HighShot());*/
    	
    	//position 2
    	/*addSequential(new ToggleArmPiston());
    	addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(45, 0.8, 2.5));
    	addSequential(new Wait(25));
    	addSequential(new AutonomousDriveStraightDistance(45, 0.7));
    	addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(5, 0.8, 2.5));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston());
    	addSequential(new Wait(50));
    	addSequential(new HighShot());*/
		
    	
        /*addSequential(new Wait(25));
		addSequential(new ToggleArmPiston());
		addSequential(new Wait(25));
		addSequential(new AutonomousTurnToAngle(0, 0.7, 1));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston()); 
    	addSequential(new Wait(50));
    	addSequential(new HighShot());*/
    	
    	// position 5
		/*addSequential(new AutonomousDriveStraight(-25, 0.5));
		addSequential(new Wait(25));
    	addSequential(new AutonomousTurnToAngle(-15, 0.6, 2.5));
    	addSequential(new Aim(0.7, 1));
    	addSequential(new ToggleIntakePiston());
    	addSequential(new Wait(25));
    	addSequential(new HighShot());*/
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
