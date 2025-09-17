package igor.second.spaceapp.appwindows.cardGeneration.generator.autoGeneration

import androidx.compose.runtime.MutableState

fun userGenerationLevelUpgrade(generationValue: MutableState<Int>){

    var collection = generateCollection()
    var value = generateValue()

    if (collection == 1){
        if (value == 1){
            generationValue.value = (1..10).random()
        } else if (value == 2){
            generationValue.value = (11..20).random()
        } else if (value == 3){
            generationValue.value = (21..30).random()
        } else if (value == 4){
            generationValue.value = (31..40).random()
        } else if (value == 5){
            generationValue.value = (41..50).random()
        } else if (value == 6){
            generationValue.value = (51..60).random()
        } else if (value == 7){
            generationValue.value = (61..70).random()
        } else {
            generationValue.value = (71..80).random()
        }
    } else if (collection == 2){
        if (value == 1){
            generationValue.value = (81..90).random()
        } else if (value == 2){
            generationValue.value = (91..100).random()
        } else if (value == 3){
            generationValue.value = (101..110).random()
        } else if (value == 4){
            generationValue.value = (111..120).random()
        } else if (value == 5){
            generationValue.value = (121..130).random()
        } else if (value == 6){
            generationValue.value = (131..140).random()
        } else if (value == 7){
            generationValue.value = (141..150).random()
        } else {
            generationValue.value = (151..160).random()
        }
    } else if (collection == 3){
        if (value == 1){
            generationValue.value = (161..170).random()
        } else if (value == 2){
            generationValue.value = (171..180).random()
        } else if (value == 3){
            generationValue.value = (181..190).random()
        } else if (value == 4){
            generationValue.value = (191..200).random()
        } else if (value == 5){
            generationValue.value = (201..210).random()
        } else if (value == 6){
            generationValue.value = (211..220).random()
        } else if (value == 7){
            generationValue.value = (221..230).random()
        } else {
            generationValue.value = (231..240).random()
        }
    } else if (collection == 4){
        if (value == 1){
            generationValue.value = (241..250).random()
        } else if (value == 2){
            generationValue.value = (251..260).random()
        } else if (value == 3){
            generationValue.value = (261..270).random()
        } else if (value == 4){
            generationValue.value = (271..280).random()
        } else if (value == 5){
            generationValue.value = (281..290).random()
        } else if (value == 6){
            generationValue.value = (291..300).random()
        } else if (value == 7){
            generationValue.value = (301..310).random()
        } else {
            generationValue.value = (311..320).random()
        }
    } else if (collection == 5){
        if (value == 1){
            generationValue.value = (321..330).random()
        } else if (value == 2){
            generationValue.value = (331..340).random()
        } else if (value == 3){
            generationValue.value = (341..350).random()
        } else if (value == 4){
            generationValue.value = (351..360).random()
        } else if (value == 5){
            generationValue.value = (361..370).random()
        } else if (value == 6){
            generationValue.value = (371..380).random()
        } else if (value == 7){
            generationValue.value = (381..390).random()
        } else {
            generationValue.value = (391..400).random()
        }
    } else if (collection == 6){
        if (value == 1){
            generationValue.value = (401..410).random()
        } else if (value == 2){
            generationValue.value = (411..420).random()
        } else if (value == 3){
            generationValue.value = (421..430).random()
        } else if (value == 4){
            generationValue.value = (431..440).random()
        } else if (value == 5){
            generationValue.value = (441..450).random()
        } else if (value == 6){
            generationValue.value = (451..460).random()
        } else if (value == 7){
            generationValue.value = (461..470).random()
        } else {
            generationValue.value = (471..480).random()
        }
    }
}