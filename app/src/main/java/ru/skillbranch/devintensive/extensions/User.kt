package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

fun User.toUserView(): UserView {
    val nickname = ""
    val initials = ""
    val status = when {
        lastVisit == null -> "Еще ни разу не был"
        isOnline -> "online"
        else -> "Последний раз был ${lastVisit?.humanizeDiff()}"
    }

    return UserView(
            id,
            fullName = "$firstName $lastName",
            avatar = avatar,
            nickname = nickname,
            initials = initials,
            status = status
    )
}



