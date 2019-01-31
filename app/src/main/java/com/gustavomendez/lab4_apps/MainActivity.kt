package com.gustavomendez.lab4_apps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import com.gustavomendez.lab4_apps.Fragments.HomeFragment
import com.gustavomendez.lab4_apps.Fragments.ProjectFragment
import com.gustavomendez.lab4_apps.Fragments.WebViewFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = "Lab 4"

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        mDrawerLayout = findViewById(R.id.drawer_layout)


        nav_view.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment()).commit()
                }
                R.id.nav_wallet -> {
                    Toast.makeText(this, "Wallet", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, ProjectFragment()).commit()
                }
                R.id.nav_offer -> {
                    Toast.makeText(this, "Offer", Toast.LENGTH_LONG).show()
                }
                R.id.nav_setting -> {
                    Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show()
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)

        (fragment as? ProjectFragment).let {
            it?.didClickProject = { url, title ->
                run {
                    //Url becomes from the Project Fragment, when user clicks the item in Recycler View of Projects
                    val webViewFragment = WebViewFragment.newInstance(url, title)
                    supportFragmentManager.beginTransaction().replace(R.id.content_frame, webViewFragment).commit()
                }
            }
        }
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            WebViewFragment.webViewRepo.canGoBack() -> WebViewFragment.webViewRepo.goBack()
            else -> super.onBackPressed()
        }
    }

    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
