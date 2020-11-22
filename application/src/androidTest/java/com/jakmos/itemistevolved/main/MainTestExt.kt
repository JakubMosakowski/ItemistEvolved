package com.jakmos.itemistevolved.main

import com.jakmos.itemistevolved.R.id
import com.jakmos.itemistevolved.domain.model.Checklist
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo

fun createChecklist(checklist: Checklist) {

  //TODO find more efficient way to fill db.

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
  clickOn(id.submitBtn)
}

