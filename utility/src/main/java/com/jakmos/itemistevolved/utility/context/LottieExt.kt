package com.jakmos.itemistevolved.utility.context

import android.animation.Animator
import androidx.annotation.RawRes
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.LottieListener
import com.jakmos.itemistevolved.utility.vocabulary.NoOp

//region Lottie - Repeat Listener

/**
 * It adds new animation inside repeat listener because we don't want to stop the previous animation in the middle.
 */
fun LottieAnimationView.getOneTimeRepeatListener(
  onRepeat: () -> Unit) = object : Animator.AnimatorListener {
  override fun onAnimationStart(animator: Animator?) = NoOp
  override fun onAnimationEnd(animator: Animator?) = NoOp
  override fun onAnimationCancel(animator: Animator?) = NoOp

  override fun onAnimationRepeat(animator: Animator?) {
    onRepeat.invoke()
    removeAnimatorListener(this)
  }
}

//endregion

//region Lottie - End Listener

/**
 * Shortcut to add only end animation listener.
 */
fun LottieAnimationView.addEndAnimationListener(
  onEnd: () -> Unit) {

  val listener = object : Animator.AnimatorListener {
    override fun onAnimationStart(animator: Animator?) = NoOp
    override fun onAnimationCancel(animator: Animator?) = NoOp
    override fun onAnimationRepeat(animator: Animator?) = NoOp

    override fun onAnimationEnd(animator: Animator?) = onEnd.invoke()

  }

  addAnimatorListener(listener)
}

//endregion

//region Lottie - Failure Listener

/**
 * Shortcut to add failure listener that is gonna fire only after default animation ended.
 */
fun LottieAnimationView.getSmoothFailureListener(
  onFailure: () -> Unit
): LottieListener<Throwable> = LottieListener<Throwable> {

  val repeatListener = getOneTimeRepeatListener {
    onFailure.invoke()
  }

  addAnimatorListener(repeatListener)
}

//endregion

//region Lottie - Placeholder

/**
 * Shortcut to add placeholder animation.
 */
fun LottieAnimationView.setPlaceholder(
  @RawRes rawRes: Int
) {
  repeatCount = LottieDrawable.INFINITE
  setAnimation(rawRes)
}

//endregion
