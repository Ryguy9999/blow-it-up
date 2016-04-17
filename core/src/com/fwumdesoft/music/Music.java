package com.fwumdesoft.music;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class Music 
{
	private double beatLength;
	private int instrument;// 0 is a piano, 9 is percussion, other channels are for other instruments
	public Music(int bpm, int instrument)
	{
		beatLength = (60.0/bpm);
		this.instrument = instrument;
	}
	
	public void playNote(Note n)
	{
	    int volume = 80; 
	    int duration = (int)(1000 * (beatLength/n.getLength())); // in milliseconds

	    try 
	    {
	        Synthesizer synth = MidiSystem.getSynthesizer();
	        synth.open();
	        MidiChannel[] channels = synth.getChannels();

	        channels[0].noteOn(n.getMidiNumber() , volume );
	        //channels[9].noteOn(60 , volume );
	        Thread.sleep( duration );
	        channels[instrument].noteOff(n.getMidiNumber());
	        //channels[9].noteOn(60 , volume );
	        synth.close();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public void playMeasure(Measure m)
	{
		for(int i = 0; i < m.getNotes().size(); i++)
		{
			playNote(m.getNotes().get(i));
		}
	}
	
	public void playSong(Song s)
	{
		while(true)
		{
			for(int i = 0; i < s.measureOrder.length; i++)
			{
				playMeasure(s.measures[s.measureOrder[i]]);
			}
		}
	}
}
