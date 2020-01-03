/*
 *
 *  * Copyright (c) 2018. T.C.
 *  * All Rights Reserved
 *  *
 *  * This product is protected by copyright and distributed under
 *  * licenses restricting copying, distribution and decompilation.
 *  *
 *
 */

package com.thushcapone.keyboard_view.extensions

import java.util.*


/**
 * Created by Thiependa Seye on 1/31/19.
 * T.C.
 * thiependa.seye@gmail.com
 */

fun <T> Array<T>.shuffle() = apply {
    val rng = Random()

    for (index in this.indices) {
        val randomIndex = rng.nextInt(this.size)

        // Swap with the random position
        val temp = this[index]
        this[index] = this[randomIndex]
        this[randomIndex] = temp
    }
}

