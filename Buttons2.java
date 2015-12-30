import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Buttons2 extends JPanel {
  //private Thread lykkja;

  private int count;

  private volatile boolean stop;
  private JButton stopLoop;

  private Chord[] loopchords;

  private JButton pushC;
  private JButton pushCis;
  private JButton pushD;
  private JButton pushDis;
  private JButton pushE;
  private JButton pushF;
  private JButton pushFis;
  private JButton pushG;
  private JButton pushGis;
  private JButton pushA;
  private JButton pushAis;
  private JButton pushB;

  private JTextField Notes;
  private JLabel NotesTyped;

  private double t; //default: 0.5
  private JTextField time;
  private JLabel timeLabel;

  private String type;
  private JLabel PlayType;
  private JButton Regular;
  private JButton Up;
  private JButton Down;
  private JButton UpDown;

  private String waveform;
  private JLabel WaveformType;
  private JButton Sine;
  private JButton Square;
  private JButton Sawtooth;

  private Note C; //default 0.5s sine
  private Note Cis;
  private Note D;
  private Note Dis;
  private Note E;
  private Note F;
  private Note Fis;
  private Note G;
  private Note Gis;
  private Note A;
  private Note Ais;
  private Note B;

  private JButton EndChord;

  private JButton LoopChords;

  public Buttons2() {
    loopchords = new Chord[1];

    stop = false;
    stopLoop = new JButton("Stop looping");
    stopLoop.addActionListener(new stopLoopListener());

    LoopChords = new JButton("Loop your chords");
    LoopChords.addActionListener(new LoopChordsListener());

    EndChord = new JButton("End Chord");
    EndChord.addActionListener(new EndChordListener());

    C = new Note("C", "Sine");
    Cis = new Note("Cis", "Sine");
    D = new Note("D", "Sine");
    Dis = new Note("Dis", "Sine");
    E = new Note("E", "Sine");
    F = new Note("F", "Sine");
    Fis = new Note("Fis", "Sine");
    G = new Note("G", "Sine");
    Gis = new Note("Gis", "Sine");
    A = new Note("A", "Sine");
    Ais = new Note("Ais", "Sine");
    B = new Note("B", "Sine");

    waveform = "Sine";
    WaveformType = new JLabel("Current waveform: Sine");
    Sine = new JButton("  Sine  ");
    Sine.addActionListener(new SineListener());
    Square = new JButton(" Square ");
    Square.addActionListener(new SquareListener());
    Sawtooth = new JButton("Sawtooth");
    Sawtooth.addActionListener(new SawtoothListener());

    type = "Regular";
    PlayType = new JLabel("Current playtype: Regular");
    Regular = new JButton(" Regular ");
    Regular.addActionListener(new RegularListener());
    Up = new JButton("   Up    ");
    Up.addActionListener(new UpListener());
    Down = new JButton("  Down   ");
    Down.addActionListener(new DownListener());
    UpDown = new JButton("Up & Down");
    UpDown.addActionListener(new UpDownListener());

    t = 0.5;
    time = new JTextField(5);
    timeLabel = new JLabel("Playtime of notes/chords: ");
    time.addActionListener(new timeListener());
    time.setText(Double.toString(t));

    pushC = new JButton("  C  ");
    pushC.addActionListener(new ButtonCListener());

    pushCis = new JButton("  C#/D♭  ");
    pushCis.addActionListener(new ButtonCisListener());

    pushD = new JButton("  D  ");
    pushD.addActionListener(new ButtonDListener());

    pushDis = new JButton("  D#/E♭  ");
    pushDis.addActionListener(new ButtonDisListener());

    pushE = new JButton("  E  ");
    pushE.addActionListener(new ButtonEListener());

    pushF = new JButton("  F  ");
    pushF.addActionListener(new ButtonFListener());

    pushFis = new JButton("  F#/G♭  ");
    pushFis.addActionListener(new ButtonFisListener());

    pushG = new JButton("  G  ");
    pushG.addActionListener(new ButtonGListener());

    pushGis = new JButton("  G#/A♭  ");
    pushGis.addActionListener(new ButtonGisListener());

    pushA = new JButton("  A  ");
    pushA.addActionListener(new ButtonAListener());

    pushAis = new JButton("  A#/B♭  ");
    pushAis.addActionListener(new ButtonAisListener());

    pushB = new JButton("  B  ");
    pushB.addActionListener(new ButtonBListener());

    Notes = new JTextField(10);
    Notes.addActionListener(new TempListener());

    NotesTyped = new JLabel("Notes typed: " + count);

    add(pushC);
    add(pushCis);
    add(pushD);
    add(pushDis);
    add(pushE);
    add(pushF);
    add(pushFis);
    add(pushG);
    add(pushGis);
    add(pushA);
    add(pushAis);
    add(pushB);

    add(EndChord);

    add(NotesTyped);
    add(Notes);

    add(timeLabel);
    add(time);

    add(PlayType);
    add(Regular);
    add(Up);
    add(Down);
    add(UpDown);

    add(WaveformType);
    add(Sine);
    add(Square);
    add(Sawtooth);

    add(LoopChords);
    add(stopLoop);

    setPreferredSize(new Dimension(1000, 400));
    setBackground(Color.red);
  }

  private class stopLoopListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      stop = true;
    }
  }

  private class LoopChordsListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      SwingWorker s = new SwingWorker < Void, Void > () {

        @Override
        public Void doInBackground() {

          String text = Notes.getText();
          String[] letters = text.split("\\s+");
          int chordCnt = 0;
          int incr = 0;

          for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals("e")) {
              chordCnt++;
            }
          }

          int[] increments = new int[chordCnt];
          int[] eposition = new int[chordCnt];

          for (int i = 0; i < letters.length; i++) {
            if (letters[i].equals("e")) {
              eposition[incr] = i + 1;
              incr++;
            } else {
              increments[incr]++;
            }
          }

          Chord[] hljomar;
          if (chordCnt == 0) {
            hljomar = new Chord[1];
          } else if (chordCnt == 1) {
            Note[] chord1 = new Note[increments[0]];
            Chord Chord1 = SetjaNotur(chord1, letters, 0);
            hljomar = new Chord[1];
            hljomar[0] = Chord1;
          } else if (chordCnt == 2) {
            Note[] chord1 = new Note[increments[0]];
            Chord Chord1 = SetjaNotur(chord1, letters, 0);
            Note[] chord2 = new Note[increments[1]];
            Chord Chord2 = SetjaNotur(chord2, letters, eposition[0]);
            hljomar = new Chord[2];
            hljomar[0] = Chord1;
            hljomar[1] = Chord2;
          } else if (chordCnt == 3) {
            Note[] chord1 = new Note[increments[0]];
            Chord Chord1 = SetjaNotur(chord1, letters, 0);
            Note[] chord2 = new Note[increments[1]];
            Chord Chord2 = SetjaNotur(chord2, letters, eposition[0]);
            Note[] chord3 = new Note[increments[2]];
            Chord Chord3 = SetjaNotur(chord3, letters, eposition[1]);
            hljomar = new Chord[3];
            hljomar[0] = Chord1;
            hljomar[1] = Chord2;
            hljomar[2] = Chord3;
          } else if (chordCnt == 4) {
            Note[] chord1 = new Note[increments[0]];
            Chord Chord1 = SetjaNotur(chord1, letters, 0);
            Note[] chord2 = new Note[increments[1]];
            Chord Chord2 = SetjaNotur(chord2, letters, eposition[0]);
            Note[] chord3 = new Note[increments[2]];
            Chord Chord3 = SetjaNotur(chord3, letters, eposition[1]);
            Note[] chord4 = new Note[increments[3]];
            Chord Chord4 = SetjaNotur(chord4, letters, eposition[2]);
            hljomar = new Chord[4];
            hljomar[0] = Chord1;
            hljomar[1] = Chord2;
            hljomar[2] = Chord3;
            hljomar[3] = Chord4;
          } else {
            hljomar = new Chord[1];
          }

          loop(hljomar);

          return null;
        }
      };

      s.execute();

    }
  }

  //setur rettar notur i hljominn sem thad faer
  //ath i augnablikinu er max 6 notur i hljomi
  public Chord SetjaNotur(Note[] chord, String[] letters, int eposition) {

    // fix so that we definitely get the time, seemed like it didn't work correctly
    String curTime = time.getText();
    double curT = Double.parseDouble(curTime);
    
    for (int i = eposition; i < letters.length; i++) {
      if (letters[i].equals("e")) {
        break;
      }

      if (letters[i].equals("C")) {
        C = new Note("C", waveform);
        chord[i - eposition] = C;
      } else if (letters[i].equals("Cis")) {
        Cis = new Note("Cis", waveform);
        chord[i - eposition] = Cis;
      } else if (letters[i].equals("D")) {
        D = new Note("D", waveform);
        chord[i - eposition] = D;
      } else if (letters[i].equals("Dis")) {
        Dis = new Note("Dis", waveform);
        chord[i - eposition] = Dis;
      } else if (letters[i].equals("E")) {
        E = new Note("E", waveform);
        chord[i - eposition] = E;
      } else if (letters[i].equals("F")) {
        F = new Note("F", waveform);
        chord[i - eposition] = F;
      } else if (letters[i].equals("Fis")) {
        Fis = new Note("Fis", waveform);
        chord[i - eposition] = Fis;
      } else if (letters[i].equals("G")) {
        G = new Note("G", waveform);
        chord[i - eposition] = G;
      } else if (letters[i].equals("Gis")) {
        Gis = new Note("Gis", waveform);
        chord[i - eposition] = Gis;
      } else if (letters[i].equals("A")) {
        A = new Note("A", waveform);
        chord[i - eposition] = A;
      } else if (letters[i].equals("Ais")) {
        Ais = new Note("Ais", waveform);
        chord[i - eposition] = Ais;
      } else if (letters[i].equals("B")) {
        B = new Note("B", waveform);
        chord[i - eposition] = B;
      }
    }
    Chord x = new Chord(chord, curT);
    return x;
  }

  //loopar med thraedi
  public void loop(Chord[] hljomar) {
    loopchords = hljomar;
    Thread lykkja = new Thread(new Adder());
    Thread stans = new Thread(new Stopper());
    lykkja.start();
    stans.start();
    try {
      lykkja.join();
      stans.join();
    } catch (InterruptedException e) {}
  }

  public synchronized void stans() {
    while (true) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System. in ));
      boolean is = false;
      String string = null;
      try {
        string = br.readLine();
      } catch (IOException ioe) {}
      if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("1")) {
        is = true;
      }

      if (is) {
        stop = true;
      } else {
        stop = false;
      }
    }
  }

  private class Stopper implements Runnable {
    public void run() {
      stans();
    }
  }

  public synchronized void loop() {

    //Thread thisThread = Thread.currentThread();

    if (type.equals("Regular")) {
      while (!stop) {
        for (int i = 0; i < loopchords.length; i++) {
          loopchords[i].play();
        }
        /*try {
        this.sleep(5);
        } catch (InterruptedException ioe) {}*/
      }
    } else if (type.equals("Up")) {
      while (!stop) {
        for (int i = 0; i < loopchords.length; i++) {
          loopchords[i].playUp();
        }
      }
    } else if (type.equals("Down")) {
      while (!stop) {
        for (int i = 0; i < loopchords.length; i++) {
          loopchords[i].playDown();
        }
      }
    } else {
      while (!stop) {
        for (int i = 0; i < loopchords.length; i++) {
          loopchords[i].playUpDown();
        }
      }
    }
  }

  private class Adder implements Runnable {
    public void run() {
      loop();
    }
  }

  private class EndChordListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      String text = Notes.getText();
      Notes.setText(text + "e ");
    }
  }

  private class SineListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      waveform = "Sine";
      WaveformType.setText("Current waveform: Sine");
    }
  }

  private class SquareListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      waveform = "Square";
      WaveformType.setText("Current waveform: Square");
    }
  }

  private class SawtoothListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      waveform = "Sawtooth";
      WaveformType.setText("Current waveform: Sawtooth");
    }
  }

  private class RegularListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      type = "Regular";
      PlayType.setText("Current playtype: Regular");
    }
  }

  private class UpListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      type = "Up";
      PlayType.setText("Current playtype: Up");
    }
  }

  private class DownListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      type = "Down";
      PlayType.setText("Current playtype: Down");
    }
  }

  private class UpDownListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      type = "UpDown";
      PlayType.setText("Current playtype: Up & Down");
    }
  }

  private class timeListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      String text = time.getText();
      t = Double.parseDouble(text);
      time.setText(Double.toString(t));
    }
  }

  private class TempListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {

    }
  }

  private class ButtonCListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      C = new Note("C", waveform);
      C.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "C ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonCisListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Cis = new Note("Cis", waveform);
      Cis.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "Cis ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonDListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      D = new Note("D", waveform);
      D.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "D ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonDisListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Dis = new Note("Dis", waveform);
      Dis.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "Dis ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonEListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      E = new Note("E", waveform);
      E.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "E ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonFListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      F = new Note("F", waveform);
      F.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "F ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonFisListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Fis = new Note("Fis", waveform);
      Fis.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "Fis ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonGListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      G = new Note("G", waveform);
      G.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "G ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonGisListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Gis = new Note("Gis", waveform);
      Gis.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "Gis ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonAListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      A = new Note("A", waveform);
      A.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "A ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonAisListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Ais = new Note("Ais", waveform);
      Ais.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "Ais ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }

  private class ButtonBListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      B = new Note("B", waveform);
      B.play(0.5);

      String text = Notes.getText();
      Notes.setText(text + "B ");

      count++;
      NotesTyped.setText("Notes typed: " + count);
    }
  }
}