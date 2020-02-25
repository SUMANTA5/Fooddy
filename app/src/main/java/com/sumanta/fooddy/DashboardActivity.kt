package com.sumanta.fooddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import com.sumanta.fooddy.activity.LoginActivity
import com.sumanta.fooddy.fragment.*
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
  // val titleName:String? = "Foddy"

    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView


    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

       /* if (intent != null){

           titleName = intent.getStringExtra("name")
        }*/

      //  title = titleName

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)


        //coll toolbar
        setUpToolbar()

        //all time open home fragment
        openHome()
       /* supportFragmentManager.beginTransaction()
            .replace(R.id.frame,HomeFragment())
            .addToBackStack("Home")
            .commit()
        supportActionBar?.title = "Home"*/

       //hamberger icon
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@DashboardActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //ClickListener in navigationView button
        navigationView.setNavigationItemSelectedListener {
            //checked item
            if (previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.home ->{
                   /* supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,HomeFragment())
                       // .addToBackStack("Home")
                        .commit()
                    supportActionBar?.title = "Home"*/
                    openHome()
                    drawerLayout.closeDrawers()
                   /* Toast.makeText(this@DashboardActivity,
                        "Home", Toast.LENGTH_SHORT
                    ).show()*/
                }
                R.id.myProfile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,ProfileFragment())
                        //.addToBackStack("Profile")
                        .commit()

                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                    /*Toast.makeText(this@DashboardActivity,
                        "myprofile", Toast.LENGTH_SHORT
                    ).show()*/
                }
                R.id.favoriteRestaurant ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,FavoriteFragment())
                        .addToBackStack("Favorite")
                        .commit()
                    supportActionBar?.title = "Favorite Restaurants"
                    drawerLayout.closeDrawers()
                   /* Toast.makeText(this@DashboardActivity,
                        "Favorite", Toast.LENGTH_SHORT
                    ).show()*/
                }
                R.id.orderHistory ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,HistoryFragment())
                        .addToBackStack("History")
                        .commit()
                    supportActionBar?.title = "History"
                    drawerLayout.closeDrawers()
                   /* Toast.makeText(this@DashboardActivity,
                        "History", Toast.LENGTH_SHORT
                    ).show()*/
                }
                R.id.faqs ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,FaqsFragment())
                        .addToBackStack("FAQs")
                        .commit()
                    supportActionBar?.title = "FAQs"
                    drawerLayout.closeDrawers()
                   /* Toast.makeText(this@DashboardActivity,
                        "Faqs", Toast.LENGTH_SHORT
                    ).show()*/
                }
                R.id.logOut -> {
                    /*Creating a confirmation dialog*/
                    val builder = AlertDialog.Builder(this@DashboardActivity)
                    builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want exit?")
                        .setPositiveButton("Yes") { _, _ ->
                            startActivity(Intent(this@DashboardActivity, LoginActivity::class.java))
                            Volley.newRequestQueue(this).cancelAll(this::class.java.simpleName)
                            ActivityCompat.finishAffinity(this)
                        }
                        .setNegativeButton("No") { _, _ ->
                            displayHome()
                        }
                        .create()
                        .show()                }
            }

            return@setNavigationItemSelectedListener true
        }

    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Fooddy"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
//ClickListener in manu button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    //all time open home fragment
    fun openHome(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title="Home"
        navigationView.setCheckedItem(R.id.home)
    }
//back Button
    override fun onBackPressed() {
    val frag = supportFragmentManager.findFragmentById(R.id.frame)
    when(frag){
        !is HomeFragment -> openHome()

        else -> super.onBackPressed()
    }

    }
    private fun displayHome() {
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        supportActionBar?.title = "All Restaurants"
        navigationView.setCheckedItem(R.id.home)
    }
}
