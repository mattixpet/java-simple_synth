//Java forrit til ad spila tona og hljoma (mjog takmarkad)
//Matthias
//2011

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import java.nio.file.Paths;

public class Audio
{
    //samplar toninn (sinus)
    public static double[] tone(double hz, double t) {
	//sja fallid freq()
	if (hz == 666.0) {
	    String a = StdIn.readString();
	    String b = StdIn.readString();
	    String c = StdIn.readString();
	    return chord(a,b,c,t);
	}

	int sps = 44100;
	int N = (int) (sps * t);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
	    a[i] = Math.sin(2 * Math.PI * i * hz / sps);
	}
	return a;
    }

    //sawtooth bylgja
    public static double[] sawtooth(double hz, double t) {
	if (hz == 666.0) {
	    String a = StdIn.readString();
	    String b = StdIn.readString();
	    String c = StdIn.readString();
	    return sawchord(a,b,c,t);
	}

	int sps = 44100;
	int N = (int) (sps * t);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
	    a[i] = (2 * Math.PI * i * (hz/8.0) / sps) - (int) (2 * Math.PI * i * (hz/8.0) / sps);
	}
	return a;
    }

    //triangle bylgja (so far virkar ekki, er bara eins og saw)
    public static double[] triangle(double hz, double t) {
	if (hz == 666.0) {
	    String a = StdIn.readString();
	    String b = StdIn.readString();
	    String c = StdIn.readString();
	    return trichord(a,b,c,t);
	}

	int sps = 44100;
	int N = (int) (sps * t);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
	    a[i] = Math.abs((2 * Math.PI * i * (hz/8.0) / sps) - (int) (2 * Math.PI * i * (hz/8.0) / sps));
	}
	return a;
    }

    //Virkar, byr til square bylgju i stad sinus
    public static double[] square(double hz, double t) {
	if (hz == 666.0) {
	    String a = StdIn.readString();
	    String b = StdIn.readString();
	    String c = StdIn.readString();
	    return squarechord(a,b,c,t);
	}

	int sps = 44100;
	int N = (int) (sps * t);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
	    a[i] = (Math.sin(2 * Math.PI * i * hz / sps))/(Math.abs(Math.sin(2 * Math.PI * i * hz / sps)));
	}
	return a;
    }

    //Notkun: a = freq(x)
    //Fyrir: x er bokstafur sem taknar notu:
    //C, Cis/Db, D, Dis/Eb, E, F, Fis/Gb, G, Gis/Ab, A, Ais/Bb, B og t.d. Cis3 eda B6 (C = C4)
    //Eftir: a er tidnin sem taknar notuna
    public static double freq(String x) {

	//mid attund (nr 4)
	String[] a = { "C", "Cis", "Db", "D", "Dis", "Eb", "E", "F", "Fis", "Gb", "G", "Gis", "Ab", "A", "Ais", "Bb", "B" };
	double[] b = { 261.63, 277.18, 277.18, 293.66, 311.13, 311.13, 329.63, 349.23, 369.99, 369.99, 392.00, 415.30, 415.30, 440.00, 466.16, 466.16, 493.88 };

	//attund nr 3
	String[] a3 = { "C3", "Cis3", "Db3", "D3", "Dis3", "Eb3", "E3", "F3", "Fis3", "Gb3", "G3", "Gis3", "Ab3", "A3", "Ais3", "Bb3", "B3" };
	double[] b3 = new double[a3.length];
	for (int i = 0; i < b3.length; i++) {
	    b3[i] = b[i] / 2;
	}

	//attund nr 5
	String[] a5 = { "C5", "Cis5", "Db5", "D5", "Dis5", "Eb5", "E5", "F5", "Fis5", "Gb5", "G5", "Gis5", "Ab5", "A5", "Ais5", "Bb5", "B5" };
	double[] b5 = new double[a5.length];
	for (int i = 0; i < b5.length; i++) {
	    b5[i] = b[i] * 2;
	}

	//attund nr 2
	String[] a2 = { "C2", "Cis2", "Db2", "D2", "Dis2", "Eb2", "E2", "F2", "Fis2", "Gb2", "G2", "Gis2", "Ab2", "A2", "Ais2", "Bb2", "B2" };
	double[] b2 = new double[a2.length];
	for (int i = 0; i < b2.length; i++) {
	    b2[i] = b3[i] / 2;
	}

	//attund nr 6
	String[] a6 = { "C6", "Cis6", "Db6", "D6", "Dis6", "Eb6", "E6", "F6", "Fis6", "Gb6", "G6", "Gis6", "Ab6", "A6", "Ais6", "Bb6", "B6" };
	double[] b6 = new double[a6.length];
	for (int i = 0; i < b6.length; i++) {
	    b6[i] = b5[i] * 2;
	}
	    
	    //bara til minningar um thekkingarleysi:
	    /* if (x.length() == 1 && a[i].length() == 1) { 
		if (x.charAt(0) == a[i].charAt(0)) {
		return b[i]; }
	    }
	    else if (x.length() == 2 && a[i].length() == 2) {
		if (x.charAt(0) == a[i].charAt(0) && x.charAt(1) == a[i].charAt(1)) {
		    return b[i]; }
	    }
	    else if (x.length() == 3 && a[i].length() == 3) {
		if (x.charAt(0) == a[i].charAt(0) && x.charAt(1) == a[i].charAt(1) && x.charAt(2) == a[i].charAt(2)) {
		    return b[i]; }
	    }
	    else if (x.length() == 4 && a[i].length() == 4) {
		if (x.charAt(0) == a[i].charAt(0) && x.charAt(1) == a[i].charAt(1) && x.charAt(2) == a[i].charAt(2) && x.charAt(3) == a[i].charAt(3))
		return b[i]; }*/

	for (int i = 0; i < a.length; i++) {
	    if (x.equals("c")) { return 666.0; }
	    else if (x.equals("R")) { return 0.0; }
            else if (x.equals(a[i])) {
		return b[i];
	    }
	    else if (x.equals(a3[i])) {
		return b3[i];
	    }
	    else if (x.equals(a5[i])) {
		return b5[i];
	    }
	    else if (x.equals(a2[i])) {
		return b2[i];
	    }
	    else if (x.equals(a6[i])) {
		return b6[i];
	    }
	}
	return -1.0;
    }

    public static double[] sum(double[] a, double[] b, double awt, double bwt) {
	//superpose a and b, weigthed
	double[] c = new double[a.length];
	for ( int i = 0; i < a.length; i++) {
	    c[i] = a[i]*awt + b[i]*bwt; }
	    return c;
    }

    //tekur inn thrja strengi sem taknar hljom t.d. Cis E Gis og skilar fylki med tidni sem taknar hljominn 
    public static double[] chord(String x, String y, String z, double t) {

	double[] G = tone(freq(x),t);
	double[] G1 = tone(freq(y),t);
	double[] G2 = tone(freq(z),t);
	double[] h = sum(G1,G2,.5,.5);
	return sum(G,h,.33,.67);
    }

    public static double[] sawchord(String x, String y, String z, double t) {

	double[] G = sawtooth(freq(x),t);
	double[] G1 = sawtooth(freq(y),t);
	double[] G2 = sawtooth(freq(z),t);
	double[] h = sum(G1,G2,.5,.5);
	return sum(G,h,.33,.67);
    }

    public static double[] trichord(String x, String y, String z, double t) {

	double[] G = triangle(freq(x),t);
	double[] G1 = triangle(freq(y),t);
	double[] G2 = triangle(freq(z),t);
	double[] h = sum(G1,G2,.5,.5);
	return sum(G,h,.33,.67);
    }

    public static double[] squarechord(String x, String y, String z, double t) {

	double[] G = square(freq(x),t);
	double[] G1 = square(freq(y),t);
	double[] G2 = square(freq(z),t);
	double[] h = sum(G1,G2,.5,.5);
	return sum(G,h,.33,.67);
    }

    public static void loopchord(String note1, String note2, String note3, double t) {

	double[] userchord = chord(note1, note2, note3, t);

	while (1 > 0) {
	    StdAudio.play(userchord);
	}
    }

    public static void loopchord(String note1, String note2, String note3, String note4, String note5, String note6, String note7, String note8, String note9, String note10, String note11, String note12, double t) {

	double[] userchord1 = chord(note1, note2, note3, t);
	double[] userchord2 = chord(note4, note5, note6, t);
	double[] userchord3 = chord(note7, note8, note9, t);
	double[] userchord4 = chord(note10, note11, note12, t);

	String lol = "lol";
	while (lol != "wtf") {
	    StdAudio.play(userchord1);
	    StdAudio.play(userchord2);
	    StdAudio.play(userchord3);
	    StdAudio.play(userchord4);
	}
    }

 public static void loopsquarechord(String note1, String note2, String note3, String note4, String note5, String note6, String note7, String note8, String note9, String note10, String note11, String note12, double t) {

	double[] userchord1 = squarechord(note1, note2, note3, t);
	double[] userchord2 = squarechord(note4, note5, note6, t);
	double[] userchord3 = squarechord(note7, note8, note9, t);
	double[] userchord4 = squarechord(note10, note11, note12, t);

	while (true) {
	    StdAudio.play(userchord1);
	    StdAudio.play(userchord2);
	    StdAudio.play(userchord3);
	    StdAudio.play(userchord4);
	}
    }

    //teknar flott munstur i command prompt
    public static void flipp() {

	for (int i=1; i<=10000; i++)
        {
	    for (int j = 1; j<=10000; j++)
	    {
		if ((i % j == 0 || j % i == 0))
	        {
		System.out.print("* ");
		}
		else
		{ 
		System.out.print("  ");
		}
	    }
       
	}
    }
    //setur saman 4 fylki i 1
    public static double[] splice(double[] f, double[] a, double[] b, double[] c) {
	    double[] x = new double[(f.length+a.length+b.length+c.length)];
	    for (int i = 0; i < x.length; i++) {

		if (i < f.length) {
		    x[i] = f[i];
		}
		else if (i >= f.length && i < a.length+f.length) {
		    x[i] = a[i-f.length];
		}
		else if (i >= f.length+a.length && i < f.length+a.length+b.length) {
		    x[i] = b[i-(f.length+a.length)];
		}
		else {
		    x[i] = c[i-(f.length+a.length+b.length)];
		}
	    }
	    return x;
    }

    public static void main(String[] args) {
	    
	    StdOut.println("Note:    C-sharp is denoted by \"Cis\" and A-flat by \"Ab\" for example.\n");
	    StdOut.println("         C3 is one octave lower than C and C5 is one octave higher and\n");
	    StdOut.println("         so on and so forth.\n");
	    StdOut.println("         A rest is denoted by \"R\".\n");
	    StdOut.println("         If you want to play notes or chords(sine), press: n\n ");
	    StdOut.println("         If you want to play notes or chords(square), type: mega\n ");
	    StdOut.println("         If you want to play notes or chords(triangle), type: tri\n ");
	    StdOut.println("         If you want to play notes or chords(sawtooth), type: saw\n ");
	    StdOut.println("         If you want to loop a chord, press: l\n ");
	    StdOut.println("         If you want to loop 4 chords, press: x\n ");
	    StdOut.println("         If you want to loop 4 square chords, press: xmega\n ");
	    StdOut.println("         If you want to listen to a good song while you watch, press: z\n ");

	String answer = StdIn.readString();

	if (answer.equalsIgnoreCase("n")) {
	    StdOut.println("\nInsert notes please.\n");
	    StdOut.println("Example: Play C for 1 second is \"C 1\"\n");
	    StdOut.println("         To play a chord type \"c 1 C E G\" to play a C-major\n");
	    StdOut.println("         chord.\n");
	    
	    String note;
	    double t = 0;
	    while (!StdIn.isEmpty()) {
		note = StdIn.readString();
		t = StdIn.readDouble();
		
		double[] a = tone(freq(note), t);
		StdAudio.play(a);
	    }
	}
	else if (answer.equalsIgnoreCase("mega")) {
	    StdOut.println("\nInsert notes please.\n");
	    StdOut.println("Example: Play C for 1 second is \"C 1\"\n");
	    StdOut.println("         To play a chord type \"c 1 C E G\" to play a C-major\n");
	    StdOut.println("         chord.\n");
 
	    String note;
	    double t = 0;
	    while (!StdIn.isEmpty()) {
		note = StdIn.readString();
		t = StdIn.readDouble();
		
		double[] a = square(freq(note), t);
		StdAudio.play(a);
	    }
	}
       	else if (answer.equalsIgnoreCase("tri")) {
	    StdOut.println("\nInsert notes please.\n");
	    StdOut.println("Example: Play C for 1 second is \"C 1\"\n");
	    StdOut.println("         To play a chord type \"c 1 C E G\" to play a C-major\n");
	    StdOut.println("         chord.\n");
	    
	    String note;
	    double t = 0;
	    while (!StdIn.isEmpty()) {
		note = StdIn.readString();
		t = StdIn.readDouble();
		
		double[] a = triangle(freq(note), t);
		StdAudio.play(a);
	    }
	}
	else if (answer.equalsIgnoreCase("saw")) {
	    StdOut.println("\nInsert notes please.\n");
	    StdOut.println("Example: Play C for 1 second is \"C 1\"\n");
	    StdOut.println("         To play a chord type \"c 1 C E G\" to play a C-major\n");
	    StdOut.println("         chord.\n");
	    
	    String note;
	    double t = 0;
	    while (!StdIn.isEmpty()) {
		note = StdIn.readString();
		t = StdIn.readDouble();
		
		double[] a = sawtooth(freq(note), t);
		StdAudio.play(a);
	    }
	    }
	else if (answer.equalsIgnoreCase("l")) {
	    StdOut.println("         To play a chord type \"C E G 1\" to loop a C-major\n");
	    StdOut.println("         chord played for 1 second at a time.\n");
	    
	    String note1 = StdIn.readString();
	    String note2 = StdIn.readString();
	    String note3 = StdIn.readString();
	    double t = StdIn.readDouble();

	    loopchord(note1, note2, note3, t);
        }
	else if (answer.equalsIgnoreCase("x")) {
	    StdOut.println("         To loop the chords type \"C E G F A C5 G B D5 E G C5 1\" to loop a C-major,\n");
	    StdOut.println("         F-major, G major, and a C-major chord played for 1 second each.\n");

	    String note1 = StdIn.readString();
	    String note2 = StdIn.readString();
	    String note3 = StdIn.readString();
	    String note4 = StdIn.readString();
	    String note5 = StdIn.readString();
	    String note6 = StdIn.readString();
	    String note7 = StdIn.readString();
	    String note8 = StdIn.readString();
	    String note9 = StdIn.readString();
	    String note10 = StdIn.readString();
	    String note11 = StdIn.readString();
	    String note12 = StdIn.readString();
	    double t = StdIn.readDouble();

	    loopchord(note1, note2, note3, note4, note5, note6, note7, note8, note9, note10, note11, note12, t);
	}
else if (answer.equalsIgnoreCase("xmega")) {
	    StdOut.println("         To loop the chords type \"C E G F A C5 G B D5 E G C5 1\" to loop a C-major,\n");
	    StdOut.println("         F-major, G major, and a C-major chord played for 1 second each.\n");

	    String note1 = StdIn.readString();
	    String note2 = StdIn.readString();
	    String note3 = StdIn.readString();
	    String note4 = StdIn.readString();
	    String note5 = StdIn.readString();
	    String note6 = StdIn.readString();
	    String note7 = StdIn.readString();
	    String note8 = StdIn.readString();
	    String note9 = StdIn.readString();
	    String note10 = StdIn.readString();
	    String note11 = StdIn.readString();
	    String note12 = StdIn.readString();
	    double t = StdIn.readDouble();

	    loopsquarechord(note1, note2, note3, note4, note5, note6, note7, note8, note9, note10, note11, note12, t);
	}
	else if (answer.equalsIgnoreCase("z")) {

	    /*AePlayWave aw = new AePlayWave("TogFeBachiDm.wav");
	    aw.start();*/
	    JFXPanel fxPanel = new JFXPanel(); // hack to get hidden initialization of javafx lib
			Media m = new Media(Paths.get("TogFeBachiDm.mp3").toUri().toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.play();

	    flipp();
	}
	//main test bara
	else if (answer.equalsIgnoreCase("test")) {
	    maintest();
	}
        else StdOut.println("Wrong entry, bye bye.");
    }

    public static void maintest() {
	
	StdOut.println("\nTest.");
	    String note;
	    String note1;
	    String note2;
	    double t = 0.0;
	    double t1 = 0.0;
	    double t2 = 0.0;


	    String note0;
	    double t0 = 0.0;
	    note0 = StdIn.readString();
	    t0 = StdIn.readDouble();

	    double[] f = triangle(freq(note0), t0);	 



		note = StdIn.readString();
		t = StdIn.readDouble();
		
		double[] a = triangle(freq(note), t);

		note1 = StdIn.readString();
		t1 = StdIn.readDouble();
		
		double[] b = triangle(freq(note1), t1);

		note2 = StdIn.readString();
		t2 = StdIn.readDouble();
		
		double[] c = triangle(freq(note2), t2);
	    
	    
		double[] x = splice(f,a,b,c);

		StdAudio.play(x);
		StdAudio.save("Tri.wav", x);
    }
}