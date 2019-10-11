package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViews() {
        btnAddPortal.setOnClickListener { onSaveClick() }
    }

    private fun onSaveClick() {
        if (etTitle.text.toString().isNotBlank() && etUrl.text.toString().isNotBlank()) {
            val reminder = Portal(etTitle.text.toString(), etUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, reminder)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"Fill in all the fields"
                , Toast.LENGTH_SHORT).show()
        }
    }

}
