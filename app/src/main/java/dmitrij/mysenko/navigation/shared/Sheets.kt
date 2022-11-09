package dmitrij.mysenko.navigation.shared

import android.content.Context
import android.graphics.Point
import android.os.Build.VERSION_CODES
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .navigationBarsPadding()
    ) {
        Text(
            text = "Sheet Content",
            fontWeight = FontWeight.W700,
        )

        Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pretium dapibus magna in maximus. Sed purus magna, pellentesque id nulla eget, blandit ullamcorper libero. Suspendisse elementum mi orci, id finibus lectus venenatis id. Donec pretium erat sagittis sollicitudin imperdiet. Curabitur bibendum odio ac aliquet gravida. Maecenas non tincidunt dolor. Sed at metus convallis, dignissim orci in, rhoncus lorem. Nam at odio dapibus, consectetur mauris ac, tincidunt ex.",
            modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Composable
fun BottomSheetContentMoreThenHalfScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height((LocalConfiguration.current.screenHeightDp / 2 + 200).dp)
            .padding(all = 16.dp)
    ) {
        Text(text = "Sheet Content", fontWeight = FontWeight.W700)

        Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pretium dapibus magna in maximus. Sed purus magna, pellentesque id nulla eget, blandit ullamcorper libero. Suspendisse elementum mi orci, id finibus lectus venenatis id. Donec pretium erat sagittis sollicitudin imperdiet. Curabitur bibendum odio ac aliquet gravida. Maecenas non tincidunt dolor. Sed at metus convallis, dignissim orci in, rhoncus lorem. Nam at odio dapibus, consectetur mauris ac, tincidunt ex.",
            modifier = Modifier.padding(horizontal = 16.dp))
    }
}

