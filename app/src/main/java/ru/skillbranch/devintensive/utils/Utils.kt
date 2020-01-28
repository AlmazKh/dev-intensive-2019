package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")
        return Pair(
                checkFullNameParts(parts?.getOrNull(0)),
                checkFullNameParts(parts?.getOrNull(1))
        )
    }

    private fun checkFullNameParts(part: String?): String? {
        return if (part == "" || part == " ") {
            return null
        } else
            part
    }
}
