import javax.swing.JFrame;

public class Keyboard
{

    public static void main (String[] args)
    {
	JFrame frame = new JFrame ("Matti's keyboard");
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	frame.getContentPane().add(new Buttons());

	frame.pack();
	frame.setVisible(true);
    }
}