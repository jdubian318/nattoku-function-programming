// object に関数が含まれる
object ShoppingCart {
    // def は関数を定義する
    def getDiscountPercentage(items: List[String]) : Int = {
        // Scala などの FP 言語では、if は式であるため、
        // return キーワードはない
        // 関数内の最後の式が戻り値として使われる
        if (items.contains("Book")) {
            5
        } else {
            0
        }
    }
}

val justApple = List("Apple")
println(ShoppingCart.getDiscountPercentage(justApple)) // 0

val appleAndBook = List("Apple", "Book")
println(ShoppingCart.getDiscountPercentage(appleAndBook)) // 5



//=============================================================================

object TipCalculator {
    def getTipCalculator(names: List[String]): Int = {
        if (names.size > 5) {
            20
        } else if (names.size > 0) {
            10
        } else {
            0
        }
    }
}

println(TipCalculator.getTipCalculator(List.empty))
// → 0

val smallGroup = List("Alice", "Bob", "Charlie")
println(TipCalculator.getTipCalculator(smallGroup))
// → 10

val largeGroup = List("Alice", "Bob", "Charlie", "Daniel", "Emily", "Frank")
println(TipCalculator.getTipCalculator(largeGroup))
// → 20



//=============================================================================

def getDiscountPercentage(items: List[String]): Int = {
    if (items.contains("Book")) {
        5
    } else {
        0
    }
}

println(getDiscountPercentage(List.empty) == 1) // false
println(getDiscountPercentage(List.empty) == 0) // true
println(getDiscountPercentage(List("Apple", "Book")) == 5) // true



//=============================================================================

def increment(x: Int): Int = {
    x + 1
}
println(increment(6) == 7) // true
println(increment(0) == 1) // true
println(increment(-6) == -5) // true
println(increment(Integer.MAX_VALUE - 1) == Integer.MAX_VALUE) // true

def add(a: Int, b: Int): Int = {
    a + b
}
println(add(2, 5) == 7) // true
println(add(-2, 5) == 3) // true

def wordScore(word: String): Int = {
    word.replace("a", "").length
}
println(wordScore("Scala") == 3) // true
println(wordScore("function") == 8) // true
println(wordScore("") == 0) // true

def getTipPercentage(names: List[String]): Int = {
    if (names.size > 5) 20
    else if (names.size > 0) 10
    else 0
}
println(getTipPercentage(List("Alice", "Bob")) == 10) // true
println(getTipPercentage(List("Alice", "Bob", "Charlie", 
                                "Danny", "Emily", "Wojtek")) == 20) // true
println(getTipPercentage(List.empty) == 0) // true

def getFirstCharacters(s: String): Char = {
    if (s.length > 0) s.charAt(0)
    else ' '
}
println(getFirstCharacters("Ola") == 'O') // true
println(getFirstCharacters("") == ' ') // true
println(getFirstCharacters(" Ha! ") == ' ') // true


