package id.co.octopus.library.core

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlin.math.ceil
import kotlin.math.roundToInt

object CommonUtils {

    private fun isCharAllowedSpace(c: Char): Boolean {
        return Character.isLetterOrDigit(c)
    }

    fun isOnline(mContext: Context): Boolean {
        val connectivity = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            for (networkInfo in info) if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    fun phoneNumberStartZero(phone: String): String {
        if (phone.startsWith("0")) return phone
        if (phone.startsWith("+62")) return "0" + phone.substring(3)
        if (phone.startsWith("62")) return "0" + phone.substring(2)
        return if (phone.startsWith("8")) "0$phone" else phone
    }

    fun isValidPhoneNumber(phone: String): Boolean {
        return phone.length in 9..14 && phone.startsWith("0")
    }

    fun isValidTokenPln(noMeter: String): Boolean {
        return noMeter.length in 8..20
    }

    fun capitalize(str: String?): String? {
        return if (str == null) str else str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    fun runDelayHome(activity: Activity?) {
        val h = Handler()
        h.postDelayed({}, 1000)
    }

    fun runDelayBackPressed(activity: Activity) {
        val h = Handler()
        h.postDelayed({ activity.onBackPressed() }, 500)
    }

    fun px2dip(context: Context, pxValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return pxValue / scale + 0.5f
    }

    fun getFirstWord(firstWord: String): String {
        var firstWord = firstWord
        if (firstWord.contains(" ")) {
            firstWord = firstWord.substring(0, firstWord.indexOf(" "))
        }
        return firstWord
    }

    fun checkPermissions(activity: FragmentActivity?): Boolean {
        val ACCESS_LOCATION = ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
        val WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            return false
        } else if (WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return ACCESS_LOCATION == PackageManager.PERMISSION_GRANTED
    }

    fun roundDecimal(number: Double): Double {
        return (number * 100.0).roundToInt() / 100.0
    }

    private fun Double.roundDownToMultipleOf(base: Double): Double = base * kotlin.math.floor(this / base)

    fun decreaseQuantity(qty: Double): Double {
        var quantity = qty
        if (qty % 1 == 0.5 || qty % 1 == 0.0) {
            quantity -= 0.5
        } else {
            quantity = qty.roundDownToMultipleOf(0.5)
        }

        return quantity
    }

    fun increaseQuantity(qty: Double): Double {
        var quantity = qty
        if (qty % 1 == 0.5 || qty % 1 == 0.0) {
            quantity += 0.5
        } else {
            quantity = (ceil(qty * 2) / 2)
        }
        return quantity
    }
}