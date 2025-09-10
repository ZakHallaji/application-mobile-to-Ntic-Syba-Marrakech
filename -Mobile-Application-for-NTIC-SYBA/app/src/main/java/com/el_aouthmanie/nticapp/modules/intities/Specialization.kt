package com.el_aouthmanie.nticapp.modules.intities

class Specialization(
    name : String
) {
    var name = name
        set(value) {
            field = value.uppercase()
        }
}