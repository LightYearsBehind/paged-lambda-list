# λ List
Flattened Android `PagedListAdapter` for speedy adaptation.

## Gradle
```gradle
implementation "com.geniewits.pagedlambdalist:paged-lambda-list:1.0.0"
```

## Exposed Functions
```kotlin
/*
 * Chainable mapping of item to RecyclerView.ViewHolder.
 *
 * match    return TRUE if this is the item to map to the following RecyclerView.ViewHolder.
 * create   return a new RecyclerView.Holder for the matched item.
 * bind     bind the item to the created RecyclerView.ViewHolder.
 */
map(
    match:  (item: T) -> Boolean = { true },
    create: (parent: ViewGroup) -> RecyclerView.ViewHolder,
    bind:   (holder: RecyclerView.ViewHolder, item: T) -> Unit
): PagedLambdaAdapter<T>
```

## Initialization
Same as how we initialize a `PagedListAdapter`, a `DiffUtil.ItemCallback<T>` implementation is required.
```kotlin
val adapter = PagedLambdaAdapter<Any>(differ)
```

## Mapping
- Single view type
```kotlin
adapter.map(
    create  = { MyViewHolder(it) { onClick(it) } },
    bind    = { holder, item -> (holder as MyViewHolder).onBind(item) }
)
```

- Multiple view types
```kotlin
adapter
    .map(
        { it is A },
        { ViewHolderA(it) },
        { holder, item -> (holder as ViewHolderA).onBind(item) }
    )
    .map(
        { it is B },
        { ViewHolderB(it) },
        { holder, item -> (holder as ViewHolderB).onBind(item) }
    )
```

- Multiple view types with placeholder
```kotlin
adapter
    .map(...)
    .map(
        create  = { PlaceholderViewHolder() }
        bind    = { holder, item -> (holder as PlaceholderViewHolder).onBind(item) }
    )
```

## Using RecyclerView.Adapter?
Look over [here](https://github.com/LightYearsBehind/lambda-list) instead.

