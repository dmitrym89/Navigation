package dmitrij.mysenko.navigation.navigation

sealed class NestedGraph(val route: String, private val root: NestedGraph? = null){
    object SignIn: NestedGraph("sign_in")
    object SignUp: NestedGraph("sign_up")
    object Main: NestedGraph("main")
    object BottomNav: NestedGraph("bottom_nav")
    object BottomNav1: NestedGraph("bottom_nav_google_1")
    object BottomNav2: NestedGraph("bottom_nav_google_2")
    object BottomNav3: NestedGraph("bottom_nav_google_3")
    object BottomNavCustom1: NestedGraph("bottom_nav_custom_1")
    object BottomNavCustom2: NestedGraph("bottom_nav_custom_2")
    object BottomNavCustom3: NestedGraph("bottom_nav_custom_3")
    object BottomNavYoutube1: NestedGraph("bottom_nav_google_youtube_1")
    object BottomNavYoutube2: NestedGraph("bottom_nav_google_youtube_2")
    object BottomNavYoutube3: NestedGraph("bottom_nav_google_youtube_3")
    object BottomNavYoutube4: NestedGraph("bottom_nav_google_youtube_4")
    object Sheets: NestedGraph("sheets")
    object Tabs: NestedGraph("tabs")
    object AnimationTransition: NestedGraph("animation_transition")

    fun makeRoute(): String = buildString {
        root?.let {
            append(it.makeRoute())
        }
        append(route)
    }
}
