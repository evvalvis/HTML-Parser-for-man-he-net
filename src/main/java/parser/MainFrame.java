package main.java.parser;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class MainFrame extends JFrame {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private JFrame frame;
  private Container container;
  private JFXPanel jfxPanel;
  private JScrollPane scrollPane;

  public MainFrame(String desiredCommand, HTMLParser parser) {

    frame = new JFrame();
    frame.setTitle(desiredCommand);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 600);
    frame.setResizable(false);

    container = getContentPane();
    jfxPanel = new JFXPanel();
    scrollPane = new JScrollPane(jfxPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

    container.add(scrollPane);

    // Creation of scene and future interactions with JFXPanel
    Platform.runLater(() -> {
      WebView webView = new WebView();
      jfxPanel.setScene(new Scene(webView));
      webView.getEngine().load(parser.getMap().get(desiredCommand));
    });

    frame.add(container);
    frame.setVisible(true);
  }

}
