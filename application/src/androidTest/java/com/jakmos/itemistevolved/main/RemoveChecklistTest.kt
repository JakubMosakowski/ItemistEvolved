package com.jakmos.itemistevolved.main

import androidx.test.rule.ActivityTestRule
import com.jakmos.itemistevolved.AndroidTestData
import com.jakmos.itemistevolved.R
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.presentation.main.MainActivity
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotContains
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import org.junit.Rule
import org.junit.Test

class RemoveChecklistTest {

  //region Rules

  @get:Rule
  var activityRule = ActivityTestRule(MainActivity::class.java)

  //endregion

  //region Remove

  /**
   * Remove checklist.
   */
  @Test
  fun removeChecklist() {

    // Given
    val checklist = AndroidTestData.CHECKLISTS[1]
    createChecklist(AndroidTestData.CHECKLISTS[2])
    createChecklist(checklist)

    // When
    clickListItemChild(id.checklistsRv, 0, id.deleteBtn)

    // Then
    sleep(1000)
    assertNotContains(id.title, checklist.name)
    assertListItemCount(id.checklistsRv, 1)
  }

  /**
   * Undo checklist removal.
   */
  @Test
  fun undoRemoveChecklist() {

    // Given
    val checklist = AndroidTestData.CHECKLISTS[1]
    createChecklist(AndroidTestData.CHECKLISTS[2])
    createChecklist(checklist)
    val undoText = activityRule.activity.getString(R.string.home_undo)

    // When
    clickListItemChild(id.checklistsRv, 0, id.deleteBtn)
    sleep(1000)
    clickOn(undoText)

    // Then
    sleep(1000)
    assertListItemCount(id.checklistsRv, 2)
  }

  //endregion

  //region Empty

  /**
   * Show empty text after last checklist removal.
   */
  @Test
  fun showEmptyText() {

    // Given
    createChecklist(AndroidTestData.CHECKLISTS[2])

    // When
    clickListItemChild(id.checklistsRv, 0, id.deleteBtn)

    // Then
    assertDisplayed(id.emptyText)
  }

  //endregion
}
