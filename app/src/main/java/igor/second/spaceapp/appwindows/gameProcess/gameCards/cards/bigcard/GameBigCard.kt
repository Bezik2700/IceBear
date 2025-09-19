package igor.second.spaceapp.appwindows.gameProcess.gameCards.cards.bigcard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import igor.second.spaceapp.R

@Composable
fun GameBigCard(
    @DrawableRes image: Int,
    text: String
) {
    Box(
        modifier = Modifier
            .padding(bottom = 24.dp)
            .height(280.dp)
    ) {
        // Background cards with shadow and smooth rotation
        Card(
            modifier = Modifier
                .rotate(45f)
                .size(width = 140.dp, height = 260.dp)
                .offset(x = (-20).dp, y = 10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.flip_side_card),
                contentDescription = "background card",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
            )
        }

        Card(
            modifier = Modifier
                .rotate(30f)
                .size(width = 140.dp, height = 260.dp)
                .offset(x = (-10).dp, y = 5.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.flip_side_card),
                contentDescription = "background card",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f))
            )
        }

        Card(
            modifier = Modifier
                .rotate(15f)
                .size(width = 140.dp, height = 260.dp)
                .offset(x = 0.dp, y = 0.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.flip_side_card),
                contentDescription = "background card",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceVariant)
            )
        }

        // Main card with elevation and border
        Card(
            modifier = Modifier
                .size(width = 150.dp, height = 270.dp)
                .offset(x = 0.dp, y = 0.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Card image with gradient overlay
                Image(
                    painter = painterResource(image),
                    contentDescription = "main card",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradient overlay for better text visibility
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )

                // Top value indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                        .size(36.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                // Bottom value indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                        .size(36.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                // Glow effect
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.1f),
                                    Color.Transparent
                                ),
                                center = Offset(0.7f, 0.3f),
                                radius = 300f
                            )
                        )
                )
            }
        }
        Box(
            modifier = Modifier
                .size(width = 160.dp, height = 280.dp)
                .align(Alignment.Center)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        center = Offset(0.5f, 0.5f),
                        radius = 200f
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
        )
    }
}
