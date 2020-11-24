package com.jakmos.itemistevolved.main

import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.domain.model.Checklist
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep

fun createChecklist(checklist: Checklist) {

  // Verify add clicked.
  clickOn(id.addItemButton)

  // Add title.
  writeTo(id.titleEditText, checklist.name)

  // Add subsection.
  checklist.subsections.forEach {
    writeTo(id.lineEditText, it.text)
    clickOn(id.addBtn)
  }

  // Submit
  closeKeyboard()
  clickOn(id.submitBtn)
  sleep(500)
}

