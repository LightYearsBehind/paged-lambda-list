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
import java.util.concurrent.atomic.AtomicInteger

internal class ViewDelegateManager<T: Any> {
    private val delegates = mutableListOf<ViewDelegate<T>>()
    private val viewTypeGenerator = AtomicInteger(0)

    fun add(
        match: (item: T) -> Boolean,
        create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
        bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
    ) {
        delegates.add(ViewDelegate(viewTypeGenerator.getAndIncrement(), match, create, bind))
    }

    fun getItemViewType(item: T?) =
        item?.run { delegates.first { it.match(this) }.viewType } ?: run { -1 }

    fun createViewHolder(parent: ViewGroup, viewType: Int) =
        delegates.first { it.viewType == viewType }.create(parent)

    fun bindViewHolder(holder: RecyclerView.ViewHolder, item: T?) {
        item?.run { delegates.first { it.match(this) }.bind(holder, this) }
    }
}