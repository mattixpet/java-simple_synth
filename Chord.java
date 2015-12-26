/*API:
void play - plays the chord
void playUp - plays the chord in arpeggios upwards
void PlayDown - plays the chord in arpeggios downwards
void playUpDown - plays 1 arpeggio of the chord
*/

public class Chord
{
    private final Note[] notes;
    private final double t; //default .5
    private double[] chord;

    public Chord(Note[] notes0, double t0)
    { 
	t = t0;

	chord = new double[1];

	notes = new Note[notes0.length];
	for (int i = 0; i < notes.length; i++) {
	    notes[i] = notes0[i];
	}
	switch (notes.length) {

	    case 0: { }; break;
	    case 1: { //notes[0].play(t);
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
	    } break;
	    case 2: {// double[] chord;
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
		double[] note2 = NoteArray(notes[1].getWaveform(),notes[1].getHz(),t);

		chord = Audio.sum(note1,note2,.5,.5);
		//	StdAudio.play(chord);
	    } break;
	    case 3: {// double[] chord;
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
		double[] note2 = NoteArray(notes[1].getWaveform(),notes[1].getHz(),t);
		double[] note3 = NoteArray(notes[2].getWaveform(),notes[2].getHz(),t);

		chord = Audio.sum(Audio.sum(note1,note2,.5,.5),note3,.67,.33);
		//StdAudio.play(chord);
	    } break;
	    case 4: {// double[] chord;   
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
		double[] note2 = NoteArray(notes[1].getWaveform(),notes[1].getHz(),t);
		double[] note3 = NoteArray(notes[2].getWaveform(),notes[2].getHz(),t);
		double[] note4 = NoteArray(notes[3].getWaveform(),notes[3].getHz(),t);
		
		chord = Audio.sum(Audio.sum(note1,note2,.5,.5),Audio.sum(note3,note4,.5,.5),.5,.5);
		//StdAudio.play(chord); 
	    } break;
	    case 5: {// double[] chord;
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
		double[] note2 = NoteArray(notes[1].getWaveform(),notes[1].getHz(),t);
		double[] note3 = NoteArray(notes[2].getWaveform(),notes[2].getHz(),t);
		double[] note4 = NoteArray(notes[3].getWaveform(),notes[3].getHz(),t);
		double[] note5 = NoteArray(notes[4].getWaveform(),notes[4].getHz(),t);
		
		chord = Audio.sum(
		        Audio.sum(note1,note2,.5,.5),
                        Audio.sum(Audio.sum(note3,note4,.5,.5),note5,.67,.33),
			.4,.6); 
	    } break;
	    case 6: {
		double[] note1 = NoteArray(notes[0].getWaveform(),notes[0].getHz(),t);
		double[] note2 = NoteArray(notes[1].getWaveform(),notes[1].getHz(),t);
		double[] note3 = NoteArray(notes[2].getWaveform(),notes[2].getHz(),t);
		double[] note4 = NoteArray(notes[3].getWaveform(),notes[3].getHz(),t);
		double[] note5 = NoteArray(notes[4].getWaveform(),notes[4].getHz(),t);
		double[] note6 = NoteArray(notes[5].getWaveform(),notes[5].getHz(),t);
		
		chord = Audio.sum(
			Audio.sum(Audio.sum(note1,note2,.5,.5),note3,.67,.33),
			Audio.sum(Audio.sum(note4,note5,.5,.5),note6,.67,.33),
			.5,.5);
	    } break;
	}
    }

    public void play() {
	StdAudio.play(chord); 
    }

    public void playUp() {
	for (int i = 0; i < notes.length; i++) {
	    StdAudio.play(NoteArray(notes[i].getWaveform(),notes[i].getHz(),t));
	}
    }

    public void playDown() {
	for (int i = notes.length-1; i >= 0; i--) {
	    StdAudio.play(NoteArray(notes[i].getWaveform(),notes[i].getHz(),t));
	}
    }

    public void playUpDown() {
	int cnt = 1;
	for (int i = 0; i < notes.length*2-2; i++) {
	    if (i <= notes.length-1) {
		StdAudio.play(NoteArray(notes[i].getWaveform(),notes[i].getHz(),t));
	    }
	    else if (i > notes.length-1) {
		StdAudio.play(NoteArray(notes[i-(-notes.length+1+(i+cnt))].getWaveform(),
                                        notes[i-(-notes.length+1+(i+cnt++))].getHz(),t));
	    }
	}
    }

    public static double[] NoteArray(String waveform, double hz, double t) {
	double[] note;
	if (waveform.equals("Sine")) {
	    note = Audio.tone(hz,t); }
	else if (waveform.equals("Square")) {
	    note = Audio.square(hz,t); }
	else if (waveform.equals("Sawtooth")) {
	    note = Audio.sawtooth(hz,t); }
	else if (waveform.equals("Triangle")) {
	    note = Audio.triangle(hz,t); }
	else note = new double[1];

	return note;
    }

    public String toString() 
    {
	String chord = "";
	for (int i = 0; i < notes.length; i++) {
	    chord += notes[i] + " ";
	}
	return chord;
    }

    public static void main(String[] args) {

	int n = Integer.parseInt(args[0]);
	double t = Double.parseDouble(args[1]);
	String[] bla = new String[n*2];
	for (int i = 0; i < bla.length; i++) {
	    bla[i] = StdIn.readString();
	}

	Note[] chordnotes = new Note[n];
	for (int i = 0; i < chordnotes.length; i++) {
	    chordnotes[i] = new Note(bla[i],bla[i+n]);
	}
	
	Chord Cmajor = new Chord(chordnotes,t);
	
	while ("lol" != "wtf") {
	       Cmajor.playUp(); 
	       Cmajor.playDown();
	    Cmajor.play();
	    Cmajor.playUpDown();
	}
    }

}