package com.satelliteship.domain.model

data class PositionResponse(
    val list: List<Positions>
)

data class Positions(
    val id: Int,
    val positions: List<Position>
)

data class Position(
    val posX: Double,
    val posY: Double
)
