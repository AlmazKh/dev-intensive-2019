package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.extensions.letters as letters

val letters: List<Pair<String, String>> = listOf(
        Pair("а", "a"),
        Pair("б", "b"),
        Pair("в", "v"),
        Pair("г", "g"),
        Pair("д", "d"),
        Pair("е", "e"),
        Pair("ё", "e"),
        Pair("ж", "zh"),
        Pair("з", "z"),
        Pair("и", "i"),
        Pair("й", "i"),
        Pair("к", "k"),
        Pair("л", "l"),
        Pair("м", "m"),
        Pair("н", "n"),
        Pair("о", "o"),
        Pair("п", "p"),
        Pair("р", "r"),
        Pair("с", "s"),
        Pair("т", "t"),
        Pair("у", "u"),
        Pair("ф", "f"),
        Pair("х", "h"),
        Pair("ц", "c"),
        Pair("ч", "ch"),
        Pair("ш", "sh"),
        Pair("щ", "sh'"),
        Pair("ъ", ""),
        Pair("ы", "i"),
        Pair("ь", ""),
        Pair("э", "e"),
        Pair("ю", "yu"),
        Pair("я", "ya")
)

fun Utils.toInitials(firstName: String?, lastName: String?): String? {
    val charOne = firstName?.getOrNull(0)
    val charTwo = lastName?.getOrNull(0)

    return if (charOne != null && charTwo != null) {
        "$charOne$charTwo"
    } else if (charOne != null && charOne != ' ') {
        "$charOne"
    } else if (charTwo != null && charTwo != ' ') {
        "$charTwo"
    } else {
        null
    }
}

fun Utils.transliteration(payload: String?, divider: String? = " "): String {
    val charsPayload = payload?.toCharArray()
    var finalString = ""

    if (charsPayload != null) {
        for (i in charsPayload) {
            loop@ for (j in letters) {
                when {
                    "$i" == j.second -> {
                        finalString += j.second
                        break@loop
                    }
                    "$i" == j.second.toUpperCase() -> {
                        finalString += i
                        break@loop
                    }
                    "$i" == j.first -> {
                        finalString += j.second
                        break@loop
                    }
                    "$i" == j.first.toUpperCase() -> {
                        val str = j.second
                        finalString += if (str.length == 1) {
                            j.second.toUpperCase()
                        } else {
                            "${j.second[0].toUpperCase()}${j.second[1]}"
                        }
                        break@loop
                    }
                    i == ' ' -> {
                        finalString += divider
                        break@loop
                    }
                }
            }
        }

    }
    return finalString
}
