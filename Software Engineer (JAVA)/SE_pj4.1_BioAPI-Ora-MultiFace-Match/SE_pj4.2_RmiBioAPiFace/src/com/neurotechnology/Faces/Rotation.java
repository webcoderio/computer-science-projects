package com.neurotechnology.Faces;

public class Rotation {
	private int Yaw;
	private int Pitch;
	private int Roll;
	
	public Rotation(int yaw, int pitch, int roll){
		this.Yaw = yaw;
		this.Pitch = pitch;
		this.Roll = roll;
	}
	
	public void setYaw(int yaw) {
		Yaw = yaw;
	}
	public int getYaw() {
		return Yaw;
	}
	public void setPitch(int pitch) {
		Pitch = pitch;
	}
	public int getPitch() {
		return Pitch;
	}
	public void setRoll(int roll) {
		Roll = roll;
	}
	public int getRoll() {
		return Roll;
	}
}
