package id.co.octopus.library.core.textpicker

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseTextPickerView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defAttributeSet: Int) : super(context, attributeSet, defAttributeSet)

    internal var fadeInDuration = FADE_IN_DURATION
    internal var fadeOutDuration = FADE_OUT_DURATION
    internal var fadeInAlpha = FADE_IN_ALPHA
    internal var fadeOutAlpha = FADE_OUT_ALPHA

    internal fun startFadeAnimation(views: List<View>, duration: Long, alpha: Float) {
        views.forEach {
            it.animate().alpha(alpha).setDuration(duration)
                .withEndAction { it.alpha = alpha }.start()
        }
    }

    abstract fun fadeView(view: RecyclerView, duration: Long, alpha: Float)

    internal open fun onItemClicked(position: Int, rv: RecyclerView) {
        rv.smoothScrollToPosition(position)
    }

    internal fun LinearSnapHelper.getSnapPosition(rv: RecyclerView): Int {
        val view = findSnapView(rv.layoutManager) ?: return 0
        return rv.getChildAdapterPosition(view)
    }

    internal fun RecyclerView.addListeners(updateViewData: (viewId: Int) -> Unit) {
        addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_DOWN) fadeView(rv, fadeInDuration, fadeInAlpha)
                else if (e.action == MotionEvent.ACTION_UP && rv.scrollState == RecyclerView.SCROLL_STATE_IDLE) fadeView(
                    rv,
                    fadeOutDuration,
                    fadeOutAlpha
                )
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> fadeView(recyclerView, fadeInDuration, fadeInAlpha)
                    RecyclerView.SCROLL_STATE_IDLE -> fadeView(recyclerView, fadeOutDuration, fadeOutAlpha)
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    updateViewData.invoke(recyclerView.getCurrentPosition())
                }
            }
        })
    }

    fun setFadeAnimation(
        fadeInDuration: Long = this.fadeInDuration,
        fadeOutDuration: Long = this.fadeOutDuration,
        fadeInAlpha: Float = this.fadeInAlpha,
        fadeOutAlpha: Float = this.fadeOutAlpha
    ) {
        this.fadeInDuration = fadeInDuration
        this.fadeOutDuration = fadeOutDuration
        this.fadeInAlpha = fadeInAlpha
        this.fadeOutAlpha = fadeOutAlpha
    }

    companion object {
        const val FADE_IN_DURATION = 300L
        const val FADE_OUT_DURATION = 1000L
        const val FADE_IN_ALPHA = .3f
        const val FADE_OUT_ALPHA = .7f
    }

    fun RecyclerView?.getCurrentPosition() : Int {
        return (this?.layoutManager as SlowLinearLayoutManager).findLastVisibleItemPosition()
    }
}
