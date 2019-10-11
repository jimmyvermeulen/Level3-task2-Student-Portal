package com.example.studentportal

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.content.Intent
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startAddPortalActivity()
        }

        initViews()
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(this,2)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val offset : Int = resources.getDimension(R.dimen.grid_padding).toInt();
                outRect.top = offset
                outRect.left =  offset
                outRect.right = offset
                outRect.bottom = offset
            }
        })
    }

    private fun startAddPortalActivity() {
        val intent = Intent(this, AddPortalActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }


}

