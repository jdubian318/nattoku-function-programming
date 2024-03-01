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
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * 単語リストの各単語のスコアを知る必要がある。
 * ランク付けを行う関数の機能は同じままである（既存の関数は一切変更できない）。
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * スコアが１よりも高い単語のリストを返す必要がる。
 * これまで実装してきた機能は引き続き同じように動作する（既存の関数は一切変更できない）。
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * 高いスコアの単語は、スコアが１よりも大きい単語である。
 * 高いスコアの単語は、スコアが０よりも大きい単語である。
 * 高いスコアの単語は、スコアが５よりも大きい単語である。
 * 
 * ----- ↓↓↓ 要件変更（追加要件） ↓↓↓ -----
 * 累積スコアを返す。
 * 入力リストとして渡された単語の累積スコアを返す必要がある。
 * これまで実装してきた機能は引き続き同じように動作する（既存の関数は一切変更できない）。
 ******************************************************************************
 */

//=============================================================================
println("----------")

// ------------------------------
// TODO: define funtions
def score(word: String): Int = word.replaceAll("a", "").length

def bonus(word: String): Int = if (word.contains("c")) 5 else 0

def penalty(word: String): Int = if (word.contains("s")) 7 else 0

def rankedWords(wordScore: String => Int, words: List[String]): List[String] = {
    // 「何を行う必要があるか？」でなく「どのように行うか？」に気を取られると、
    // 常にコードが読みにくくなる。
    //def negativeScore(word: String): Int = -wordScore(word)
    //words.sortBy(negativeScore)
    // ↓
    // 宣言型アプローチ
    // 「何をする必要があるか？」を宣言する。
    words.sortBy(wordScore).reverse
}

def wordScores(wordScore: String => Int, words: List[String]): List[Int] = {
    words.map(wordScore)
}

/*
def highScoringWords(wordScore: String => Int, words: List[String]): List[String] = {
    words.filter(word => wordScore(word) > 1)
}
*/
// ↓
/*
// この関数のパラメーターは２つ。
// Int を受け取って List[String] を返す関数を返す。
def highScoringWords(wordScore: String => Int, words: List[String]): Int => List[String] = {
    higherThan => words.filter(word => wordScore(word) > higherThan)
}
*/
// ↓
/*
// << 「関数を返す」構文を使用 >>
// この関数のパラメーターは１つ。String => Int
// 返される関数は、Int を受け取り、関数を返す。
// 返される関数は、List[String] を受け取り、新しいを List[String] を返す。
def highScoringWords(wordScore: String => Int): Int => List[String] => List[String] = {
    higherThan => words => words.filter(word => wordScore(word) > higherThan)
}
*/
// ↓
// << 「複数のパラメーターリスト」構文を使用 >>
// Scala をはじめとする関数型言語では、複数のパラメーターリストを定義できる構文を
// 使うことができる。
def highScoringWords(wordScore: String => Int)(higherThan: Int)(words: List[String]) : List[String] = {
    words.filter(word => wordScore(word) > higherThan)
}

def cumulativeScore(wordScore: String => Int, words: List[String]): Int = {
    // 0 から始まる現在の合計に
    // 現在の要素を追加できる関数 foldLeft を指定
    words.foldLeft(0)((total, word) => total + wordScore(word))

    // --- List("rust", "java") の例 ---
    // total は 0 から始まる。
    // →  0 + wordScore("rust") = -3
    // → -3 + wordScore("java") = -1
    //
    // 無名関数はリストの要素ごとに呼び出される。
    // （上記の例だと２回）
}


// ------------------------------
// TODO: use functions
val words = List("ada", "haskell", "scala", "java", "rust")

println(words)
// List("ada", "haskell", "scala", "java", "rust")

println(rankedWords(score, words))
// List("haskell", "rust", "scala", "java", "ada")

println(rankedWords(w => score(w) + bonus(w), words)) 
// List("scala", "haskell", "rust", "java", "ada")

println(rankedWords(w => score(w) + bonus(w) - penalty(w), words)) 
// List("java", "scala", "ada", "haskell", "rust")

println(wordScores(w => score(w) + bonus(w) - penalty(w), words))
// List(1, -1, 1, 2, 3)

/*
println(highScoringWords(w => score(w) + bonus(w) - penalty(w), words))
// List("java")
*/
// ↓
/*
// 新しいイミュータブル値の定義
val wordsWithScoreHigherThan: Int => List[String] =
    highScoringWords(w => score(w) + bonus(w) - penalty(w), words)

println(wordsWithScoreHigherThan(1))
// List("java")
println(wordsWithScoreHigherThan(0))
// List("ada", "scala", "java")
println(wordsWithScoreHigherThan(5))
// List()
*/
// ↓
val wordsWithScoreHigherThan: Int => List[String] => List[String] =
    highScoringWords(w => score(w) + bonus(w) - penalty(w))

val words2 = List("football", "f1", "hockey", "basketball")

println(wordsWithScoreHigherThan(1)(words))
// List("java")

println(wordsWithScoreHigherThan(0)(words2))
// List("football", "f1", "hockey", "basketball")

println(wordsWithScoreHigherThan(5)(words2))
// List("football", "hockey")

println(highScoringWords(w => score(w) + bonus(w) - penalty(w))(1)(words))
// List("java")


//=============================================================================
//println("----------")

// ------------------------------
// TODO: define funtions


// ------------------------------
// TODO: use functions




