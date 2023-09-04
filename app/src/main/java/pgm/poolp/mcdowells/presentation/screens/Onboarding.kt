package pgm.poolp.mcdowells.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import pgm.poolp.core.domain.BigMick
import pgm.poolp.mcdowells.R
import pgm.poolp.mcdowells.presentation.ui.theme.McDowellsTheme
import pgm.poolp.mcdowells.presentation.ui.theme.YellowTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding() {
    YellowTheme {
        Scaffold(
            topBar = { AppBar() },
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {  innerPadding ->
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                //verticalArrangement = Arrangement.SpaceBetween
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

                Spacer(modifier = Modifier.weight(1.0f))

                val bigMickViewModel: BigMickViewModel = viewModel()
                BigMicksList(flow = bigMickViewModel.bigMicks)

                Spacer(modifier = Modifier.weight(1.0f))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BigMicksList(flow: Flow<PagingData<BigMick>>) {
    val bigMickItems = flow.collectAsLazyPagingItems()
    LazyHorizontalStaggeredGrid(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(height = 240.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalItemSpacing = 0.dp,
        rows = StaggeredGridCells.Fixed(3)) {

        items(
            count = bigMickItems.itemCount,
            //key = bigMickItems.itemKey { it.id }
        ) {index ->
            val item = bigMickItems[index]
            TopicChip(bigMick = item!!)
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
private fun TopicChip(bigMick: BigMick) {

    Surface(
        modifier = Modifier
            .height(72.dp)
            .padding(4.dp),
        shadowElevation = McDowellsTheme.elevations.card,
        tonalElevation = McDowellsTheme.elevations.card,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
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
                    text = bigMick.title,
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
                        text = bigMick.description,
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