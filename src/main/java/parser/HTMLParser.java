package main.java.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Evangelos Valvis
 */
public class HTMLParser {

  private HashMap<String, String> commands;
  private final String source;

  public HTMLParser() throws IOException {
    commands = new HashMap<String, String>();
    source = "http://man.he.net";
    loadCommands();
  }

  /**
   * Loads the available unix commands from http://man.he.net and initializes a Hash map with each
   * command and its link
   *
   * @throws IOException
   */
  private void loadCommands() throws IOException {
    Document doc = Jsoup.connect("http://man.he.net/man1").get();
    Elements links = doc.select("a[href]");

    for (Element link : links) {
      String command;
      String commandLink;
      StringTokenizer tokenizer = new StringTokenizer(link.toString(), "<>", false);
      tokenizer.nextToken();
      command = tokenizer.nextToken();
      tokenizer = new StringTokenizer(link.toString(), "\"", false);
      tokenizer.nextToken();
      commandLink = source + tokenizer.nextToken();
      commands.put(command, commandLink);
    }
  }

  public HashMap<String, String> getMap() {
    return this.commands;
  }
}
