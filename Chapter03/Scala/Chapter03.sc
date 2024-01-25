println("----------")

def replan(plan: List[String],
            newCity: String,
            beforeCity: String): List[String] = {
    val beforeCityIndex = plan.indexOf(beforeCity)
    val citiesBefore = plan.slice(0, beforeCityIndex)
    val citiesAfter = plan.slice(beforeCityIndex, plan.size)
    citiesBefore.appended(newCity).appendedAll(citiesAfter)
}


val planA = List("Paris", "Berlin", "Krakow")
val planB = replan(planA, "Vienna", "Krakow")
println("PlanA = " + planA)
println("PlanB = " + planB)
println("PlanA = " + planA + " <--- A に変更がないことを確認！")



//=============================================================================

println("----------")

def abbreviate(name: String): String = {
    val initial = name.substring(0, 1)
    val separator = name.indexOf(' ')
    val lastName = name.substring(separator + 1)
    initial + ". " + lastName
}


val name1 = "Alonzo Church"
println(name1 + " --> " + abbreviate(name1))
val name2 = "A. Church"
println(name2 + " --> " + abbreviate(name2))
val name3 = "A Church"
println(name3 + " --> " + abbreviate(name3))



//=============================================================================

println("----------")

/*
Scala API では、Array クラスは次のようにスライス関数を定義します。

    def slice(from: Int, until: Int): Array[T]

添字が「from」以上「until」未満の要素のコレクションを返す。
「from」は配列の開始インデックス (包括的) 
「until」は配列の終了インデックス (排他的) 

　　　｜＜ーーー＞｜
ーーー●ーーーーー○ーーー
      from        until
*/

// 引数としてリストを受け取り、その最初の２つの要素だけを含んだ新しいリストを返す。
def firstTwo(list: List[String]): List[String] =
    list.slice(0, 2)
println(firstTwo(List("a", "b", "c")) == List("a", "b")) // true

// 引数としてリストを受け取り、その最後の２つ要素だけを含んだ新しいリストを返す。
def lastTwo(list: List[String]): List[String] =
    list.slice(list.size - 2, list.size)
println(lastTwo(List("a", "b", "c")) == List("b", "c")) // true

// 引数としてリストを受け取り、その最初の２つの要素を末尾に移動した新しいリストを返す。
def moveFirstTwoToTheEnd(list: List[String]): List[String] = {
    val firstTwo = list.slice(0, 2) // 「0..1」 chars
    val withoutFirstTwo = list.slice(2, list.size) // 「2..last」 chars
    withoutFirstTwo.appendedAll(firstTwo) // 「2..last」 + 「0..1」
}
println(moveFirstTwoToTheEnd(List("a", "b", "c")) == List("c", "a", "b"))

// 引数としてリストと新しい要素を受け取り、
// そのリストの最後の要素の前に element を挿入した新しいリストを返す。
def insertedBeforeLast(list: List[String], element: String): List[String] = {
    val last = list.slice(list.size - 1, list.size)
    val withoutLast = list.slice(0, list.size - 1)
    withoutLast.appended(element).appendedAll(last)
}
println(insertedBeforeLast(List("a", "b"), "c") == List("a", "c", "b"))


