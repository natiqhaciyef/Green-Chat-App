package com.natiqhaciyef.greenchatapp.view.components

//https://freebiefy.com/free-travel-app-ui-design/
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.natiqhaciyef.greencamp.domain.util.classes.NavItemModel
import com.natiqhaciyef.greenchatapp.R
import com.natiqhaciyef.greenchatapp.ui.theme.AppGreen
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun BottomShadow(
    alpha: Float = 0.1f, height: Dp = 8.dp,
    padding: Dp = 0.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = padding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Black.copy(alpha = alpha),
                        Color.Transparent,
                    )
                )
            )
    )
}

@Composable
fun BottomShadow(modifier: Modifier) {
    Box(
        modifier = modifier
    )
}



//@Preview
@ExperimentalPagerApi
@Composable
fun <T>CustomViewPager(list: MutableList<T> = mutableListOf()) {
    val pagerState = rememberPagerState(
        initialPage = 0,
//        pageCount = list.size
    )

    LaunchedEffect(key1 = Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 40.dp),
        count = list.size
    ) { page ->

        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 30.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            val item = list[page]
            // item
        }
    }
}



@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun SocialMediaRow(
    text: String,
    image: Int,
    textColor: Color = AppGreen,
    color: Color = Color.White,
    content: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(55.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable {
                content()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Google",
                modifier = Modifier
                    .size(25.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

//@Composable
//fun AboutUsView() {
//    val context = LocalContext.current
//    Spacer(modifier = Modifier.height(20.dp))
//    Text(
//        text = "Haqqımızda qısa məlumat",
//        fontSize = 20.sp,
//        fontWeight = FontWeight.Bold,
//        color = Color.Black,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 20.dp)
//    )
//
//    Spacer(modifier = Modifier.height(10.dp))
//
//    Text(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 15.dp),
//        text = context.getString(R.string.info),
//        textAlign = TextAlign.Center,
//        fontSize = 17.sp,
//        fontWeight = FontWeight.Medium
//    )
//}

