package com.jakmos.itemistevolved.presentation.widget

//import android.view.MenuItem
//import androidx.navigation.NavController
//import androidx.navigation.ui.NavigationUI
//import co.windly.limbo.utility.view.changeVisibility
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
////region Helper
//
//fun setupWithNavController(
//  view: BottomNavigationView, controller: NavController, supportedViewIds: List<Int>,
//  onItemSelected: (menuItem: MenuItem) -> Unit) {
//
//  // Attach destination listener.
//  controller.addOnDestinationChangedListener { _, destination, _ ->
//
//    // Search for menu item.
//    val menuItem = view.menu.findItem(destination.id)
//
//    // Update item selection.
//    menuItem?.isChecked = true
//
//    // Change bottom navigation visibility based destinations.
//    view.changeVisibility(destination.id in supportedViewIds)
//  }
//
//  // Attach item selection listener.
//  view.setOnNavigationItemSelectedListener { onItemSelected.invoke(it).let { false } }
//
//  // Configure no action should happen for item reselection.
//  view.setOnNavigationItemReselectedListener { /* No-op. */ }
//}
//
//fun onNavigationCompleted(
//  view: BottomNavigationView, controller: NavController, destinationId: Int) {
//
//  // Search for menu item.
//  val menuItem =
//    view.menu.findItem(destinationId)
//
//  // Navigate to selected item.
//  NavigationUI.onNavDestinationSelected(menuItem, controller)
//
//  // Update item selection.
//  menuItem.isChecked = true
//}
//
////endregion
