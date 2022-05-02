package dev.adriankuta.callmonitor.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.adriankuta.callmonitor.R
import dev.adriankuta.callmonitor.databinding.ItemCallLogBinding
import dev.adriankuta.callmonitor.model.CallLog

class CallLogAdapter() :
    RecyclerView.Adapter<CallLogAdapter.ViewHolder>() {

    private val callLogs: MutableList<CallLog> = mutableListOf()

    fun submitItems(newItems: List<CallLog>) {
        callLogs.clear()
        callLogs.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCallLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CallLog) {
            val res = binding.root.context.resources
            binding.contactName.text = item.name
            binding.callDuration.text =
                res.getQuantityString(R.plurals.call_duration, item.duration, item.duration)
        }
    }

    override fun getItemCount(): Int = callLogs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(callLogs[position])
    }


    private fun ViewGroup.inflate(): ItemCallLogBinding =
        ItemCallLogBinding.inflate(LayoutInflater.from(this.context), this, false)

}