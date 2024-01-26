package no.uio.ifi.in2000.erlenjoa.oblig1

import kotlin.math.round

fun converter(verdi: Int, enhet: ConverterUnits): Int {
    return when (enhet) {
        ConverterUnits.OUNCE -> round(verdi*0.02957).toInt()
        ConverterUnits.CUP -> round(verdi*0.23659).toInt()
        ConverterUnits.GALLON -> round(verdi*3.78541).toInt()
        ConverterUnits.HOGSHEAD -> round(verdi*238.481).toInt()
        else -> verdi
    }
}

/**
 * Konverteringstabell:
 * 1 fluid ounce = 0,02957 liter
 * 1 cup         = 0,23659 liter
 * 1 gallon      = 3,78541 liter
 * 1 hogshead    = 238,481 liter
 */