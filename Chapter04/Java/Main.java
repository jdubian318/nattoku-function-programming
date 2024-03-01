//import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.Function;

/******************************************************************************
 * 単語のランク付けの要件
 * 
 * 単語のスコアは 'a' 以外の文字ごとに1ポイントを与えるという方法で計算する。
 * 単語のリストが与えられたら、スコアの高い順に単語を並べ替えたリストを返す。
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * 単語に 'c' が含まれている場合は、５ポイントのボーナススコアを加算する必要がある。
 * 古いスコアリング方式（ボーナス無し）も引き続きサポートする必要がある。
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * 単語に 's' が含まれている場合は、７ポイントのペナルティスコアをスコアから差し引く必要がる。
 * （ボーナスの有無に関わらず）古いスコアリング方法も引き続きサポートする必要がある。
 ******************************************************************************
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----------");

        List<String> words = Arrays.asList("ada", "haskell", "scala", "java", "rust");
        // ver.1, ver.2
        /*
        List<String> ranking = rankedWords(words);
        */
        // ↓
        // ver.3
        /*
        List<String> ranking = rankedWords(scoreComparator, words);
        System.out.println(ranking); // [haskell, rust, scala, java, ada]
        System.out.println(words); // 変更されていないか確認する！
        */
        // ↓
        // ver.4
        /*
        List<String> ranking = rankedWords(scoreWithBonusComparator, words);
        System.out.println(ranking); // [scala, haskell, rust, java, ada]
        System.out.println(words); // 変更されていないか確認する！
        */
        // ↓
        // ver.5
        /*
        System.out.println(rankedWords(w -> score(w), words));          // [haskell, rust, scala, java, ada]
        System.out.println(rankedWords(w -> scoreWithBonus(w), words)); // [scala, haskell, rust, java, ada]
        System.out.println(words); // 変更されていないか確認する！
        */
        // ↓
        // ver.6
        System.out.println(rankedWords(w -> score(w), words));          // [haskell, rust, scala, java, ada]
        System.out.println(rankedWords(w -> scoreWithBonus(w), words)); // [scala, haskell, rust, java, ada]
        System.out.println(rankedWords(w -> scoreWithBonusAndPenalty(w), words)); // [java, ada, scala, haskell, rust]
        System.out.println(rankedWords(w -> score(w) + bonus(w) - penalty(w), words)); // [java, ada, scala, haskell, rust] scoreWithBonusAndPenaltyの別パターン
        System.out.println("- - - -");
        System.out.println(words); // 変更されていないか確認する！
    }


    // ------------------------------------------------------------------------
    static int score(String word) {
        return word.replaceAll("a", "").length();
    }

    /*
    static Comparator<String> scoreComparator = new Comparator<String>() {
        public int compare(String w1, String w2) {
            return Integer.compare(score(w2), score(w1));
        }
    };
    */
   // ↓
   static Comparator<String> scoreComparator =
        (w1, w2) -> Integer.compare(score(w2), score(w1));




    // ------------------------------------------------------------------------
    // ↓ 追加要件
    // ver.4
    static int scoreWithBonus(String word) {
        int base = score(word);
        if (word.contains("c")) return base + 5;
        else return base;
    }

    /*
    static Comparator<String> scoreWithBonusComparator = new Comparator<String>() {
        public int compare(String w1, String w2) {
            return Integer.compare(scoreWithBonus(w2), scoreWithBonus(w1));
        }
    };
    */
    // ↓
    static Comparator<String> scoreWithBonusComparator =
        (w1, w2) -> Integer.compare(scoreWithBonus(w2), scoreWithBonus(w1));




    // ------------------------------------------------------------------------
    // ↓ 追加要件
    // ver.6
    static int scoreWithBonusAndPenalty(String word) {
        int base = score(word);
        int bonus = word.contains("c") ? 5 : 0;
        int penalty = word.contains("s") ? 7 : 0;
        return base + bonus - penalty;
    }




    // ------------------------------------------------------------------------
    // ↓ 別パターン
    static int bonus(String word) {
        return word.contains("c") ? 5 : 0;
    }

    static int penalty(String words) {
        return words.contains("s") ? 7 : 0;
    }

    

    // ------------------------------------------------------------------------
    /* 
    // ver.1 元の値（words）が変わってしまっている
    static List<String> rankedWords(List<String> words) {
        words.sort(scoreComparator);
        return words;
    }
    */
    // ↓
    /* 
    // ver.2 結果を計算するのにまだ外部の値（scoreComparator）を使っている
    static List<String> rankedWords(List<String> words) {
        return words
            .stream()                       // words リストから要素を「生成」する新しい Stream を返す
            .sorted(scoreComparator)        // １つ前の Stream から要素を「生成」するが、渡された Comparator を使ってソートする
            .collect(Collectors.toList());  // １つ前の Stream から全ての要素コピーすることで、新しい List を返す
    }
    */
    // ↓
    /*
    // ver.3
    static List<String> rankedWords(Comparator<String> comparator, List<String> words) {
        return words
            .stream()                       // words リストから要素を「生成」する新しい Stream を返す
            .sorted(comparator)             // １つ前の Stream から要素を「生成」するが、渡された Comparator を使ってソートする
            .collect(Collectors.toList());  // １つ前の Stream から全ての要素コピーすることで、新しい List を返す
    }
    */
    // ↓
    // ver.5 スコアリング関数を渡す
    // 引数として Function 型の値を受け取ることは、スコアリングアルゴリズムを
    // カスタマイズできるようになることを意味する。
    // Comparator は wordScore 値の中で提供されるスコアリング関数を使って内部で作成される。
    static List<String> rankedWords(Function<String, Integer> wordScore, List<String> words) {
        Comparator<String> wordComparator =
            (w1, w2) -> Integer.compare(
                wordScore.apply(w2),
                wordScore.apply(w1)
            );
        // 不変性をサポートする Java の Stream API を使って、渡されたリストのコピーを返す。
        return words
            .stream()
            .sorted(wordComparator)
            .collect(Collectors.toList());
    }
}