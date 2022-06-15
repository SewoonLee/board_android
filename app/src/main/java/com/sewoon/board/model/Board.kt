package com.sewoon.board.model

import java.util.*

data class Board(
    val id: Int?,
    val title: String?,
    val content:String?,
    val hits:Int?,
    val created_date:Date?,
    val updated_date:Date?,
    val password:String?
)