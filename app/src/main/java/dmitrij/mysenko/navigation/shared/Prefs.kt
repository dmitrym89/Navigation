package dmitrij.mysenko.navigation.shared

import android.content.Context
import dmitrij.mysenko.navigation.MainActivity

fun Context.readValue(key: String):String?{
    return (this as? MainActivity)?.getPreferences(Context.MODE_PRIVATE)?.getString(key, null)
}


fun Context.writeValue(key: String, value: String){
    val prefs = (this as? MainActivity)?.getPreferences(Context.MODE_PRIVATE) ?: return
    with(prefs.edit()){
        putString(key, value)
        apply()
    }
}