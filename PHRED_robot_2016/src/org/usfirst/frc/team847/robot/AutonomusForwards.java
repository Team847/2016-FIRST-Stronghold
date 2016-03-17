package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class AutonomusForwards implements RobotMap  {
	DriveTrain AutoDrive;
	double timer;
	
	public AutonomusForwards(DriveTrain dt){
		AutoDrive = dt; //new DriveTrain(autoWheel, autoPad);
	}

	public void resetAuto(){
		timer = 0;
	}
	
	public void autoControl(String autoSelected){
		
		switch(autoSelected) {
    		case DRIVE_FORWARD_AUTO:
    			autoDrive();
    			break;
    		case DEFAULT_AUTO:
    		default:
    	}
	}

	public void autoDrive(){
		//50 on the timer = 1 secconds. so 50 = 1, 50 = 1, 50 = 1, and 50 = 1
		//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
		if(timer <= 250){
			AutoDrive.turnWheel(0);
			AutoDrive.driveWheels(-0.6);
			timer++;
			//System.out.println(timer);
		}else{
			AutoDrive.turnWheel(0);
			AutoDrive.driveWheels(0);
		}
	}
}