package pgm.poolp.mcdowells

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pgm.poolp.mcdowells.ui.theme.McDowellsTheme
import pgm.poolp.mcdowells.ui.theme.YellowTheme
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Onboarding() {
    YellowTheme {
        Scaffold(
            topBar = { AppBar() },
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Choose topics that interest you",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 32.dp,
                    )
                )

                LazyHorizontalStaggeredGrid(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(height = 240.dp)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                    horizontalItemSpacing = 0.dp,
                    rows = StaggeredGridCells.Fixed(3)) {

                    items(topics) {item ->
                        TopicChip(topic = item)
                    }
                }

                Text(
                    text = "Choose topics that interest you",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 32.dp,
                    )
                )
            }
        }
    }
}

@Composable
private fun AppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        /*
        Image(
            painter = painterResource(id = OwlTheme.images.lockupLogo),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        */
    }
}

@Composable
private fun TopicChip(topic: Topic) {

    Surface(
        modifier = Modifier
            .height(72.dp)
            .padding(4.dp)
            .wrapContentHeight(),
        shadowElevation = McDowellsTheme.elevations.card,
        tonalElevation = McDowellsTheme.elevations.card,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(12.dp),
    ) {
        Row() {
            Image(
                painterResource(id = R.drawable.ic_grain),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 72.dp, height = 72.dp)
                    .aspectRatio(1f)
            )
            Column {
                Text(
                    text = topic.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.courses.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Onboarding")
@Composable
private fun OnboardingPreview() {
    Onboarding()
}

@Preview("Topic Chip")
@Composable
private fun TopicChipPreview() {
    YellowTheme {
        TopicChip(topics.first())
    }
}
