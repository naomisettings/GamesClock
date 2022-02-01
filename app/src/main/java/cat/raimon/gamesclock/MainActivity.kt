package cat.raimon.gamesclock

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cat.raimon.gamesclock.rummikub.RummyFragment

class MainActivity : AppCompatActivity() {

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        (fragment as? RummyFragment.IOnBackPressed)?.onBackPressed()?.not()?.let { isCanceled: Boolean ->
            if (!isCanceled) super.onBackPressed()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val navController = findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this,navController)


    }
/*
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
 */

}