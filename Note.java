public class Note {

    private final String note;
    private final String waveform;
    private final double hz;
    private double[] NoteArray;

    public Note(String note0, String waveform0) 
    { note = note0; waveform = waveform0; hz = Audio.freq(note); }

    public void play(double t)
    {
	if (waveform.equals("Sine")) 
	    { NoteArray = Audio.tone(hz,t); }
	else if (waveform.equals("Square")) 
	    { NoteArray = Audio.square(hz,t); }
	else if (waveform.equals("Sawtooth"))
	    { NoteArray = Audio.sawtooth(hz,t); }
	else if (waveform.equals("Triangle"))
	    { NoteArray = Audio.triangle(hz,t); }
	else { NoteArray = new double[1]; } 

	StdAudio.play(NoteArray);
    }

    public String getWaveform() {
	return waveform;
    }

    public String getNote() {
	return note;
    }
	
	public double getHz() {
	return hz;
	}

    public String toString() 
    {       String wavef = waveform.substring(0,3);
	    return note + "(" + wavef + ")"; }




    public static void main(String[] args) {

	Note C = new Note("C","Square");
        C.play(1.0);
    }
}

