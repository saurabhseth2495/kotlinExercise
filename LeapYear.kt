fun main(args: Array<String>) {
    print("please enter year :- ")
    var leap: Boolean = false
    var year: Int = Integer.valueOf(readLine())
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                leap = true
            }
        } else {
            leap = true
        }
    }
    println(if (leap) "$year is leap year" else "$year is not leap year")
}