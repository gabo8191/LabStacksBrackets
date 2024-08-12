package model;

public class Student {
  private int code;
  private String name;
  private int[] grades;

  public Student(int code, String name) {
    this.code = code;
    this.name = name;
    this.grades = new int[3];
  }

  /*
   * deberia ignorar
   * esto ((
   * ]
   */

  /*
   * p
   */

  // { {

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}[

  @Override
  public String toString() {
    return "Student [code=" + code + ", name=" + name + "}";
  }
}
