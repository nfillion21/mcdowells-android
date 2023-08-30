package pgm.poolp.mcdowells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pgm.poolp.mcdowells.ui.theme.YellowTheme
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {
    YellowTheme {
        Scaffold(
            topBar = { AppBar() },
            containerColor = MaterialTheme.colorScheme.surface
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Choose topics that interest you",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 32.dp
                    )
                )
                /*
                TopicsGrid(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                )
                */
                Spacer(Modifier.height(56.dp)) // center grid accounting for FAB
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

/*
@Composable
private fun TopicsGrid(modifier: Modifier = Modifier) {
    StaggeredGrid(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        topics.forEach { topic ->
            TopicChip(topic = topic)
        }
    }
}
*/

/*
@Composable
private fun TopicChip(topic: Topic) {
    val (selected, onSelected) = remember { mutableStateOf(false) }
    val topicChipTransitionState = topicChipTransition(selected)

    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = OwlTheme.elevations.card,
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(
                topicChipTransitionState.cornerRadius
            )
        )
    ) {
        Row(modifier = Modifier.toggleable(value = selected, onValueChange = onSelected)) {
            Box {
                NetworkImage(
                    url = topic.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 72.dp, height = 72.dp)
                        .aspectRatio(1f)
                )
                if (topicChipTransitionState.selectedAlpha > 0f) {
                    Surface(
                        color = pink500.copy(alpha = topicChipTransitionState.selectedAlpha),
                        modifier = Modifier.matchParentSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary.copy(
                                alpha = topicChipTransitionState.selectedAlpha
                            ),
                            modifier = Modifier
                                .wrapContentSize()
                                .scale(topicChipTransitionState.checkScale)
                        )
                    }
                }
            }
            Column {
                Text(
                    text = topic.name,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Icon(
                            painter = painterResource(R.drawable.ic_grain),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(12.dp)
                        )
                        Text(
                            text = topic.courses.toString(),
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
*/

@Composable
private fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val rowWidths = IntArray(rows) { 0 } // Keep track of the width of each row
        val rowHeights = IntArray(rows) { 0 } // Keep track of the height of each row

        // Don't constrain child views further, measure them with given constraints
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = max(rowHeights[row], placeable.height)

            placeable
        }

        // Grid's width is the widest row
        val width = rowWidths.maxOrNull()?.coerceIn(constraints.minWidth, constraints.maxWidth)
            ?: constraints.minWidth
        // Grid's height is the sum of each row
        val height = rowHeights.sum().coerceIn(constraints.minHeight, constraints.maxHeight)

        // y co-ord of each row
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }
        layout(width, height) {
            // x co-ord we have placed up to, per row
            val rowX = IntArray(rows) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.place(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
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
        //TopicChip(topics.first())
    }
}
