package com.example.innorussian.learn_fragment.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innorussian.R

class LearnParentAdapter(private val parents: List<LearnParentModel>) :
    RecyclerView.Adapter<LearnParentAdapter.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.learn_parent,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        val parent = parents[position]

        holder.nameView.text = parent.nameOfSection


        holder.imageView.setImageResource(parent.drawableID)

        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = LearnChildAdapter(parent.children)
            setRecycledViewPool(viewPool)
        }

        val isExpandable : Boolean = parents[position].isExpandable()
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val parent = parents[position]
            if (!parent.expandable){
                val mEnlargeAnimation : Animation = AnimationUtils.loadAnimation(holder.imageView.context, R.anim.enlarge)
                holder.imageView.startAnimation(mEnlargeAnimation)
            } else {
                val mShrinkAnimation : Animation = AnimationUtils.loadAnimation(holder.imageView.context, R.anim.shrink)
                holder.imageView.startAnimation(mShrinkAnimation)
            }
            parent.expandable = !parent.isExpandable()
            notifyItemChanged(position){
                if (!parent.expandable){
                    val mEnlargeAnimation : Animation = AnimationUtils.loadAnimation(holder.imageView.context, R.anim.enlarge)
                    holder.imageView.startAnimation(mEnlargeAnimation)
                } else {
                    val mShrinkAnimation : Animation = AnimationUtils.loadAnimation(holder.imageView.context, R.anim.shrink)
                    holder.imageView.startAnimation(mShrinkAnimation)
                }
            }
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.findViewById(R.id.learn_topics_layout)

        var imageView : ImageView = itemView.findViewById(R.id.learn_section_image)
        var nameView : TextView = itemView.findViewById(R.id.learn_section_name)

        var linearLayout: LinearLayout = itemView.findViewById(R.id.learn_linear_layout)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.learn_expandable_layout)
    }
}