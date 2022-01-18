package dtu.projekt.phonefreedom

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.*

class ShowSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_show_setting)

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.app_name)


        val btn_changeLang = findViewById<Button>(R.id.btn_changelang)
        btn_changeLang.setOnClickListener {
            showChangeLang()
        }
        val btn_animation= findViewById<Button>(R.id.Animation1)
            btn_animation.setOnClickListener { showAnimation()}
    }

    private fun showChangeLang(){

        val listItems = arrayOf("English","Danish")

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems,-1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                recreate()
                    Toast.makeText(this, "English selected.", Toast.LENGTH_SHORT).show()

            } else if (which == 1) {
                setLocate("da")
                recreate()
                    Toast.makeText(this, "Danish selected.", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()

        }
        val mDialog = mBuilder.create()

        mDialog.show()
    }

    private fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My Lang", Lang)
        editor.apply()
        //loadLocate()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

    private fun showAnimation(){

        val listItems = arrayOf("ON","OFF")

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Animation")
        mBuilder.setSingleChoiceItems(listItems,-1) { dialog, tilstand ->
            if (tilstand == 0) {
                Toast.makeText(this, "Animation turned on.", Toast.LENGTH_SHORT).show()

            } else if (tilstand == 1) {
                Toast.makeText(this, "Animation turned off.", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()

        mDialog.show()
    }



}