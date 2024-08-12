package co.edu.uptc.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Persistence {
  private String fileName;

  public Persistence(String fileName) {
    this.fileName = fileName;
  }

  public StringBuffer load() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    StringBuffer buffer = new StringBuffer();
    String line;
    boolean insideString = false;
    boolean insideMultiLineComment = false;

    while ((line = reader.readLine()) != null) {
      for (int i = 0; i < line.length(); i++) {
        char currentChar = line.charAt(i);

        if (currentChar == '\"') {
          insideString = !insideString;
        }

        if (!insideString && !insideMultiLineComment) {
          if (currentChar == '/' && i + 1 < line.length() && line.charAt(i + 1) == '*') {
            insideMultiLineComment = true;
            i++;
            continue;
          }
        }

        if (insideMultiLineComment) {
          if (currentChar == '*' && i + 1 < line.length() && line.charAt(i + 1) == '/') {
            insideMultiLineComment = false;
            i++;
            continue;
          }
          continue;
        } else {
          if (currentChar == '/' && i + 1 < line.length() && (line.charAt(i + 1) == '/' || line.charAt(i + 1) == '*')) {
            break;
          }
        }

        if (!insideString) {
          buffer.append(currentChar);
        }
      }

      buffer.append('\n');
    }

    reader.close();
    return buffer;
  }

}
