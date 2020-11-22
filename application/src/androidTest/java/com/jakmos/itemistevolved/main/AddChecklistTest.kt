package com.jakmos.itemistevolved.main

import androidx.test.rule.ActivityTestRule
import com.jakmos.itemistevolved.AndroidTestData.Companion.CHECKLISTS
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.presentation.main.MainActivity
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Rule
import org.junit.Test

class AddChecklistTest {

  //region Rules

  @get:Rule
  var activityRule = ActivityTestRule(MainActivity::class.java)

  //endregion

  //region Setup

  companion object {
    val TITLE = CHECKLISTS[2].name
    val SUBSECTIONS = CHECKLISTS[2].subsections.map { it.text }
  }

  //endregion

  //region Add

  /**
   * Verify if add checklist feature works.
   */
  @Test
  fun addChecklistTest() {

    // Verify add clicked.
    clickOn(id.addItemButton)

    assertDisplayed(id.submitBtn)

    // Add title.
    writeTo(id.titleEditText, TITLE)

    // Add subsection.
    writeTo(id.lineEditText, SUBSECTIONS[0])
    clickOn(id.addBtn)
    writeTo(id.lineEditText, SUBSECTIONS[1])
    clickOn(id.addBtn)
    writeTo(id.lineEditText, SUBSECTIONS[2])
    clickOn(id.addBtn)

    assertListItemCount(id.subsectionsRv, SUBSECTIONS.size)

    // Submit
    clickOn(id.submitBtn)

    // Then.
    assertListItemCount(id.checklistsRv, 1)
    assertContains(id.title, TITLE)
  }

  //endregion
}
