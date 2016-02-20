package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	CANTalon ShoulderE = new CANTalon(CANTALON_SHOULDER);
	double pShoulderE;
	double pElbow;
	boolean shoulderEFWD;
	boolean shoulderEREV;
	boolean elbowFWD;
	boolean elbowREV;
	boolean shoulderMash;
	boolean elbowMash;
	/*Note to Stephen: When you pull all the pieces together for the robot map
	 *You'll have to create two xbox controller classes, one being the drive 
	 *controller and the other being the object manip controller. This class here
	 *needs to get all it's values from the object manip controller. */
	public ObstacleArmElbow(GamePad xbox){
		
		gamePad = xbox;
		
		ShoulderE.set(0);
		Elbow.set(0);
		shoulderEFWD = ShoulderE.isFwdLimitSwitchClosed();
		shoulderEREV = ShoulderE.isRevLimitSwitchClosed();
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
//		ShoulderE.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
//		Elbow.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		pShoulderE = ShoulderE.getAnalogInPosition();
		pElbow = Elbow.getAnalogInPosition();
	}
	public void shoulderJoint(){
		
		/*  The Robot Framework program should send this method a handful of 
		 *  button values in the form of booleans to decide which way to move
		 * The code below will stop the motors from doing anything if BOTH 
		 * buttons are pressed, cuz who knows what would happen if both were pressed
		 * at the same time...?? I have no clue. don't be a buttonmasher!*/
		
		boolean reachSUP = gamePad.yButton();
		boolean reachSDOWN = gamePad.xButton();
		
		if(reachSUP == true && reachSDOWN == true){
			ShoulderE.set(0);
			return;
		}
		
		if(pShoulderE == MAX_S || shoulderEFWD == true){
			ShoulderE.set(0);
			return;
		}
		
		if(pShoulderE == MIN_S || shoulderEREV == true){
			ShoulderE.set(0);
			return;
		}
		
		if(reachSUP == true){
			ShoulderE.set(.5);
		}
		else if(reachSDOWN == true){
					ShoulderE.set(-.5);
			}
		else ShoulderE.set(0);
	}

	public void elbowJoint(){
		
		boolean reachEUP = gamePad.lStickPressed();
		boolean reachEDOWN = gamePad.rStickPressed();
		
		if(reachEUP == true && reachEDOWN == true){
			Elbow.set(0);
			return;
		}
		
		if(pElbow == MAX_S || elbowFWD == true){
			Elbow.set(0);
			return;
		}
		
		if(pElbow == MIN_S || elbowREV == true){
			Elbow.set(0);
			return;
		}
		
		if(reachEUP == true){
			Elbow.set(.5);
		}
		else if(reachEDOWN == true){
					Elbow.set(-.5);
			}
		else Elbow.set(0);
		return;
	}	 

	
	public void presets(boolean upPosButton, boolean drivePosButton, double SupPosition, double SdrivePosition, double EupPosition, double EdrivePosition){
		
		if(upPosButton == true && pShoulderE < SupPosition){
			ShoulderE.set(.5);
		}
		else{
			if(drivePosButton == true && pShoulderE > SdrivePosition){
				ShoulderE.set(-.5);
			}
			else{
				ShoulderE.set(0);
			}
		}
		
		if(upPosButton == true && pElbow < EupPosition){
			Elbow.set(.5);
		}
		else{
			if(drivePosButton == true && pElbow > EdrivePosition){
				Elbow.set(-.5);
			}
			else{
				Elbow.set(0);
				return;
			}
		}
	}
}







/*  
 *  
 *  __-  
 * |\  _~`|                                                   
 * | \  -~`|                                                              
 * |  \  _~`|                                                            
 * |   \  -~`|                                                              
 *  \   \  -~`|                                                               
 *   \   \  _~`|                                                             
 *    \   \  _~`|                                                              
 *     \   \  -~`|                                                           
 *      \   \  -~`|`                                                               
 *       \   \  -~`|                                                              
 *        \   \  _~`|                                                               
 *         \   \  -~`|                                                                 
 *          \   \  _~`|                                                            
 *           \   \  _~`|                                                              
 *            \   \  -~`|                                                                
 *             \   \  _~``|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  ||                                                         
 *              \   \  -~ ~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`\                                                     
 *               \   \  _ -- _ - - _ _ - _ __ -- _ - - _ _ - _ __ -- _ - - \
 *                \   \_____________________________________________________\
 *                 \  |                                                     |
 *                  \ |                                                     |
 *                   \|_____________________________________________________|
 */

