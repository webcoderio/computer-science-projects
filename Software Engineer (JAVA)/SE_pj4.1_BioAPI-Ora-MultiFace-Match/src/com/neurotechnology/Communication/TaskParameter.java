package com.neurotechnology.Communication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TaskParameter {

	private ByteBuffer value;
	
	public TaskParameter(int part, int ID, long value){
		this.value = ByteBuffer.allocate(16);
		this.value.order(ByteOrder.BIG_ENDIAN);
		this.value.putInt(part);
		this.value.putInt(ID);
		this.value.putLong(value);
	}
	
	public TaskParameter(int part, int ID, double value){
		this.value = ByteBuffer.allocate(16);
		this.value.order(ByteOrder.BIG_ENDIAN);
		this.value.putInt(part);
		this.value.putInt(ID);
		this.value.putDouble(value);
	}
	
	public TaskParameter(int part, int ID, int value){
		this.value = ByteBuffer.allocate(16);
		this.value.order(ByteOrder.BIG_ENDIAN);
		this.value.putInt(part);
		this.value.putInt(ID);
		this.value.putInt(0);
		this.value.putInt(value);
	}
	
	public TaskParameter(int part, int ID, short value){
		this.value = ByteBuffer.allocate(16);
		this.value.order(ByteOrder.BIG_ENDIAN);
		this.value.putInt(part);
		this.value.putInt(ID);
		this.value.putInt(0);
		this.value.put((byte)0);
		this.value.put((byte)0);
		this.value.putShort(value);
	}
	
	public byte[] getValue() {
		return value.array();
	}
}
