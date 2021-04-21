fun main(args: Array<String>) {
    println("Enter the statement : ")
    var statement: String = readLine().toString()

    print(if (isPangram(statement)) "the statement is pangram" else "the statement is not pangram")
}

fun isPangram(statement: String): Boolean {
    var statement1: CharArray = (statement.toLowerCase()).toCharArray()
    println(statement1)
    for (asciiCh in 97..122) {
        var found: Boolean = false
        for (ch: Char in statement1) {
            if (asciiCh == ch.toInt()) {
                found = true
                break;
            }
        }
        if (!found) {
            return false
        }
    }
    return true
}
