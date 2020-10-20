package com.example.innorussian.phrases
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innorussian.R
import java.util.*


class PhrasesParentAdapter(private val parents: List<PhrasesParentModel>) :
    RecyclerView.Adapter<PhrasesParentAdapter.ViewHolder>(), TextToSpeech.OnInitListener{
    private val viewPool = RecyclerView.RecycledViewPool()
    private var mTTs: TextToSpeech? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PhrasesParentAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.phrases_parent,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        val parent = parents[position]

        holder.englishView.text = parent.english
        holder.russianView.text = parent.russian
        holder.transcView.text = parent.transc


        mTTs = TextToSpeech(holder.itemView.context, this)

        holder.listenButton.setOnClickListener{
            speakOut(parent.russian)
        }

        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = PhrasesChildAdapter(parent.children)
            setRecycledViewPool(viewPool)
        }

        val isExpandable : Boolean = parents[position].isExpandable()
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val parent = parents[position]
            parent.expandable = !parent.isExpandable()
            notifyItemChanged(position)
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.findViewById(R.id.phrases_examples_layout)

        var englishView : TextView = itemView.findViewById(R.id.phrases_english)
        var russianView : TextView = itemView.findViewById(R.id.phrases_russian)
        var transcView : TextView = itemView.findViewById(R.id.phrases_transcription)
        var listenButton : ImageButton = itemView.findViewById(R.id.ib_listen)

        var linearLayout: LinearLayout = itemView.findViewById(R.id.phrases_linear_layout)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.phrases_expandable_layout)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = mTTs!!.isLanguageAvailable(Locale("ru"))
            Log.d("TTS", "ok");
        } else {
            Log.d("TTS", "error");
        }
    }

    private fun speakOut(text: String){
        mTTs!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}