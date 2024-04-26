package com.example.presentation.wishlist_screen

import ProductCreateRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    //navController:NavHostController ,
    gebwt:List<ProductCreateRequest>,
    modifier: Modifier
) {
    // val ballArticles = remember { mutableStateOf<List<Article>>(emptyList()) }
    // LaunchedEffect(null) {
    //     ballArticles.value = bookmarksService.getPinned()
    // }
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 192.dp)) {
        items(gebwt.size) { fav ->
            CardMarks(fav)
        }
    }


}
@Composable
fun EmptyState() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
        )
        Text(
            text = "You haven't saved any articles yet. Start reading and bookmarking them now",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF333647), textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(start = 100.dp, end = 100.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMarks(
    article: ProductCreateRequest,
    onClick: (ProductCreateRequest) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFDDDDDD))
            .clickable {
                onClick(article)
            }
    ) {
        AsyncImage(
            model = article.img,
            placeholder = painterResource(R.drawable.category),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column() {
            Text(
                text = (article.price ?: "No category"),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF7C82A1),
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = article.title?: "No category",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF333647), fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


@Preview
@Composable
fun PreviewWiWishlist() {
    WishlistScreen(FavoritesData.conversationSample, modifier = Modifier.fillMaxSize())
}