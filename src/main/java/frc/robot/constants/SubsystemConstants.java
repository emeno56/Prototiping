package frc.robot.constants;

public class SubsystemConstants {

    public static final String CAN_BUS = "Drivetrain";

    public static class HopperConstants {
        public static final int HOPPER_LOWER_MOTOR_ID = 21;
        public static final int HOPPER_LOWER_MOTOR_2_ID = 23;
        public static final int HOPPER_UPPER_MOTOR_ID = 22;
    }

    public static class LoaderConstants {
        public static final int LOADER_MOTOR_ID = 31;
    }

    public static class ShooterConstants {
        public static final int SHOOTER_MOTOR_ID = 41;
        public static final String SHOOTER_LOG_KEY = "Subsystem/Shooter/"; 
        public static final double SHOOTER_HEIGHT_M = 0.7; //TODO: this is a placeholder value, get the actual height of the shooter and the angle of the shooter
    }

    public static class IntakeConstants {
        public static final int INTAKE_MOTOR_ID = 51;
    }

    public static class DrivetrainConstants {
        public static final double DISTANCE_TO_HUB_M = 2.5;//TODO: this is a placeholder value, need to calculate the actual value when we have a drivetrain
    }
}
