/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* %sb=comment-by:mohammad-Aljelmawi 2024-03-27 */
package com.example.android.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.WordListAdapter.WordViewHolder

/* %sb: WORDS_COMPARATOR is used to check if item value needs to be replaced or not */
class WordListAdapter : ListAdapter<Word, WordViewHolder>(WORDS_COMPARATOR) {

    /* %sb: ViewHolder is an object inside a RecyclerView
            RecyclerView will create n of them using `create` to fill the space on screen 
            then will reuse them one by one using `bind` to show data scrolling
    */
    /* %sb: (parent|RecyclerView, viewType|type of parent as int) -> 
        ViewHolder( recyclerview_item as a class; 
        this will be passed on item value changed
        with an index for the target Word in ListAdapter to be displayed) using `onBindViewHolder` */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    /* %sb: reuse WordViewHolder(recyclerview_item) at postion(index of Word object in ListAdapter) */
    /* %sb: holder is the WordViewHolder(recyclerview_item as a class) to be reused */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        /* %sb: getItem is like: ListAdapter['Word'][postion|index] -> Word */
        val current = getItem(position)
        /* %sb: holder.bind as function defiend in WordViewHolder class wich takes a text */
        holder.bind(current.word)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* %sb: get the text-view(i.e. a label) inside itemView(recyclerview_item) */
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        /* %sb: static access */
        companion object {
            /* %sb: create a new WordViewHolder(recyclerview_item as a class) in given parent(RecyclerView) */
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}
