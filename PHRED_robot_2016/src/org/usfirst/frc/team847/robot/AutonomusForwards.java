package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class AutonomusForwards implements RobotMap  {
	GamePad autoPad;
	GamePad autoWheel;
	DriveTrain AutoDrive;
	double timer;
	//BallShooter AutoShooter;
	
public AutonomusForwards(DriveTrain dt){
	AutoDrive = dt; //new DriveTrain(autoWheel, autoPad);
	timer = 0;
	//AutoShooter = new BallShooter(autoWheel, autoPad);
}

public void AutoDrive(){
	
//50 on the timer = 1 secconds. so 50 = 1, 50 = 1, 50 = 1, and 50 = 1
//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
if(timer <= 250){
	AutoDrive.turnWheel(0);
	AutoDrive.driveWheels(-0.6);
	timer++;
	System.out.println(timer);
		}
else{
	AutoDrive.turnWheel(0);
	AutoDrive.driveWheels(0);
}
	return;
	}
	
public void AutoShoot(){
	//AutoShooter.elevatorDown();
	return;
}

}