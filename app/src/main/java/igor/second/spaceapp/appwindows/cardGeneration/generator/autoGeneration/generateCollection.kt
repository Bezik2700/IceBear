package igor.second.spaceapp.appwindows.cardGeneration.generator.autoGeneration

fun generateCollection(): Int {

    var b = 0
    val a = (1..1000).random()

    when (a) {
        in 1..400 -> b = 1 // 40 %
        in 401..700 -> b = 2 // 30 %
        in 701..950 -> b = 3 // 25 %
        in 951..980 -> b = 4 // 3 %
        in 981..997 -> b = 5 // 1.7 %
        in 998..1000 -> b = 6 // 0.3 %
    }
    return b
}