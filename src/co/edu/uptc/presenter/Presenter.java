package co.edu.uptc.presenter;

import co.edu.uptc.model.Bracket;
import co.edu.uptc.persistence.Persistence;
import co.edu.uptc.view.View;

public class Presenter {

  private static final String FILE_NAME = "data/test.java";
  private View view;
  private Persistence persistence;
  private Bracket bracket;
  private String text;

  public Presenter() {
    view = new View();
    bracket = new Bracket();
  }

  public void load() {
    try {
      persistence = new Persistence(FILE_NAME);
      StringBuffer buffer = persistence.load();
      text = buffer.toString();
    } catch (Exception e) {
      view.show(e.getMessage());
    }
  }

  public void isBalanced() {
    bracket.fillStacks(text);
    String check = bracket.checkBalance(text);
    view.show(check);
  }

  public static void main(String[] args) {
    Presenter presenter = new Presenter();
    presenter.load();
    presenter.isBalanced();
  }

}
