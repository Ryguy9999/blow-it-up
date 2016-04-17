package com.fwumdesoft.music;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Song 
{
	public Measure[] measures;
	public int[] measureOrder;
	public String songName;
	public int bpm;
	
	public Song(Measure[] measures, int[] measureOrder, String songName, int bpm)
	{
		this.measures = measures;
		this.measureOrder = measureOrder;
		this.songName = songName;
		this.bpm = bpm;
	}
	
	public Song(File f) throws FileNotFoundException
	{
		this.songName = f.getName();
		Scanner scan = new Scanner(f);
		this.bpm = Integer.parseInt(scan.nextLine());
		String[] occurencesStrings = scan.nextLine().split(" ");
		measureOrder = new int[occurencesStrings.length];
		this.measures = new Measure[measureOrder.length];
		for(int i = 0; i < occurencesStrings.length; i++)
			measureOrder[i] = Integer.parseInt(occurencesStrings[i]);
		for(int measure = 0; scan.hasNext(); measure++)
		{
			String[] data = scan.nextLine().split(" ");
			ArrayList<Note> notes = new ArrayList<Note>();
			for(int i = 0; i < data.length; i += 2)
				notes.add(new Note(data[i], data[i + 1]));
			measures[measure] = new Measure(notes);
		}
	}
}
