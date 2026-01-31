package frc.robot.subsystems;

import static frc.robot.constants.SubsystemConstants.CAN_BUS;
import static frc.robot.constants.SubsystemConstants.ShooterConstants.SHOOTER_MOTOR_ID;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.KrakenBuilder;

public class Shooter extends SubsystemBase {

  TalonFX motor;
  public Shooter() {
    motor = KrakenBuilder.create(SHOOTER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.6, 0, 0.000000001)
      .withInversion(InvertedValue.CounterClockwise_Positive)
      .build();
  }

  public Runnable rev(double revSpeed) {
    return () -> motor.set(revSpeed);//setControl(new DutyCycleOut(revSpeed).withEnableFOC(true));
  }

  public Runnable rev(DoubleSupplier revSpeed) {
    return () -> motor.setControl(new VelocityVoltage(revSpeed.getAsDouble()).withEnableFOC(true));
  }

  @Override
  public void periodic() {}
}
