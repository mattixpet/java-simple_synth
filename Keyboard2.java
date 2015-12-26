import javax.swing.JFrame;

public class Keyboard2
{

    public static void main (String[] args)
    {
      JFrame frame = new JFrame ("Matti's keyboard v2");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new Buttons2());

      frame.pack();
      frame.setVisible(true);
    }
}