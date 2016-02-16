
package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Joystick turn;
	DriveTrain scrubTrain;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    public void robotInit() {
    	
    	turn = new Joystick(1);
    	scrubTrain = new DriveTrain();
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /***
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double feedsd = turn.getRawAxis(4);
    	double feedfwd = turn.getRawAxis(1);
    	
    	if(Math.abs(feedsd) <= 0.05){
    		feedsd = 0;
    	}
    	
    	if(Math.abs(feedfwd) <= 0.05){
    		feedfwd = 0;
    	}
    	
        scrubTrain.turnWheel(feedsd);
        scrubTrain.driveWheels(feedfwd);
//  scrubTrain.turnWheel(.5);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	scrubTrain.testMotor();

    }
    
}
