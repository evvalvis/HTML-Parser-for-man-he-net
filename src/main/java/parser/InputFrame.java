package main.java.parser;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class InputFrame extends JFrame {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private JFrame mainFrame;
  private Container mainContainer;
  private JLabel label;
  private JTextArea text;
  private JButton button;
  private Handler handler = new Handler();
  private HTMLParser parser = null;

  public InputFrame() {

    try {
      parser = new HTMLParser();
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    mainFrame = new JFrame();
    mainFrame.setTitle("Man he net html render");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(200, 200);
    mainFrame.setResizable(false);

    mainContainer = getContentPane();
    mainContainer.setLayout(new FlowLayout());
    label = new JLabel("Enter the desired unix command");
    text = new JTextArea();
    text.setColumns(10);
    button = new JButton("Search");

    mainContainer.add(label);
    mainContainer.add(text);
    mainContainer.add(button);

    button.addActionListener(handler);

    mainFrame.add(mainContainer);
    mainFrame.setVisible(true);
  }

  private class Handler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == button) {
        String desiredCommand = text.getText();
        System.out.println(desiredCommand);
        if (parser.getMap().containsKey(desiredCommand)) {
          new MainFrame(desiredCommand, parser);
        } else {
          JOptionPane.showMessageDialog(null, "Requested command could not be found!",
              "Command not found!", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  public static void main(String[] args) {
    new InputFrame();
  }
}
