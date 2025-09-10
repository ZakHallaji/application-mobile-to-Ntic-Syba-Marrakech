package com.el_aouthmanie.nticapp.modules.intities

import com.el_aouthmanie.nticapp.controllers.UserAuthRole
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey

class User (
    val name : String,
    val group : String,
    authRole: UserAuthRole = UserAuthRole.Guest
) {

}