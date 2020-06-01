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
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicInteger

/**
 * Extending on {@see PagedListAdapter} to allow mapping of item to
 * {@see RecyclerView.ViewHolder} and binding to it through lambda functions.
 *
 * @author  Daniel Tan
 * @version 1.0.0
 * @since   1.0.0
 */
open class PagedLambdaAdapter<T>(
    differ: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(
    differ
) {
    private val delegates = mutableListOf<Delegate<T>>()
    private val viewTypeGenerator = AtomicInteger(0)

    fun map(
        match: (item: T) -> Boolean = { true },
        create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
        bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
    ): PagedLambdaAdapter<T> {
        delegates.add(Delegate(viewTypeGenerator.getAndIncrement(), match, create, bind))
        return this
    }

    override fun getItemViewType(position: Int) =
        getItem(position)?.run { delegates.first { it.match(this) }.viewType } ?: run { -1 }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegates.first { it.viewType == viewType }.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.run { delegates.first { it.match(this) }.bind(holder, this) }
    }

    private data class Delegate<T>(
        val viewType: Int,
        val match: (item: T) -> Boolean,
        val create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
        val bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
    )
}
