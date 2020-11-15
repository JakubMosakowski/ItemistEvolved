package com.jakmos.itemistevolved.utility.context

import android.animation.Animator
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieListener
import com.jakmos.itemistevolved.utility.vocabulary.NoOp

//region Lottie - Repeat Listener

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

fun LottieAnimationView.getSmoothFailureListener(
  onFailure: () -> Unit
): LottieListener<Throwable> = LottieListener<Throwable> {

  val repeatListener = getOneTimeRepeatListener {
    onFailure.invoke()
  }

  addAnimatorListener(repeatListener)
}

//endregion
