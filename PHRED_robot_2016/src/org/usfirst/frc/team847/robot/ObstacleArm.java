package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArm {
	
	CANTalon Elbow = new CANTalon(1);
	CANTalon Shoulder = new CANTalon(2);
	double pReader = 0;
	boolean shoulderFWD = false;
	boolean shoulderREV = false;
	boolean elbowFWD = false;
	boolean elbowREV = false;
	boolean buttonMash = false;
	/*Note to Stephen: When you pull all the pieces together for the robot map
	 *You'll have to create two xbox controller classes, one being the drive 
	 *controller and the other being the object manip controller. This class here
	 *needs to get all it's values from the object manip controller.*/
	
	public ObstacleArm(){
	}

	public void shoulderJoint(boolean reachUP, boolean reachDOWN){
		
		/*  Robot Frame should send me boolean reachUP so this code
		    can decide how the arm should move. */
		shoulderFWD = Shoulder.isFwdLimitSwitchClosed();
		shoulderREV = Shoulder.isRevLimitSwitchClosed();
		pReader = Shoulder.getPosition();
		
		/*stops the motors from doing anything if BOTH buttons are pressed
		 * cuz who knows what would happen if both were pressed at the same
		 * time...?? I have no clue. don't be a buttonmasher!*/
		if(reachUP == true && reachDOWN == true){
			buttonMash = true;
		}
		else{
			buttonMash = false;
		}
		
		/* pretty much everything in this class below this point is receiving
		 * the state of reachUP and reachDOWN and telling the motors what to do
		 * from there.*/
		if(buttonMash = true){
			Shoulder.set(0);
		}
		else{
			if(reachUP == true){
				if(shoulderFWD == true){
					Shoulder.set(0);
			}
				else{ 
					if(shoulderFWD == false){
						Shoulder.set(-.2);
				}
			}
		}
		
		if(reachDOWN == true){
			if(shoulderREV == true){
				Shoulder.set(0);
			}
			else{
				if(shoulderREV == false){
					Shoulder.set(.2);
				}
			}
		}
	}
		return;
}
	
	public void presets(boolean upPos, boolean drivePos, double upPosition, double drivePosition){
		
		if(upPos == true && pReader > drivePosition && pReader < upPosition){
			Shoulder.set(.2);
		}
		else{
			if(drivePos == true && pReader > drivePosition && pReader < upPosition){
				Shoulder.set(-.2);
			}
			else{
				Shoulder.set(0);
			}
		}
	}
	
	/*   (UNUSED CLASS)VVVVVVVVV  
	 *It turns out we aren't using this elbow joint but it's still here cuz 
	 *it doesn't hurt anything by staying, and it would be a pain to take it 
	 *all out and then realize a day later that I actually wanted it...*/
	public void elbowJoint(boolean reachOUT, boolean reachIN){
	//obsolete now :P
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
		
		if(reachOUT = true){
			if(elbowFWD = true){
				Elbow.set(0);
			}
			else{ 
				if(elbowFWD = false){
					Elbow.set(-.2);
				}
			}
		}
		
		if(reachIN = true){
			if(elbowREV = true){
				Elbow.set(0);
			}
			else{
				if(elbowREV = false){
					Elbow.set(.2);
				}
			}
		}

	}
}