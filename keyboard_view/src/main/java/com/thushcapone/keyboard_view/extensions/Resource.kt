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

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.content.ContextCompat


/**
 * Created by Thiependa Seye on 1/10/19.
 * T.C.
 * thiependa.seye@gmail.com
 */

/**
 * Returns a color associated with a particular resource ID
 */
fun Int.getColorFromResourceId(context: Context) = run {
    ContextCompat.getColor(context, this)
}

fun Int.getColorFilterFromResourceId(context: Context) = run {
    PorterDuffColorFilter(this.getColorFromResourceId(context), PorterDuff.Mode.SRC_IN)
}