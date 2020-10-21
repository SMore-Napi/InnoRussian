package com.example.innorussian.phrasebook

class ParentModel (
    val drawableID : Int,
    val nameOfSection : String,
    val children : List<PhrasebookChildModel>,
    var expandable : Boolean = false
){
    fun isExpandable() : Boolean{
        return expandable
    }

    fun setExpandable(expandable: Boolean) : Void?{
        this.expandable = expandable
        return null
    }
}