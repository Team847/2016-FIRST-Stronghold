package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements RobotMap{
	CommandGroup autonomusComand;
	SendableChooser autoChooser;
	
	CameraServer Camera;
	GamePad speedControl;
	GamePad controller2;
	GamePad steeringControl;

	String autoSelected;
    SendableChooser chooser;
	AutonomusForwards autonomous;
	AutonomusBall autonomous1;
	Just_SHOOT autonomous2;
	BuiltInAccelerometer accelerometer;

	DriveTrain scrubTrain;
    BallShooter shooter2;
	ObstacleArmElbow arm;
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    public void robotInit() {
    	Camera = CameraServer.getInstance();
        Camera.setQuality(50);
        Camera.startAutomaticCapture("cam0");

        controller2 = new GamePad(OBJ_MANIP_GAMEPAD);// give controller2 in GamePad the variable 2
    	speedControl = new GamePad(DRIVE_GAMEPAD);// give controller1 in GamePad the variable 1
    	steeringControl = new GamePad(TURN_CONTROL);

    	scrubTrain = new DriveTrain(speedControl, steeringControl);
    	shooter2 = new BallShooter(controller2, speedControl);
    	arm = new ObstacleArmElbow(controller2);
    	
    	accelerometer = new BuiltInAccelerometer();
    	
        chooser = new SendableChooser();
        chooser.addDefault("Default", DEFAULT_AUTO);
        chooser.addObject("Drive Forward", DRIVE_FORWARD_AUTO);
        SmartDashboard.putData("Auto choices", chooser);

    	autonomous = new AutonomusForwards(scrubTrain);
    	autonomous1 = new AutonomusBall();
    	autonomous2 = new Just_SHOOT();
    	//autoChooser = new SendableChooser();
    	//autoChooser.addDefault("Shooting only", Just_SHOOT );
    	
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
    	autonomous.reset();
    	//autonomusComand = (CommandGroup)autoChooser.getSelected();
    	//autonomusCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//autonomous.autoControl((String)chooser.getSelected());
    	//System.out.println("Accel Y: " + accelerometer.getY());
    	//autonomous.autoControl(autoSelected);
    	autonomous.autoDrive();
    }

    /***
     * This function is called periodically during operator control
     */
    public void teleopInit(){

    }
    
    public void teleopPeriodic() {
		shooter2.runShooter();
    	arm.moveArm();
    	scrubTrain.driveController();
    	
    	System.out.println("Accel Y: " + accelerometer.getY());
    	
        Timer.delay(0.005);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
//    	scrubTrain.testMotor();
//    	Timer.delay(0.005);
    	arm.armOverride();
}
}