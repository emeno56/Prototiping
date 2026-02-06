package frc.robot.subsystems;

import static frc.robot.constants.SubsystemConstants.CAN_BUS;
import static frc.robot.constants.SubsystemConstants.ShooterConstants.SHOOTER_MOTOR_ID;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import dev.doglog.DogLog;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.Kraken;
import frc.robot.utils.hardware.KrakenBuilder;
import frc.robot.utils.hardware.MotorLogger;

public class Shooter extends SubsystemBase {
  Kraken motor;

  public Shooter() {
    motor = KrakenBuilder.create(SHOOTER_MOTOR_ID, CAN_BUS, "Shooter Motor")
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.6, 0, 0.000000001)
      .withInversion(InvertedValue.CounterClockwise_Positive)
      .build();
  }

  public Runnable rev(double revSpeed) {
    return () -> motor.set(revSpeed);//setControl(new DutyCycleOut(revSpeed).withEnableFOC(true));
  }

  public Runnable rev(DoubleSupplier revSpeed) {//, double p, double i, double d) {
    DogLog.log("Subsystems/Shooter/RPS", revSpeed.getAsDouble());
    // DogLog.log("Shooter P", p);
    // DogLog.log("Shooter I", i);
    // DogLog.log("Shooter D", d);

    
    // Slot0Configs configs = new Slot0Configs().withKP(p).withKI(i).withKD(d);
    // motor.getConfigurator().apply(configs);
    return () -> motor.setControl(new VelocityVoltage(revSpeed.getAsDouble()).withEnableFOC(true));
  }

  @Override
  public void periodic() {
    MotorLogger.log("Shooter", motor);
  }
}
