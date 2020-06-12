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

internal interface ViewMapper<T: Any, Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>> {
    fun map(
        match: (item: T) -> Boolean,
        create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
        bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
    ): Adapter
}