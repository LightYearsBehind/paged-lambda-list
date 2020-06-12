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

/**
 * Extending on {@see PagedListAdapter} to allow mapping of item to
 * {@see RecyclerView.ViewHolder} and binding to it through lambda functions.
 *
 * @author  Daniel Tan
 * @version 1.1.0
 * @since   1.0.0
 */
@Suppress("DEPRECATION")
open class PagedLambdaAdapter<T: Any>(
    differ: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(
    differ
), ViewMapper<T, PagedLambdaAdapter<T>> {
    private val viewDelegateManager = ViewDelegateManager<T>()

    override fun map(
        match: (item: T) -> Boolean,
        create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
        bind: (holder: RecyclerView.ViewHolder, item: T) -> Unit
    ): PagedLambdaAdapter<T> {
        viewDelegateManager.add(match, create, bind)
        return this
    }

    override fun getItemViewType(position: Int) =
        viewDelegateManager.getItemViewType(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewDelegateManager.createViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        viewDelegateManager.bindViewHolder(holder, getItem(position))
    }
}