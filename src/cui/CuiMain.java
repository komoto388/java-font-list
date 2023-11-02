package cui;

import java.util.List;

import javafx.scene.text.Font;

/**
 * コマンドプロンプトにフォント一覧を表示するクラス
 */
public class CuiMain {

    /**
     * フォント一覧を表示する
     * @param args プログラム実行時の引数
     */
    public static void main(String[] args) {
        // フォント一覧を取得する
        List<String> fontFamilyList = Font.getFamilies();
        
        // フォント一覧を表示する
        System.out.println("***** フォントファミリー一覧 *****");
        
        for(int i = 0; i < fontFamilyList.size(); i++) {
            String font = fontFamilyList.get(i);
            System.out.printf("%3d. %s\n", i + 1, font);
        }
    }
}
