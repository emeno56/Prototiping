package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.KrakenBuilder;

import static frc.robot.constants.SubsystemConstants.HopperConstants.*;
import static frc.robot.constants.SubsystemConstants.*;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

//Agitaor???
public class Hopper extends SubsystemBase {

  TalonFX lowerMotor;
  TalonFX lowerMotor2;
  TalonFX upperMotor;

  public Hopper() {
    lowerMotor = KrakenBuilder.create(HOPPER_LOWER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.4,0,0.00000000001)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();
    
    lowerMotor2 = KrakenBuilder.create(HOPPER_LOWER_MOTOR_2_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.4,0,0.00000000001)
      .withInversion(InvertedValue.CounterClockwise_Positive)
      .build();

    upperMotor = KrakenBuilder.create(HOPPER_UPPER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.4, 0, 0.00000000001)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();
  }

  public Runnable agitate(double agitateSpeed) {
    return () -> { 
      lowerMotor.set(agitateSpeed);
      lowerMotor2.set(agitateSpeed);
      upperMotor.set(agitateSpeed);
    };
  }

  public Runnable agitate() {
    double lowerAgitation = 84;
    return () -> {
      lowerMotor.setControl(new VelocityVoltage(0/*lowerAgitation*/).withEnableFOC(true));
      lowerMotor2.setControl(new VelocityVoltage(lowerAgitation).withEnableFOC(true));
      upperMotor.setControl(new VelocityVoltage(10).withEnableFOC(true));
    };
  }

  @Override
  public void periodic() {}
}
