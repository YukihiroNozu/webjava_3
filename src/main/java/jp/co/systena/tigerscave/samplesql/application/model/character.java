package jp.co.systena.tigerscave.samplesql.application.model;

import javax.validation.constraints.Pattern;

public class character {

  @Pattern(regexp="^[0-9]*$")
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
  public String getHp() {
    return hp;
  }
  public void setHp(String hp) {
    this.hp = hp;
  }
  public String getJob() {
    return job;
  }
  public void setJob(String job) {
    this.job = job;
  }

  private String name;
  @Pattern(regexp="^[0-9]*$")
  private String hp;

  private String job;
}
