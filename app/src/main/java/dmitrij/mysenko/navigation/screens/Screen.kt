package dmitrij.mysenko.navigation.screens

import dmitrij.mysenko.navigation.navigation.NestedGraph

sealed class Screen(val route: String){

    object SignInScreen1: Screen("sign_in_screen_1")
    object SignInScreen2: Screen("sign_in_screen_2")

    object SignUpScreen1: Screen("sign_up_screen_1")
    object SignUpScreen2: Screen("sign_up_screen_2")
    object SignUpScreen3: Screen("sign_up_screen_3")

    object MainScreen: Screen("main_screen")

    object BottomNavScreen: Screen("bottom_nav_screen")

    object BottomNavGoogleScreen: Screen("bottom_nav_google_screen")
    object BottomNavScreen11: Screen("bottom_nav_screen_1_1")
    object BottomNavScreen12: Screen("bottom_nav_screen_1_2")
    object BottomNavScreen21: Screen("bottom_nav_screen_2_1")
    object BottomNavScreen22: Screen("bottom_nav_screen_2_2")
    object BottomNavScreen31: Screen("bottom_nav_screen_3_1")
    object BottomNavScreen32: Screen("bottom_nav_screen_3_2")

    object BottomNavYoutubeScreen: Screen("bottom_nav_youtube_screen")
    object BottomNavYoutubeScreen11: Screen("bottom_nav_screen_youtube_1_1")
    object BottomNavYoutubeScreen12: Screen("bottom_nav_screen_youtube_1_2")
    object BottomNavYoutubeScreen21: Screen("bottom_nav_screen_youtube_2_1")
    object BottomNavYoutubeScreen22: Screen("bottom_nav_screen_youtube_2_2")
    object BottomNavYoutubeScreen31: Screen("bottom_nav_screen_youtube_3_1")
    object BottomNavYoutubeScreen32: Screen("bottom_nav_screen_youtube_3_2")
    object BottomNavYoutubeScreen41: Screen("bottom_nav_screen_youtube_4_1")
    object BottomNavYoutubeScreen42: Screen("bottom_nav_screen_youtube_4_2")

    object BottomNavCustomScreen: Screen("bottom_nav_custom_screen")
    object BottomNavCustomScreen11: Screen("bottom_nav_custom_screen_1_1")
    object BottomNavCustomScreen12: Screen("bottom_nav_custom_screen_1_2")
    object BottomNavCustomScreen21: Screen("bottom_nav_custom_screen_2_1")
    object BottomNavCustomScreen22: Screen("bottom_nav_custom_screen_2_2")
    object BottomNavCustomScreen31: Screen("bottom_nav_custom_screen_3_1")
    object BottomNavCustomScreen32: Screen("bottom_nav_custom_screen_3_2")

    object DialogScreen: Screen("dialogs_screen")

    object PopupScreen: Screen("popups_screen")

    object SheetScreen: Screen("sheets_screen")
    object ModalBottomSheetScreen: Screen("modal_bottom_sheet_screen")
    object HalfModalBottomSheetScreen: Screen("half_modal_bottom_sheet_screen")
    object ScaffoldBottomSheetScreen: Screen("scaffold_bottom_sheet_screen")
    object BottomSheetLikePlayerScreen: Screen("bottom_sheet_like_player_screen")

    object TabsScreen: Screen("tabs_screen")
    object DefaultTabsScreen: Screen("default_tabs_screen")
    object SwipeableTabsScreen: Screen("swipeable_tabs_screen")
    object CustomTabsScreen: Screen("custom_tabs_screen")

    object DrawerScreen: Screen("drawer_screen")
    object DrawerTopScreen: Screen("drawer_top_screen")
    object DrawerLatestScreen: Screen("drawer_latest_screen")
    object DrawerUserScreen: Screen("drawer_user_screen")
    object DrawerImageScreen: Screen("drawer_image_screen")

    object LookaheadLayoutScreen: Screen("lookahead_layout_screen")
    object ColumnToRowScreen: Screen("column_to_row_screen")

    object OtherScreen: Screen("other_screen")
    object WallpaperScreen: Screen("wallpaper_screen")
    object GridLayoutScreen: Screen("grid_layout_screen")

    object DragAndDropScreen: Screen("drag_and_drop_screen")
    object SimpleDragAndDropScreen: Screen("simple_drag_and_drop_screen")
    object TwoListsDragAndDropScreen: Screen("two_lists_drag_and_drop_screen")

    object CollapsingScreen: Screen("collapsing_screen")
    object CollapsingHalfVisibleRectScreen: Screen("collapsing_half_visible_rect_screen")
    object CollapsingClassicScreen: Screen("collapsing_classic_screen")

    object IosActivityScreen: Screen("ios_activity_screen")

    object MasksScreen: Screen("masks_screen")

    object Player13Screen: Screen("player_13_screen")

    object TableScreen: Screen("table_screen")

    object ChartScreen: Screen("chart_screen")

    object AfterMainScreen: Screen("after_main_screen")

    fun makeRoute(root: NestedGraph) = "${root.makeRoute()}/$route"

    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }

    fun withArgs(root: NestedGraph, vararg args: String) = buildString {
        append(makeRoute(root))
        args.forEach { arg ->
            append("/$arg")
        }
    }
}
