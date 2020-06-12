/*
 * Copyright 2020 Daniel Tan
 *
 * Licensed under the MIT License;
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/MIT
 */
package com.geniewits.pagedlambdalist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

internal data class ViewDelegate<T: Any>(
    val viewType: Int,
    val match: (item: T) -> Boolean,
    val create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
    val bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
)