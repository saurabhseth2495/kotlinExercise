fun main(args: Array<String>) {
    print("please enter year :- ")
    var year: Int = Integer.valueOf(readLine())
    var leap = ((year % 400) == 0) || (((year % 4) == 0) && ((year % 100) != 0))
    println(if (leap) "$year is leap year" else "$year is not leap year")
}