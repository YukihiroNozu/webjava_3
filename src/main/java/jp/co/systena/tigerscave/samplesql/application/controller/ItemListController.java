package jp.co.systena.tigerscave.samplesql.application.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.systena.tigerscave.samplesql.application.model.character;
import jp.co.systena.tigerscave.samplesql.application.model.characterListForm;

@Controller // Viewあり。Viewを返却するアノテーション
public class ItemListController {

  @Autowired
  JdbcTemplate jdbcTemplate;


  /**
   * 初期表示用
   *
   * アイテムデータを取得して一覧表示する
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/characterlist", method = RequestMethod.GET) // URLとのマッピング
  public String index(Model model) {

    model.addAttribute("characters", getCharacterList());

    return "characterlist";
  }



  /**
   * 「更新」ボタン押下時の処理
   *
   * 入力された名前と価格をアイテムIDをキーとして更新する
   *
   * @param listForm
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/characterlist", method = RequestMethod.POST) // URLとのマッピング
  public String update(@Valid characterListForm listForm,
                        BindingResult result,
                        Model model) {

    // listFormに画面で入力したデータが入っているので取得する
    List<character> characterList = listForm.getItemList();
    // ビューに受け渡し用にmodelにセット
    model.addAttribute("characters", characterList);
    model.addAttribute("listForm", listForm);


    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {
      if (characterList != null) {
        //画面入力値1行ずつ処理をする
        for (character character : characterList) {

          //1行分の値でデータベースをUPDATEする
          //item_idをキーに名称と価格を更新する
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int updateCount = jdbcTemplate.update(
              "UPDATE items SET name = ?, hp = ?, job = ? WHERE id = ?",
              character.getName(),
              character.getJob(),
              Integer.parseInt(character.getHp()),
              Integer.parseInt(character.getId()));


        }
      }
    }

    return "characterlist";

  }

  /**
   * 「削除」リンク押下時の処理
   *
   * パラメータで受け取ったアイテムIDのデータを削除する
   *
   * @param itemId
   * @param model
   * @return
   */
  @RequestMapping(value = "/deleteCharacter", method = RequestMethod.GET) // URLとのマッピング
  public String update(@RequestParam(name = "id", required = true) String id,
      Model model) {


    // 本来はここで入力チェックなど


      // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    int deleteCount = jdbcTemplate.update("DELETE FROM items WHERE id = ?", Integer.parseInt(id));


    return "redirect:/characterlist";

  }



  /**
   * 「登録」ボタン押下時の処理
   *
   * 入力されたアイテムID、名前、価格をデータベースに登録する
   *
   * @param form
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/addCharacter", method = RequestMethod.POST) // URLとのマッピング
  public String insert(@Valid character form,
                        BindingResult result,
                        Model model) {

    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {

          //1行分の値をデータベースにINSERTする
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int insertCount = jdbcTemplate.update(
                "INSERT INTO items VALUES( ?, ?, ?, ? )",
                Integer.parseInt(form.getId()),
                form.getName(),
                Integer.parseInt(form.getHp()),
                form.getJob()
              );

    }

    return "redirect:/characterlist";

  }


  /**
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  private List<character> getCharacterList() {

    //SELECTを使用してテーブルの情報をすべて取得する
    List<character> list = jdbcTemplate.query("SELECT * FROM items ORDER BY id", new BeanPropertyRowMapper<character>(character.class));

    return list;

    /*
    //結果はMapのリストとして取得することもできる
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM items ORDER BY id");

    */


  }
}
