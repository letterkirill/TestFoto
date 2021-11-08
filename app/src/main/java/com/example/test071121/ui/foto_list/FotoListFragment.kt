package com.example.test071121.ui.foto_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.test071121.R

class FotoListFragment: Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(FotoListViewModel::class.java)}
    private var clickListener: FotoListAdapter.OnFotoClickListener? = null
    private var mainListener: OnFotoClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(activity is OnFotoClickListener){
            mainListener = activity as OnFotoClickListener

            clickListener = object: FotoListAdapter.OnFotoClickListener {
                override fun onClickFoto(url: String) {
                    mainListener?.onClickFoto(url)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_foto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FotoListAdapter(LayoutInflater.from(context), clickListener)

        val columns = getResources().getInteger(R.integer.count_columns);
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_foto)
        val layoutManager = GridLayoutManager(recyclerView.context, columns, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.getFotoList()
        }

        viewModel.fotoList.observe(viewLifecycleOwner){
            adapter.setItems(it)

            if(swipeRefresh.isRefreshing){
                swipeRefresh.isRefreshing = false
            }
        }

        viewModel.getFotoList()
    }

    interface OnFotoClickListener {
        fun onClickFoto(url: String)
    }
}