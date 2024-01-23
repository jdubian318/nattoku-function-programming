def add(a: Int, b: Int): Int = {
    a + b
}

def increment(x: Int): Int = {
    x + 1
}

def getFirstCharacter(s: String): Char = {
    s.charAt(0)
}

def wordScore(word: String): Int = {
    word.length()
}


println(add(1, 2) == 3)
println(increment(0) == 1)
println(getFirstCharacter("Hello") == 'H')
println(wordScore("Scala") == 5)