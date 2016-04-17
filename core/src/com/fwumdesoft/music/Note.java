package com.fwumdesoft.music;

import java.util.ArrayList;

public class Note 
{
	private int midiNumber;
	private int length;
	
	public Note(String note, String length)
	{
		midiNumber = Integer.parseInt(note);
		this.length = Integer.parseInt(length);
	}
	
	public int getMidiNumber()
	{
		return midiNumber;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public static double getTotalLength(ArrayList<Note> n)
	{
		double totalLength = 0.0;
		for(int i = 0; i < n.size(); i++)
			totalLength += (1.0/n.get(i).getLength());
		return totalLength;
	}
}
