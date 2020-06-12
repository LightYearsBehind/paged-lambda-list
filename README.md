# λ List
Flattened Android `PagedListAdapter` for speedy adaptation.

## Gradle
```gradle
implementation "com.geniewits.pagedlambdalist:paged-lambda-list:1.1.0"
```

## Initialization
Same as how we initialize a `PagedListAdapter` and `PagingDataAdapter`, a `DiffUtil.ItemCallback<T>` implementation is required.
```kotlin
val adapter = PagedLambdaAdapter<Any>(differ)
```
or
```kotlin
val adapter = PagingLambdaAdapter<Any>(differ)
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

