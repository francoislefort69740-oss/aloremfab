package com.example.myapplication.canvas

import com.example.myapplication.model.StepControlGRV
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getResultConformityOutside(exterieur: List<Int>): String {
    var result = "CONFORME"
    for (i in exterieur) {
        if (i != 1) { result = "NON CONFORME" }
    }
    return result
}

fun getResultConformityInside(stepInside: StepControlGRV.Step4ControlGRV?): String {
    var resultat = "CONFORME"
    if (stepInside?.internalNA ?: false) {
        resultat = "N/A"
    }
    if (stepInside?.internalOK == false || stepInside?.internalObjectInside ?: false || stepInside?.internalPollution ?: false ) {
        resultat = "NON CONFORME"
    }
    if (stepInside == null) resultat = "NON CONFORME"
    return resultat
}

fun getResultConformityThickNess(step: StepControlGRV.Step5ControlGRV): String {

    val minimums = listOf(step.epaisseurMinSideFront, step.epaisseurMinSideBack, step.epaisseurMinSideRight, step.epaisseurMinSideLeft)
    val controls = listOf(step.epaisseurMinSideBack to
            listOf(step.epaisseurSideBackResult1, step.epaisseurSideBackResult2, step.epaisseurSideBackResult3, step.epaisseurSideBackResult4, step.epaisseurSideBackResult5),

        step.epaisseurMinSideFront to
                listOf(step.epaisseurSideFrontResult1, step.epaisseurSideFrontResult2, step.epaisseurSideFrontResult3, step.epaisseurSideFrontResult4, step.epaisseurSideFrontResult5),

        step.epaisseurMinSideLeft to
                listOf(step.epaisseurSideLeftResult1, step.epaisseurSideLeftResult2, step.epaisseurSideLeftResult3, step.epaisseurSideLeftResult4, step.epaisseurSideLeftResult5),

        step.epaisseurMinSideRight to
                listOf(step.epaisseurSideRightResult1, step.epaisseurSideRightResult2, step.epaisseurSideRightResult3, step.epaisseurSideRightResult4, step.epaisseurSideRightResult5)
    )

    return when {
        step.epaisseurNA -> "N/A"
        minimums.any { it == 0 } -> "NON CONFORME"
        controls.any { (minimum, results) -> minimum != null && results.filterNotNull().any { it < minimum } } -> "NON CONFORME"
        else -> "CONFORME"
    }
}

fun getNextDateControl(d: String): String {
    return try {
        val date = LocalDate.parse(
            d,
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )

        val result = date.plusMonths(30).minusDays(1)
        return result.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    } catch (e: Exception) {
        "A DEFINIR"
    }
}



