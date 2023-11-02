package gui;

import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * フォント毎にサンプルの文字列をを表示する画面
 * @author komoto
 */
public class MainFrameController implements Initializable {

    /** フォント一覧を格納するリスト */
    private List<String> fontFamilyList;

    /** フォントリストの先頭から何番目のフォントを表示しているか表すインデックス番号 */
    private int fontIndex;

    /** 画面タイトルのラベル */
    @FXML
    private Label titleLabel;

    /** 表示中のフォント名を表示するテキストフィールド */
    @FXML
    private TextField fontFamilyTextField;

    /** リストの１つ前のフォントに切り替えるボタン */
    @FXML
    private Button previousButton;

    /** リストの１つ後のフォントに切り替えるボタン */
    @FXML
    private Button nextButton;

    /** 現在のフォントに従い、サンプルを表示するラベル1 */
    @FXML
    private Label sampleLabel1;

    /** 現在のフォントに従い、サンプルを表示するラベル2 */
    @FXML
    private Label sampleLabel2;

    /** 現在のフォントに従い、サンプルを表示するボタン */
    @FXML
    private Button sampleButton;

    /**
     * フォント一覧を取得し、最初のフォントを画面上に表示する
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fontFamilyList = Font.getFamilies();
        fontIndex = 0;
        update();
    }

    /**
     * 1つ前のフォントを表示する
     * @param event イベントのインスタンス 
     * @throws UnexpectedException {@code fontIndex} が0を下回る場合、エラーとする
     */
    @FXML
    void onPreviousButtonAction(ActionEvent event) throws UnexpectedException {
        if (fontIndex > 0) {
            fontIndex--;
        } else {
            throw new UnexpectedException("0以下の値は期待していません: " + fontIndex);
        }
        update();
    }

    /**
     * 1つ後のフォントを表示する
     * @param event イベントのインスタンス 
     * @throws UnexpectedException {@code fontIndex} がフォントリストの要素数を上回る場合、エラーとする
     */
    @FXML
    void onNextButtonAction(ActionEvent event) throws UnexpectedException {
        if (fontIndex < fontFamilyList.size() - 1) {
            fontIndex++;
        } else {
            throw new UnexpectedException("フォント一覧リストの要素数を超えています: " + fontIndex + ", List = " + fontFamilyList.size());
        }
        update();
    }

    /**
     * {@code fontIndex} が示すフォントに合わせて、画面上の文字のフォントを変更する。
     * ヘッド部分のフォントは変更しない。
     */
    private void update() {
        String font = fontFamilyList.get(fontIndex);
        fontFamilyTextField.setText(font);

        // リストの先頭にあるフォントを表示している場合、previousButtonを押せなくする
        if (fontIndex <= 0) {
            previousButton.setDisable(true);
        } else {
            previousButton.setDisable(false);
        }

        // リストの最後にあるフォントを表示している場合、nextButtonを押せなくする
        if (fontIndex >= fontFamilyList.size() - 1) {
            nextButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
        }

        String cssStyleString = String.format("-fx-font-family: %s; -fx-font-size: 16;", font);
        sampleLabel1.setStyle(cssStyleString);
        sampleLabel2.setStyle(cssStyleString);
        sampleButton.setStyle(cssStyleString);
    }
}
