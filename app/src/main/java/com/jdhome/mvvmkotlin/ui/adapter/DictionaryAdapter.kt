package com.jdhome.mvvmkotlin.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jdhome.mvvmkotlin.R
import com.jdhome.mvvmkotlin.networking.model.ResponseData
import com.jdhome.mvvmkotlin.utility.UtilComparison
import kotlinx.android.synthetic.main.adapter_dictionary.view.*
import timber.log.Timber
import java.util.*


class DictionaryAdapter(
    private val context: Context?,
    private val apiData: MutableList<ResponseData>?
) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    private var findText: String? = null
    private var rowIndex: Int? = null
    private var msgString: String? = null


    fun setFilter(
        searchText: String,
        voiceString: String?,
        row: Int
    ) {
        findText = searchText
        msgString = voiceString
        rowIndex = row


        notifyDataSetChanged()
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtWord: AppCompatCheckedTextView? = itemView.tv_Word
        val txtFrequency: AppCompatCheckedTextView? = itemView.tv_Frequency
        val imgBulb: LinearLayout? = itemView.changeBulb


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_dictionary,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (apiData?.size != null)
            apiData.size
        else
            0
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtWord?.text = apiData?.get(position)?.word
        holder.txtFrequency?.text = apiData?.get(position)?.frequency.toString()

        if (!TextUtils.isEmpty(findText)) {
            if (rowIndex == position
                    /*&& rowIndex == position*/) {

                Timber.e("Match Found$rowIndex")
                holder.imgBulb?.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.colorAccent
                    )
                )

                //+1 up frequency{Logic Left}
                holder.txtFrequency?.text =
                    apiData?.get(position)?.frequency?.plus(0.inc()).toString()

                //Position Change
                rowIndex?.let {
                    Timber.e("Index$rowIndex")


                    if (it > 0 && position != 0) {
                        Collections.swap(apiData, position, it - 1)

                    } else {
                        Collections.swap(apiData, position, it)
                    }

                }//it-1

            } else {
               // Toast.makeText(context, "No Match Found", Toast.LENGTH_SHORT).show()
                Timber.e("No Match Found$rowIndex")

                holder.imgBulb?.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.colorPrimaryDark
                    )
                )
            }
        }


    }
}