package jp.co.systena.tigerscave.samplesql.application.model;

import java.util.List;
import javax.validation.Valid;

public class characterListForm {

  @Valid
  private List<character> characterList;

  public List<character> getItemList() {
    return characterList;
  }

  public void setItemList(List<character> itemList) {
    this.characterList = itemList;
  }
}
