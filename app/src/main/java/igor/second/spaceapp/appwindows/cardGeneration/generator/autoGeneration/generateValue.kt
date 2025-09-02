package igor.second.spaceapp.appwindows.cardGeneration.generator.autoGeneration

fun generateValue(): Int {

    var b = 0
    val a = (1..1000).random()

    when (a) {
        in 1..210 -> b = 1
        in 211..390 -> b = 2
        in 391..540 -> b = 3
        in 541..660 -> b = 4
        in 661..760 -> b = 5
        in 761..850 -> b = 6
        in 851..930 -> b = 7
        in 931..1000 -> b = 8
    }
    return b
}