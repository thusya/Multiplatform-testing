package home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mpowa.composeapp.generated.resources.Res
import mpowa.composeapp.generated.resources.arrow_left
import mpowa.composeapp.generated.resources.ic_hero
import mpowa.composeapp.generated.resources.ic_project
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import signup.UserType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun DetailsScreen(navController: NavController) {

    val tabs = listOf("Project", "Hero", "Manager")
    val images = listOf(Res.drawable.ic_hero, Res.drawable.ic_project, Res.drawable.ic_hero)

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    Column {

        TopAppBar(
            actions = {
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Impactor",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFF191927),
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 27.sp,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(6f)
                )
                Spacer(Modifier.weight(1f))
            },
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.arrow_left),
                        contentDescription = "",
                        tint = Color(0xFF69718A)
                    )
                }
            },
        )

        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color(0xFFEDEDED)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            tabs.forEachIndexed { index, tab ->
                UserType(
                    text = tab, selectedTabIndex == index, index
                ) {
                    selectedTabIndex = it
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(resource = images[selectedTabIndex]),
            contentDescription = "Selected User Type Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}