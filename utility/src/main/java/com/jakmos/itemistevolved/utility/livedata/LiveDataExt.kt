package com.jakmos.itemistevolved.utility.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

//region Operate on stream

/**
 * Combines two live data streams using a delivered mapping lambda
 */
inline fun <reified T, reified K, reified R> LiveData<T>.combineWith(
  liveData: LiveData<K>,
  noinline block: (first: T?, second: K?) -> R
): LiveData<R> {

  // Mediator stream, which will combine all added streams together
  val result = MediatorLiveData<R>()

  // Add first stream to mediator
  result.addSource(this) {
    result.value = block.invoke(this.value, liveData.value)
  }

  // Add second stream to mediator
  result.addSource(liveData) {
    result.value = block.invoke(this.value, liveData.value)
  }

  // Return mediator stream
  return result
}

//endregion

//region Update

inline fun <reified T> MutableLiveData<T>.updateTo(newValue: T, byPost: Boolean = true) {
  when (byPost) {
    true -> postValue(newValue)
    false -> value = newValue
  }
}

//endregion

//region Test

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
  val observer = Observer<T> { }
  try {
    observeForever(observer)
    block()
  } finally {
    removeObserver(observer)
  }
}

//endregion
