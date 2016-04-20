package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import utility.Parameters;

import org.usfirst.frc.team4587.robot.Robot;

public class AutoIntakeBall extends Command {
    public AutoIntakeBall(){
       	requires(Robot.getIntake());
    }
    @Override
    protected void initialize() {
        if ( Robot.getIntake().isBallLoaded() == false )
        {
           	Scheduler.getInstance().add(new LowerIntake());
        	Robot.getIntake().setIntakeMotorTarget(Parameters.getDouble("Intake Motor Input Speed", 1.0));
        }
    }
    @Override
    protected void execute() {
       	Robot.getIntake().updateIntakeMotor();
    }
    @Override
    protected boolean isFinished() {
       	return Robot.getIntake().isBallLoaded();
    }
    @Override
    protected void end() {
       	Robot.getIntake().setIntakeMotorTarget(0.0);
       	Scheduler.getInstance().add(new RaiseIntake());
    }
    @Override
    protected void interrupted() {
       	end();
    }
}
