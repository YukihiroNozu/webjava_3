package jp.co.systena.tigerscave.samplesql.application.form;
public class CharacterParameter{

  /*
   * 名前
   */
  private String name;
  /*
   * 職業
   */
  private String job;
  /*
   * HP
   */
  private int hp = 100;
  /*
   * メッセージ
   */
  public String message;

  /*
   *以下、Getter,Setter
   */

  private String id;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void attack() {

  }

  public CharacterParameter() {

  }

  public CharacterParameter(String name, String job, int hp, String id) {
    this.name = name;
    this.job = job;
    this.hp = hp;
    this.id = id;
  }

}