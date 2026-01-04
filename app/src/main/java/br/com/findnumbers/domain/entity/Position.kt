package br.com.findnumbers.domain.entity

data class Position(
    val initial: Pair<Int, Int>,
    val final: Pair<Int, Int>,
    val number: String
)