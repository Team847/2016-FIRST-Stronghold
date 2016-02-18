package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArm {

	CANTalon Elbow = new CANTalon(1);
	CANTalon Shoulder = new CANTalon(2);
	double pShoulder;
	double pElbow;
	boolean shoulderFWD;
	boolean shoulderREV;
	boolean elbowFWD;
	boolean elbowREV;
	boolean shoulderMash;
	boolean elbowMash;
	/*Note to Stephen: When you pull all the pieces together for the robot map
	 *You'll have to create two xbox controller classes, one being the drive 
	 *controller and the other being the object manip controller. This class here
	 *needs to get all it's values from the object manip controller.*/
	public ObstacleArm(){
		Shoulder.set(0);
		Elbow.set(0);
		shoulderFWD = Shoulder.isFwdLimitSwitchClosed();
		shoulderREV = Shoulder.isRevLimitSwitchClosed();
		Shoulder.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		Elbow.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		pShoulder = Shoulder.getAnalogInPosition();
		pElbow = Elbow.getAnalogInPosition();
	}
	public void shoulderJoint(boolean reachUP, boolean reachDOWN, double minS, double maxS){

		/*  The Robot Framework program should send this method a handful of 
		 *  button values in the form of booleans to decide which way to move
		 * The code below will stop the motors from doing anything if BOTH 
		 * buttons are pressed, cuz who knows what would happen if both were pressed
		 * at the same time...?? I have no clue. don't be a buttonmasher!*/
		
		if(reachUP == true && reachDOWN == true){
			shoulderMash = true;
		}
		else{
			shoulderMash = false;
		}
		if(shoulderMash = true){
			Shoulder.set(0);
		}
		else{
			if(pShoulder == maxS || shoulderFWD == true){
				Shoulder.set(0);
			}
			else if(reachUP == true){
				Shoulder.set(-.2);
				}
			
		if(pShoulder == minS || shoulderREV == true){
			Shoulder.set(0);
			}
			else if(reachDOWN == true){
				Shoulder.set(.2);
				}
			}
		}
	public void elbowJoint(boolean reachUP, boolean reachDOWN, double minE, double maxE){
		
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
		
		if(elbowFWD == true || pElbow == maxE){
				Elbow.set(0);
			}
			else{
				if(reachDOWN == true){
				Elbow.set(-.2);
				}
			}
		if((pElbow == minE || elbowREV == true) && reachUP == true){
				Elbow.set(0);
			}
			else{
				if(elbowREV = false){
					Elbow.set(.2);
				}
			}
		}

	/* Hey Stephen!! This class wants six variables to work,
		so we gotta make sure it has them all.  */
	
	public void presets(boolean upPosButton, boolean drivePosButton, double SupPosition, double SdrivePosition, double EupPosition, double EdrivePosition){
		
		if(upPosButton == true && pShoulder < SupPosition){
			Shoulder.set(.2);
		}
		else{
			if(drivePosButton == true && pShoulder > SdrivePosition){
				Shoulder.set(-.2);
			}
			else{
				Shoulder.set(0);
			}
		}
		
		if(upPosButton == true && pElbow < EupPosition){
			Elbow.set(.2);
		}
		else{
			if(drivePosButton == true && pElbow > EdrivePosition){
				Elbow.set(-.2);
			}
			else{
				Elbow.set(0);
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

