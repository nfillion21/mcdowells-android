package pgm.poolp.mcdowells.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val YellowThemeLight = lightColorScheme(
    primary = yellow500,
    onPrimary = Color.Black,
    secondary = blue700,
    onSecondary = Color.White,
    surface = yellow500
)

private val YellowThemeDark = darkColorScheme(
    primary = yellow200,
    secondary = blue200,
    onSecondary = Color.Black,
    surface = yellowDarkPrimary
)

@Composable
fun YellowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        YellowThemeDark
    } else {
        YellowThemeLight
    }
    McDowellsTheme(colors, content)
}

@Composable
private fun McDowellsTheme(
    colorScheme:ColorScheme,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            //window.statusBarColor = colorScheme.primary.toArgb()
            //window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            WindowCompat.setDecorFitsSystemWindows(window, false)
            //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            //window.statusBarColor = Color.Transparent.toArgb()

            //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}