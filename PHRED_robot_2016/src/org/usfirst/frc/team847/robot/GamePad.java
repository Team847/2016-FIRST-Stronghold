package org.usfirst.frc.team847.robot;																									
import edu.wpi.first.wpilibj.Joystick;

public class GamePad extends Joystick {

	// Stick axis mappings
	private final static int lStickX     = 0;  //Left Stick, X Axis
	private final static int lStickY     = 1;  //Left Stick, Y Axis
	private final static int lTrigger    = 2;  //left Trigger
	private final static int rTrigger    = 3;  //right Trigger
	private final static int rStickX     = 4;  //right Stick X Axis
	private final static int rStickY     = 5;  //right Stick Y Axis
	
	// Button mappings
	private final static int buttonA     = 1;  //a Button
	private final static int buttonB     = 2;  //b Button
	private final static int buttonX     = 3;  //x Button
	private final static int buttonY     = 4;  //y Button
	private final static int bumperL     = 5;  //l Bumper
	private final static int bumperR     = 6;  //r Bumper
	private final static int buttonBack  = 7;  //back button
	private final static int buttonStart = 8;  //start button
	private final static int pressLStick = 9;  //left Stick Pressed
	private final static int pressRStick = 10; //right Stick Pressed
	
	// Dpad mapping 
	private final static int dPad        = 0;  // dPad: Returns Angle in degrees {0,45,90,...,315}

	// Instance variables
	private double min = 0.10;
	private double max = 1.00;

	// Constructors 
	// Port corresponds to the port used on the Driver Station
	public GamePad(int port) {
		super(port);
	}
	
	// Port corresponds to the port used on the Driver Station
	// minimum and maximum are used for validating axis values 
	public GamePad(int port, double minimum, double maximum){
		super(port);
		min = minimum;
		max = maximum;
	}

	// Axis methods: Joy sticks and triggers
	// After being run through a range tester they return values between -max and max.
	// Axis values in the 'deadzone', which is defined as -min to min, are reset to 0.
	
	public double leftStickX() {
		double LX = getRawAxis(lStickX);
		return rangeTester(LX);
	}

	public double leftStickY() {
		double LY = getRawAxis(lStickY);
		return rangeTester(LY);
	}

	public double rightTrigger() {
		double RT = getRawAxis(rTrigger);
		return rangeTester(RT);
	}

	public double leftTrigger() {
		double LT = getRawAxis(lTrigger);
		return rangeTester(LT);
	}

	public double rightStickX() {
		double RX = getRawAxis(rStickX);
		return rangeTester(RX);
	}

	public double rightStickY() {
		double RY = getRawAxis(rStickY);
		return rangeTester(RY);
	}

	//Gets the squared  (^2)  value of every axis on the controller
	public double quadraticLX(){
		double LX1x2 = getRawAxis(lStickX);
		double LXj = rangeTester(LX1x2);
		double LXf = (LXj*Math.abs(LXj));
		return LXf;
	}
	
	public double quadraticLY(){
		double LY1x2 = getRawAxis(lStickY);
		double LYj = rangeTester(LY1x2);
		double LYf = (LYj*Math.abs(LYj));
		return LYf;
	}
	
	public double quadraticRX(){
		double RX1x2 = getRawAxis(rStickX);
		double RXj = rangeTester(RX1x2);
		double RXf = (RXj*Math.abs(RXj));
		return RXf;
	}
	
	public double quadraticRY(){
		double RY1x2 = getRawAxis(rStickY);
		double RYj = rangeTester(RY1x2);
		double RYf = (RYj*Math.abs(RYj));
		return RYf;
	}
	
	public double quadraticLT(){
		double LT1x2 = getRawAxis(lTrigger);
		double LTj = rangeTester(LT1x2);
		double LTf = (LTj*Math.abs(LTj));
		return LTf;
	}
	
	public double quadraticRT(){
		double RT1x2 = getRawAxis(rTrigger);
		double RTj = rangeTester(RT1x2);
		double RTf = (RTj*Math.abs(RTj));
		return RTf;
	}
	
	// Validate the Axis values
	private double rangeTester(double value) {
		if(Math.abs(value) <= min)
			value = 0;
		if (value > max)
			value =  max;
		else if(value < -max)
			value = -max;
		value = deadzoneScaler(value);
		
		return value;		
	}
	
	private double deadzoneScaler(double value){
	
	double temp = Math.abs(value);
	temp = (temp - min)/(max - min);

	if(value < 0)
		temp *= -1;
	
	return temp;
	
	}
	
	// Button methods
	// Buttons return true/false, which is a result of being pressed/not pressed
	public boolean aButton() {
		return getRawButton(buttonA);
	}

	public boolean bButton() {
		return getRawButton(buttonB);
	}

	public boolean xButton() {
		return getRawButton(buttonX);
	}

	public boolean yButton() {
		return getRawButton(buttonY);
	}

	public boolean lBumper() {
		return getRawButton(bumperL);
	}

	public boolean rBumper() {
		return getRawButton(bumperR);
	}

	public boolean backB() {
		return getRawButton(buttonBack);
	}

	public boolean startB() {
		return getRawButton(buttonStart);
	}

	public boolean lStickPressed() {
		return getRawButton(pressLStick);
	}

	public boolean rStickPressed() {
		return getRawButton(pressRStick);
	}
	
	// Dpad method(s)
	// Returns angle in degrees {0,45,90,...,315), with 'up' being 0.
	public int dPad() {
		return getPOV(dPad);
	}
}


































































































///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
////////                                                                       ////////
////////         | ?  | ?|  ? |  ? |                                           ////////
////////     }    \\   / }  \/   /     {                                       ////////
////////   }  {      ?\ ?   /?   ?  }      }                                   ////////
////////               ? \?/ ?                                                 ////////
////////               \ |  \ /                                                ////////
////////                | | |                                                  ////////
////////         ___-----|||--_____                                            ////////
////////        /                  \                     ________________      ////////
////////       /                    -_                  /                |     ////////
////////      |              ___      \___              |_               |     ////////
////////      |             | 0 \         -___            \_      __ _-/       ////////
////////      |              ----             \__         _/     /             ////////
////////       \                                 \_______/     _/              ////////
////////        \  \        __/                               /-               ////////
////////         \  \______/                                 /                 ////////
////////          \_                                     __/                   ////////
////////            ------_____                    ____-                       ////////
////////                       ------________------                            ////////
////////                                                                       ////////
////////     ?????__     ?????__     ?????__     ?????__     ?????__           ////////
////////       \    \      \    \      \    \      \    \      \    \          ////////
////////        |?   \      |?   \      |?   \      |?   \      |?   \         ////////
////////-_-_-_-/?     \-_-_/?     \_-_-/?     \-_-_/?     \-_-_/?     \-_-_-_-_////////
////////      /?       |  /?       |  /?       |  /?       |  /?       |  -_-  ////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
