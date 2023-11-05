package com.duran.animals.midterm.model

import java.io.Serializable

data class animals(
    val name: String,
    val description: String,
    var blockstatus: Boolean
): Serializable