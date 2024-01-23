import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * 純粋関数のルール
 * 1. 関数の戻り値は常に１つだけ。
 * 2. 関数はその引数にのみ基づいて戻り値を計算する。
 * 3. 関数は既存の値を変更しない。
 ***********************************************************/

/*
import java.util.ArrayList;
import java.util.List;

class TipCalculator {
    private List<String> names = new ArrayList<>();
    private int tipPercentage = 0;

    // addPerson() は値を返さないが、純粋関数は常に１つの値を返す
    public void addPerson(String name) {
        // 既存の値（names と tipPercentage）を変更している
        // 純粋関数は新しい値を作成できるだけであり、値を変更してはならない
        names.add(name);
        if (names.size() > 5) {
            tipPercentage = 20;
        } else if (names.size() > 0) {
            tipPercentage = 10;
        }
    }

    public List<String> getNames() {
        // 外部状態（names 変数）に基づいて戻り値を計算している
        // 純粋関数は引数だけを使って戻り値を計算しなければならない
        return names;
    }

    public int getTipPercentage() {
        // 外部状態（tipPercentage 変数）に基づいて戻り値を計算している
        // 純粋関数は引数だけを使って戻り値を計算しなければならない
        return tipPercentage;
    }
}
*/
// ↓

class TipCalculator {
    // addPerson() は既存の値を変更しないため、純粋関数になった
    // ただし、ArrayList の add メソッドのラッパーにすぎないため、
    // これも削除してしまうことができる
    public static List<String> addPerson(List<String> names, String name) {
        List<String> updated = new ArrayList<>(names);
        updated.add(name);
        return updated;
    }

    public static int getTipPercentage(List<String> names) {
        if (names.size() > 5) {
            return 20;
        } else if (names.size() > 0) {
            return 10;
        } else {
            return 0;
        }
    }
}