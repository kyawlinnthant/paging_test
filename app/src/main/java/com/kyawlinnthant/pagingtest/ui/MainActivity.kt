package com.kyawlinnthant.pagingtest.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyawlinnthant.pagingtest.R
import com.kyawlinnthant.pagingtest.paging.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainNavigator {

    private val vm: MainViewModel by viewModel()
    private var adapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        observe()
    }

    override fun observe() {
        vm.data.observe(this, Observer { adapter?.submitList(it) })
    }

    override fun setupRecyclerView() {
        adapter = NewsAdapter { getItemClick(it) }.apply {
            rc.layoutManager = LinearLayoutManager(this@MainActivity)
            rc.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            rc.adapter = this
        }
    }

    private fun getItemClick(position: Int) {
        val item = adapter?.getItemId(position)
        Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
    }


}
