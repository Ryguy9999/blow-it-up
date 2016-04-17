package com.fwumdesoft.music;

import java.util.ArrayList;

public class Measure 
{
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	public Measure(ArrayList<Note> notes)
	{
		this.notes = notes;
	}
	
	public ArrayList<Note> getNotes()
	{
		return notes;
	}
}
