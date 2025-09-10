package com.el_aouthmanie.nticapp.controllers


sealed class UserAuthRole{
    data object Admin: UserAuthRole(){
        override fun toString(): String {
            return "Admin"
        }
    }
    data object Trainee : UserAuthRole(){
        override fun toString(): String {
            return "Trainee"
        }
    }
    data object Guest : UserAuthRole(){
        override fun toString(): String {
            return "Guest"
        }
    }
}

fun String.toUserAuthRole() : UserAuthRole{
    return when (this){
        UserAuthRole.Trainee.toString() -> UserAuthRole.Trainee
        UserAuthRole.Admin.toString() -> UserAuthRole.Admin
        else -> UserAuthRole.Guest
    }
}