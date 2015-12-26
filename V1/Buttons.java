import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Buttons extends JPanel
{
    private int count;

    private double[] C;
    private JButton pushC;
    private double[] Cis;
    private JButton pushCis;
    private double[] D;
    private JButton pushD;
    private double[] Dis;
    private JButton pushDis;
    private double[] E;
    private JButton pushE;
    private double[] F;
    private JButton pushF;
    private double[] Fis;
    private JButton pushFis;
    private double[] G;
    private JButton pushG;
    private double[] Gis;
    private JButton pushGis;
    private double[] A;
    private JButton pushA;
    private double[] Ais;
    private JButton pushAis;
    private double[] B;
    private JButton pushB;

    public Buttons ()
    {
	count = 0;
	C = Audio.tone(Audio.freq("C"), 1);
	Cis = Audio.tone(Audio.freq("Cis"), 1);
	D = Audio.tone(Audio.freq("D"), 1);
	Dis = Audio.tone(Audio.freq("Dis"), 1);
	E = Audio.tone(Audio.freq("E"), 1);
	F = Audio.tone(Audio.freq("F"), 1);
	Fis = Audio.tone(Audio.freq("Fis"), 1);
	G = Audio.tone(Audio.freq("G"), 1);
	Gis = Audio.tone(Audio.freq("Gis"), 1);
	A = Audio.tone(Audio.freq("A"), 1);
	Ais = Audio.tone(Audio.freq("Ais"), 1);
	B = Audio.tone(Audio.freq("B"), 1);

	pushC = new JButton ("  C  ");
	pushC.addActionListener (new ButtonCListener());

	pushCis = new JButton ("  C#/D♭  ");
	pushCis.addActionListener (new ButtonCisListener());

	pushD = new JButton ("  D  ");
	pushD.addActionListener (new ButtonDListener());

	pushDis = new JButton ("  D#/E♭  ");
	pushDis.addActionListener (new ButtonDisListener());

	pushE = new JButton ("  E  ");
	pushE.addActionListener (new ButtonEListener());

	pushF = new JButton ("  F  ");
	pushCis.addActionListener (new ButtonCisListener());

	pushFis = new JButton ("  F#/G♭  ");
	pushFis.addActionListener (new ButtonFisListener());

	pushG = new JButton ("  G  ");
	pushG.addActionListener (new ButtonGListener());

	pushGis = new JButton ("  G#/A♭  ");
	pushGis.addActionListener (new ButtonGisListener());

	pushA = new JButton ("  A  ");
	pushA.addActionListener (new ButtonAListener());

	pushAis = new JButton ("  A#/B♭  ");
	pushAis.addActionListener (new ButtonAisListener());

	pushB = new JButton ("  B  ");
	pushB.addActionListener (new ButtonBListener());

	add (pushC);
	add (pushCis);
	add (pushD);
	add (pushDis);
	add (pushE);
	add (pushF);
	add (pushFis);
	add (pushG);
	add (pushGis);
	add (pushA);
	add (pushAis);
	add (pushB);

	setPreferredSize (new Dimension(300, 40));
	setBackground (Color.red);
    }

       private class ButtonCListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(C);
	       count++;
	   }
       }

       private class ButtonCisListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(Cis);
	       count++;
	   }
       }

       private class ButtonDListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(D);
	       count++;
	   }
       }

       private class ButtonDisListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(Dis);
	       count++;
	   }
       }

       private class ButtonEListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(E);
	       count++;
	   }
       }

       private class ButtonFListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(F);
	       count++;
	   }
       }

       private class ButtonFisListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(Fis);
	       count++;
	   }
       }

       private class ButtonGListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(G);
	       count++;
	   }
       }

       private class ButtonGisListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(Gis);
	       count++;
	   }
       }

       private class ButtonAListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(A);
	       count++;
	   }
       }

       private class ButtonAisListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(Ais);
	       count++;
	   }
       }

       private class ButtonBListener implements ActionListener
       {

	   public void actionPerformed (ActionEvent event)
	   {
	       StdAudio.play(B);
	       count++;
	   }
       }
}