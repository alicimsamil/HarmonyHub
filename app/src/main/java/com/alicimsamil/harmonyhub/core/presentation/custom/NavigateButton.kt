package com.alicimsamil.harmonyhub.core.presentation.custom

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import com.alicimsamil.harmonyhub.R

class NavigateButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var icon: Drawable? = null
    private var backgroundColor: Int = Color.BLUE

    init {

        getTypedAttributes(attrs)

        setButtonFeatures()

    }

    private fun getTypedAttributes(attrs: AttributeSet?){
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.NavigateButton)
        icon = attributes.getDrawable(R.styleable.NavigateButton_customIcon)
        backgroundColor =
            attributes.getColor(R.styleable.NavigateButton_customBackgroundColor, Color.BLUE)
        attributes.recycle()
    }

    private fun setButtonFeatures(){
        val marginHorizontal = resources.getDimensionPixelSize(R.dimen.space_small)
        val marginVertical = resources.getDimensionPixelSize(R.dimen.space_extra_small)
        setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            icon ?: ResourcesCompat.getDrawable(resources, R.drawable.right_arrow, context.theme),
            null
        )
        setPadding(marginHorizontal, marginVertical, marginHorizontal, marginVertical)
        setBackgroundColor(backgroundColor)
    }

    fun setIcon(drawable: Drawable?) {
        icon = drawable
        setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null)
    }
}