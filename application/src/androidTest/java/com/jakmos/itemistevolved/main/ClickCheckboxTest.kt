package com.jakmos.itemistevolved.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.jakmos.itemistevolved.AndroidTestData
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.RetryTestRule
import com.jakmos.itemistevolved.presentation.main.MainActivity
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickBack
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
import com.schibsted.spain.barista.internal.assertAny
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain


class ClickCheckboxTest {

  //region Rules

  @get:Rule
  val rule: RuleChain = RuleChain
    .outerRule(RetryTestRule(3))
    .around(activityScenarioRule<MainActivity>())

  //endregion

  //region Setup

  companion object {
    val CHECKLIST = AndroidTestData.CHECKLISTS[2]
  }

  //endregion

  //region Click

  /**
   * Verify if clicking checkbox is saved.
   */
  @Test
  fun clickCheckboxTest() {

    // Given
    createChecklist(CHECKLIST)
    clickOn(CHECKLIST.name)

    // When
    clickListItemChild(id.checkboxRv, 0, id.checkbox)
    clickBack()
    clickOn(CHECKLIST.name)

    // Then
    withText(CHECKLIST.subsections[0].text).assertAny(
      withCompoundDrawable(R.drawable.ic_check_circle_checked)
    )
  }

  //endregion

  //region Uncheck All

  /**
   * Verify if clicking uncheck all works.
   */
  @Test
  fun clickUncheckAllTest() {

    // Given
    createChecklist(CHECKLIST)
    clickOn(CHECKLIST.name)
    clickListItemChild(id.checkboxRv, 0, id.checkbox)
    clickListItemChild(id.checkboxRv, 1, id.checkbox)

    // When
    clickOn(id.clearBtn)
    clickBack()
    clickOn(CHECKLIST.name)

    // Then
    withText(CHECKLIST.subsections[0].text).assertAny(
      withCompoundDrawable(R.drawable.ic_check_circle_unchecked)
    )
    withText(CHECKLIST.subsections[1].text).assertAny(
      withCompoundDrawable(R.drawable.ic_check_circle_unchecked)
    )
    withText(CHECKLIST.subsections[2].text).assertAny(
      withCompoundDrawable(R.drawable.ic_check_circle_unchecked)
    )
  }

  //endregion

  //region Commons

  private fun withCompoundDrawable(
    expectedDrawableResId: Int): Matcher<View> = object : BoundedMatcher<View, TextView>(
    TextView::class.java) {

    override fun describeTo(description: Description) {
      description.appendText("has compound drawable resource $expectedDrawableResId")
    }

    override fun matchesSafely(textView: TextView): Boolean {
      for (drawable in textView.compoundDrawables) {
        if (sameBitmap(textView.context, drawable, expectedDrawableResId, textView)) {
          return true
        }
      }
      return false
    }
  }

  private fun sameBitmap(
    context: Context,
    drawable: Drawable?,
    resourceId: Int,
    view: View
  ): Boolean {

    val otherDrawable: Drawable? =
      ContextCompat.getDrawable(context, resourceId)

    if (drawable == null || otherDrawable == null) {
      return false
    }

    val stateDrawable = invokeStateListDrawable(drawable, view)

    val bitmap = getBitmapFromDrawable(stateDrawable)
    val otherBitmap = getBitmapFromDrawable(otherDrawable)

    return bitmap.sameAs(otherBitmap)
  }

  private fun invokeStateListDrawable(drawable: Drawable, view: View): Drawable =
    if (drawable is StateListDrawable) {

      val index =
        drawable.findStateDrawableIndex(view.drawableState)

      drawable.getStateDrawable(index)!!

    } else drawable

  private fun getBitmapFromDrawable(drawable: Drawable): Bitmap {

    val bitmap: Bitmap = Bitmap.createBitmap(
      drawable.intrinsicWidth,
      drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
  }

  //endregion
}
