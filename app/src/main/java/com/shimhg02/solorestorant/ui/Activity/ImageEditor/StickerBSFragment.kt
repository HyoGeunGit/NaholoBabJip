package com.shimhg02.solorestorant.ui.Activity.ImageEditor

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shimhg02.solorestorant.R

@Suppress("DEPRECATION")
class StickerBSFragment : BottomSheetDialogFragment() {
    private var mStickerListener: StickerListener? = null
    fun setStickerListener(stickerListener: StickerListener?) {
        mStickerListener = stickerListener
    }

    interface StickerListener {
        fun onStickerClick(bitmap: Bitmap?)
    }

    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(
            bottomSheet: View,
            newState: Int
        ) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(
            bottomSheet: View,
            slideOffset: Float
        ) {
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(
            context,
            R.layout.fragment_bottom_sticker_emoji_dialog,
            null
        )
        dialog.setContentView(contentView)
        val params =
            (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
        (contentView.parent as View).setBackgroundColor(
            resources.getColor(
                android.R.color.transparent
            )
        )
        val rvEmoji: RecyclerView = contentView.findViewById(R.id.rvEmoji)
        val gridLayoutManager = GridLayoutManager(activity, 3)
        rvEmoji.layoutManager = gridLayoutManager
        val stickerAdapter = StickerAdapter()
        rvEmoji.adapter = stickerAdapter
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    inner class StickerAdapter :
        RecyclerView.Adapter<StickerAdapter.ViewHolder>() {
        var stickerList = intArrayOf(R.drawable.aa, R.drawable.aa)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_sticker, parent, false)
            return ViewHolder(
                view
            )
        }

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            holder.imgSticker.setImageResource(stickerList[position])
        }

        override fun getItemCount(): Int {
            return stickerList.size
        }

        inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            var imgSticker: ImageView

            init {
                imgSticker = itemView.findViewById(R.id.imgSticker)
                itemView.setOnClickListener {
                    if (mStickerListener != null) {
                        mStickerListener!!.onStickerClick(
                            BitmapFactory.decodeResource(
                                resources,
                                stickerList[layoutPosition]
                            )
                        )
                    }
                    dismiss()
                }
            }
        }
    }

//    private fun convertEmoji(emoji: String): String {
//        var returnedEmoji = ""
//        returnedEmoji = try {
//            val convertEmojiToInt = emoji.substring(2).toInt(16)
//            getEmojiByUnicode(convertEmojiToInt)
//        } catch (e: NumberFormatException) {
//            ""
//        }
//        return returnedEmoji
//    }

    private fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}