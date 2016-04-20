package org.usfirst.frc.team4587.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4587.robot.Robot;

public class IntakeAndShooterIdle extends Command {
    public IntakeAndShooterIdle()
    {
    	//requires(Robot.getIntakeAndShooter());
    	setInterruptible(true);
    }
    @Override
    protected void initialize() {
       	//Robot.getIntakeAndShooter().setIntakeMotorTarget(0.0);
       	//Robot.getIntakeAndShooter().setShootingMotorTarget(0.0);
       	//Robot.getIntakeAndShooter().setElevatorMotorTarget(0.0);
       	//Robot.getIntakeAndShooter().disarm();
    }
    @Override
    protected void execute() {
       	//Robot.getIntakeAndShooter().updateIntakeMotor();
       	//Robot.getIntakeAndShooter().updateElevatorMotor();
       	//Robot.getIntakeAndShooter().updateShootingMotor();
    }
    @Override
    protected boolean isFinished() {
       	return false;
    }
    @Override
    protected void end() {
    }
    @Override
    protected void interrupted() {
       	end();
    }
}
