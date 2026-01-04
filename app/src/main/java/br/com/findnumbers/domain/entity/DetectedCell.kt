package br.com.findnumbers.domain.entity

data class DetectedCell(
    val row: Int,
    val col: Int,
    val value: String,
    val boundingBox: android.graphics.Rect
)
