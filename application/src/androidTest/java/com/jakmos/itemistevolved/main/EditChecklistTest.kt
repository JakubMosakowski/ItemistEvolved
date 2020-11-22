package com.jakmos.itemistevolved.main

import androidx.test.rule.ActivityTestRule
import com.jakmos.itemistevolved.AndroidTestData
import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.presentation.main.MainActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotContains
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
import org.junit.Rule
import org.junit.Test

class EditChecklistTest {

  //region Rules

  @get:Rule
  var activityRule = ActivityTestRule(MainActivity::class.java)

  //endregion

  //region Edit

  /**
   * Edit checklist title
   */
  @Test
  fun editChecklistTitle() {

    // Given
    createChecklist(AndroidTestData.CHECKLISTS[2])
    val newTitle = "New title"

    // When
    clickOn(id.editBtn)
    writeTo(id.titleEditText, newTitle)
    clickOn(id.submitBtn)

    // Then
    assertContains(id.title, newTitle)
  }

  /**
   * Add subsection.
   */
  @Test
  fun addSubsection() {

    // Given
    val checklist = AndroidTestData.CHECKLISTS[2]
    createChecklist(checklist)
    val checklistTitle = checklist.name
    val newSubsection = "New subsection"

    // When
    clickOn(id.editBtn)
    writeTo(id.lineEditText, newSubsection)
    clickOn(id.addBtn)
    clickOn(id.submitBtn)

    // Then
    clickOn(checklistTitle)
    assertContains(id.checkbox, newSubsection)
  }

  /**
   * Remove subsection.
   */
  @Test
  fun removeSubsection() {

    // Given
    val checklist = AndroidTestData.CHECKLISTS[2]
    val subsectionTitle = checklist.subsections.first().text
    createChecklist(checklist)

    // When
    clickOn(id.editBtn)
    closeKeyboard()
    clickListItemChild(id.subsectionsRv, 0, id.deleteBtn)
    clickOn(id.submitBtn)

    // Then
    clickOn(checklist.name)
    assertNotContains(id.checkbox, subsectionTitle)
  }

  /**
   * Reorder subsection.
   */
  @Test
  fun reorderSubsection() {
    //TODO think how you can mock drag and drop action efficiently.
  }


  //endregion
}
