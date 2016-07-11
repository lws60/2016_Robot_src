package org.usfirst.frc.team4587.robot.commands;

import org.usfirst.frc.team4587.robot.Robot;
import org.usfirst.frc.team4587.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.Parameters;

/**
 *
 */
public class AutonomousIRI extends CommandGroup {
	private int defense;
	private int position;
	private int backUp;
	private int twoBall;
	private String defenseName;
    
    public  AutonomousIRI() {
    	// rough terrain addSequential(new AutonomousDriveStraightDistance(135, 0.6));
    	// rock wall addSequential(new AutonomousDriveStraightDistance(210, 0.9));
    	
    	defense = RobotMap.ROCK_WALL; //Robot.getFieldDefense();
    	position = 3;//Robot.getFieldPosition();
    	backUp = 3;//Robot.getFieldBackUp();
    	twoBall = RobotMap.ROCK_WALL;//Robot.getFieldTwoBall();
    	//defense = RobotMap.CHEVAL;
    	//position = 3;
    	defenseName = "secret passage";
    	

		SmartDashboard.putNumber("Position", position);
		SmartDashboard.putNumber("Back-up Position", backUp);
		SmartDashboard.putNumber("Balls", twoBall);
    	
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
    		
    		if (twoBall == 1)
    		{
    			//one ball
    			addSequential(new LowbarOneBall());
    		}
    		else if(twoBall == 2)
    		{
    			//two ball
    			addSequential(new LowbarTwoBall());
    		}
    		
    		
    		defenseName = "low bar";
    		SmartDashboard.putString("Defense", defenseName);
    		return;
    	case RobotMap.ROCK_WALL:
    		addSequential(new AutonomousDriveStraightDistance(169, Parameters.getDouble("Rock Wall Speed", 0.9)));
    		/*addSequential(new AutonomousDriveStraightDistance(56, 0.7));
    		addSequential(new AutonomousDriveOverStaticDefense(0.7));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));*/
    		addSequential(new ArmDown());
    		defenseName = "rock wall";
    		break;
    	case RobotMap.ROUGH_TERRAIN:

    		addSequential(new AutonomousDriveStraightDistance(169, Parameters.getDouble("Rock Wall Speed", 0.9)));
    		/*addSequential(new AutonomousDriveStraightDistance(56, 0.7));
    		addSequential(new AutonomousDriveOverStaticDefense(0.7));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));*/
    		addSequential(new ArmDown());
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
    	/*case RobotMap.PORTCULLIS:
    		addSequential(new ArmDown());
    		addSequential(new Wait(25));
    		addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Portcullis Distance", 160), Parameters.getDouble("Portcullis Speed", 0.7)));
    		addSequential(new Wait(25));
    		defenseName = "portcullis";
    		break;*/
    	case RobotMap.RAMPARTS:
    		addSequential(new AutonomousDriveStraightDistance(169, Parameters.getDouble("Rock Wall Speed", 0.9)));
    		/*addSequential(new AutonomousDriveStraightDistance(56, 0.7));
    		addSequential(new AutonomousDriveOverStaticDefense(0.7));
    		addSequential(new AutonomousDriveStraightDistance(10, 0.4));*/
    		addSequential(new ArmDown());
    		break;
    	}
    	
    	
		SmartDashboard.putString("Defense", defenseName);
    	
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
	    	break;
		case 3:
	    	addSequential(new ArmDown());
			addSequential(new AutonomousTurnToAngle(7, 0.8, 2.5));
			addSequential(new LowerIntake());
	    	addSequential(new Wait(20));
	    	addSequential(new Aim2(0.3, 13, 0.1, 1, 0));
			addSequential(new Wait(10));
			addSequential(new HighShot());
			//addSequential(new Wait(20));
			
			break;
		case 4:
	    	addSequential(new ArmDown());
			addSequential(new LowerIntake());
	    	addSequential(new Wait(50));
	    	addSequential(new Aim2(0.3, 13, 0.1, 1, 0));
			addSequential(new Wait(10));
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
		
		switch (backUp)
		{
		case 3:
	    	addSequential(new Wait(20));
	    	addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
	    	addSequential(new Wait(20));
	    	addSequential(new AutonomousDriveStraightDistance(-125, -0.8));

			addParallel(new StartIntakeMotors(1.0));
	    	addSequential(new AutonomousTurnToAngle(-2, 0.8, 2.5));
			addSequential(new LowerIntake());
	    	addSequential(new AutonomousDriveStraightDistance(-48, -0.6));
			addSequential(new Wait(35));
			addParallel(new StartIntakeMotors(0.0));
			addSequential(new ArmUp());
			addSequential(new ToggleIntakePiston());
			break;
		case 4:
	    	addSequential(new Wait(20));
	    	addSequential(new AutonomousTurnToAngle(0, 0.8, 2.5));
	    	addSequential(new Wait(20));
	    	addSequential(new AutonomousDriveStraightDistance(-125, -0.8));
	    	
			addParallel(new StartIntakeMotors(1.0));
	    	addSequential(new AutonomousTurnToAngle(2, 0.8, 2.5));
			addSequential(new LowerIntake());
	    	addSequential(new AutonomousDriveStraightDistance(-48, -0.6));
			addSequential(new Wait(35));
			addParallel(new StartIntakeMotors(0.0));
			addSequential(new ArmUp());
			addSequential(new ToggleIntakePiston());
			break;
		}
		
		switch (twoBall)
		{
		case RobotMap.CHEVAL:
    		//cheval
			addSequential(new AutonomousDriveStraightDistance(27, 0.5));
			addSequential(new ArmDown());
			addSequential(new Wait(75));
			addSequential(new AutonomousDriveStraightDistance(95, 0.7));
			addSequential(new Wait(25));
			defenseName = "cheval";

	    	addSequential(new ArmDown());
	    	addSequential(new Wait(50));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(75));
			addSequential(new HighShot());
			break;
		
		case RobotMap.ROCK_WALL:
			addSequential(new ArmUp());
	    	addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
			addSequential(new Wait(20));
			addSequential(new AutonomousDriveStraightDistance(205, Parameters.getDouble("Rock Wall Speed", 0.9)));
			defenseName = "rock wall";
			
			addSequential(new ArmDown());
			addSequential(new LowerIntake());
			addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
	    	addSequential(new Aim2(0.3, 13, 0.1, 1, 0));
			addSequential(new Wait(10));
			addSequential(new HighShot());
			break;
		case RobotMap.ROUGH_TERRAIN:
			addSequential(new ArmUp());
	    	addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
			addSequential(new Wait(20));
			addSequential(new AutonomousDriveStraightDistance(190, Parameters.getDouble("Rock Wall Speed", 0.9)));
			defenseName = "rock wall";
			
			addSequential(new ArmDown());
			addSequential(new LowerIntake());
	    	addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
	    	addSequential(new Aim2(0.3, 13, 0.1, 1, 0));
			addSequential(new Wait(10));
			addSequential(new HighShot());
			break;
		case RobotMap.MOAT:
			//addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Moat Distance", 210), Parameters.getDouble("Moat Speed", 0.9)));
			addSequential(new AutonomousDriveStraightDistance(56, 0.4));
			addSequential(new AutonomousDriveOverStaticDefense(0.4));
			addSequential(new AutonomousDriveStraightDistance(10, 0.4));
			addSequential(new ArmDown());
			addSequential(new Wait(25));
			defenseName = "moat";

	    	addSequential(new ArmDown());
	    	addSequential(new Wait(50));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(75));
			addSequential(new HighShot());
			break;
		/*case RobotMap.PORTCULLIS:
			addSequential(new ArmDown());
			addSequential(new Wait(25));
			addSequential(new AutonomousDriveStraightDistance(Parameters.getInt("Portcullis Distance", 160), Parameters.getDouble("Portcullis Speed", 0.7)));
			addSequential(new Wait(25));
			defenseName = "portcullis";

	    	addSequential(new ArmDown());
	    	addSequential(new Wait(50));
			addSequential(new Aim(0.7, 1));
			addSequential(new ToggleIntakePiston());
			addSequential(new Wait(75));
			addSequential(new HighShot());
			break;*/
		case RobotMap.RAMPARTS:
			addSequential(new ArmUp());
	    	addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
			addSequential(new Wait(20));
			addSequential(new AutonomousDriveStraightDistance(205, Parameters.getDouble("Rock Wall Speed", 0.9)));
			defenseName = "rock wall";
			
			addSequential(new ArmDown());
			addSequential(new LowerIntake());
			addSequential(new AutonomousTurnToAngle(0, 0.7, 2.5));
	    	addSequential(new Aim2(0.3, 13, 0.1, 1, 0));
			addSequential(new Wait(10));
			addSequential(new HighShot());
			break;
		}
		
		//position 3/4
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
