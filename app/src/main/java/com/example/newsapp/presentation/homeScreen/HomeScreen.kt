package com.example.newsapp.presentation.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedButton
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.presentation.Components.NewsItem
import com.example.newsapp.presentation.Components.SearchHeader
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = koinViewModel(),
    navController: NavHostController
) {
    val state = newsViewModel.state.value

    val drawerState = rememberDrawerState(DrawerValue.Open)

    val modalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                newsViewModel.getCategories().map {
                    TextButton(
                        onClick = { newsViewModel.selectCategory(it.value) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = if (state.selectedCategory == it.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)


                    ) {
                        Text(
                            text = it.name,
                            color = MaterialTheme.colorScheme.background,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Button(
                    onClick = { newsViewModel.getNewsByCategory(state.selectedCategory) },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text(text = "Apply")
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                SearchHeader(
                    newsViewModel,
                    onClick = {
                        scope.launch { modalState.show() }
                    }
                )
            }
        ) { paddingValue ->
            if (state.isLoading) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onSurface)
                }

            }

            if (state.error != "") {
                Text(text = state.error)
            }
            if (state.news != null && state.news!!.articles.isNotEmpty()) {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    contentPadding = PaddingValues(
                        horizontal = paddingValue.calculateStartPadding(
                            LayoutDirection.Ltr
                        ), vertical = paddingValue.calculateTopPadding()
                    )
                ) {
                    items(state.news!!.articles) { item ->
                        NewsItem(article = item, navController = navController)

                    }
                }
            }
        }
    }
}

