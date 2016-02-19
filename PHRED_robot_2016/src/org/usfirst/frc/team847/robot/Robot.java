
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
public class Robot extends IterativeRobot implements RobotMap{
	
	GamePad turnControl;
	GamePad controller2;
	DriveTrain scrubTrain;
    BallShooter shooter2;
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    public void robotInit() {
    	controller2 = new GamePad(OBJ_MANIP_GAMEPAD);// give controller2 in GamePad the variable 2
    	turnControl = new GamePad(DRIVE_GAMEPAD);// give controller1 in GamePad the variable 1
    	scrubTrain = new DriveTrain();
    	shooter2 = new BallShooter(controller2);
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
    	double feedsd = turnControl.quadraticLY();
    	double feeddir = turnControl.rightStickX();     		
        scrubTrain.turnWheel(feeddir);
        scrubTrain.driveWheels(feedsd);
        shooter2.shootingMethod();
}
    

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	scrubTrain.testMotor();

    }
    
}
