package com.example.innorussian.home_fragment.phrasebook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innorussian.R
import kotlinx.android.synthetic.main.phrasebook_main.*

class PhrasebookFragment : Fragment(R.layout.phrasebook_main){

    lateinit var recyclerView : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
    }


    private fun initRecycler(){
        recyclerView = phrasebook_rv!!

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL, false)
            adapter = PhrasebookParentAdapter(
                ParentDataFactory.getParents(40)
            )
            setHasFixedSize(true)
        }

    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ibShop.setOnClickListener{
            if(cvShop_hidden_view.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvShop, AutoTransition())
                cvShop_hidden_view.visibility = View.GONE
            }
            else{
                TransitionManager.beginDelayedTransition(cvShop, AutoTransition())
                cvShop_hidden_view.visibility = View.VISIBLE
            }

        }


        ibSportComplex.setOnClickListener{
            if(cvSportsComplex_hidden_view.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvSportsComplex, AutoTransition())
                cvSportsComplex_hidden_view.visibility = View.GONE
            }
            else{
                TransitionManager.beginDelayedTransition(cvSportsComplex, AutoTransition())
                cvSportsComplex_hidden_view.visibility = View.VISIBLE
            }

        }

        ibDorms.setOnClickListener{
            if(cvDorms_hidden_view.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvDorms, AutoTransition())
                cvDorms_hidden_view.visibility = View.GONE
            }
            else{
                TransitionManager.beginDelayedTransition(cvDorms, AutoTransition())
                cvDorms_hidden_view.visibility = View.VISIBLE
            }

        }

        ibUniversity.setOnClickListener{
            if(cvUniversity_hidden_view.visibility == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cvUniversity, AutoTransition())
                cvUniversity_hidden_view.visibility = View.GONE
            }
            else{
                TransitionManager.beginDelayedTransition(cvUniversity, AutoTransition())
                cvUniversity_hidden_view.visibility = View.VISIBLE
            }

        }
    }*/
}