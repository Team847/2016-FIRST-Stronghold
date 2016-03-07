package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class AutonomusForwards implements RobotMap  {
	GamePad autoPad;
	GamePad autoWheel;
	DriveTrain AutoDrive;
	BallShooter AutoShooter;
	
public AutonomusForwards(){
	AutoDrive = new DriveTrain(autoWheel, autoPad);
	AutoShooter = new BallShooter(autoWheel, autoPad);
}
public void AutoDrive(){
	
double timer = 0;
//50 on the timer = 1 secconds. so 50 = 1, 50 = 1, 50 = 1, and 50 = 1
//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
if(timer <= 250){
	AutoDrive.turnWheel(0);
	AutoDrive.driveWheels(0.5);
	timer ++;
		}
	return;
	}
	
public void AutoShoot(){
	AutoShooter.elevatorDown();
	return;
}

}