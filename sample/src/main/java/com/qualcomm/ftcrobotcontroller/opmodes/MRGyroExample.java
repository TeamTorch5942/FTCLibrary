package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

public class MRGyroExample extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        //Setup
        ModernRoboticsI2cGyro sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        DeviceInterfaceModule cdim = hardwareMap.deviceInterfaceModule.get("dim");
        hardwareMap.logDevices();

        // calibrate the gyro.
        sensorGyro.calibrate();
        waitForStart();

        // make sure the gyro is calibrated.
        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
            cdim.setLED(0,true);
        }
        cdim.setLED(0,false);
        cdim.setLED(1,true);


        while (opModeIsActive()) {
            telemetry.addData("Heading ", sensorGyro.getHeading());
            telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());
            Thread.sleep(100);
        }
    }
}
/*
Name the sensor	 	ModernRoboticsI2cGyro sensorGyro = hardwareMap.gyroSensor.get("gyro");
Calibrate the gyro	 	sensorGyro.calibrate();
Wait for calibration to finish.

 	while (sensorGyro.isCalibrating()) {
Thread.sleep(50);
}
Read rate of rotation x	 	sensorGyro.rawX();
Read rate of rotation y	 	sensorGyro.rawY();
Read rate of rotation z	 	sensorGyro.rawZ();
Read heading (0-359)	 	sensorGyro.getHeading();
Read heading (- & +)	 	sensorGyro.getIntegratedZValue();
Reset Heading               G.resetZAxisIntegrator();


Integrated Z Value is most useful.
Turning clockwise/right is -, counterclockwise/left is +.
Value will begin at zero on calibration or reset.
*/
