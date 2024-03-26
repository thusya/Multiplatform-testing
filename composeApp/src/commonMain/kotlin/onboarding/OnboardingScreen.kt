package onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import components.LoginButton
import mpowa.composeapp.generated.resources.Res
import mpowa.composeapp.generated.resources.onboarding_first
import mpowa.composeapp.generated.resources.onboarding_second
import navigation.NavigationScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    onSystemBarsVisibilityChanged: (Boolean) -> Unit = { false }
) {
    onSystemBarsVisibilityChanged(false)

    DisposableEffect(Unit) {
        onDispose {
            onSystemBarsVisibilityChanged(true)
        }
    }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    VerticalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) {

        when (it) {
            0 -> FirstScreen(pagerState = pagerState)
            else -> SecondScreen(navController = navController)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun FirstScreen(pagerState: PagerState) {
    var navigateToNextScreen by remember { mutableStateOf(false) }

    LaunchedEffect(navigateToNextScreen) {
        if (navigateToNextScreen) {
            pagerState.animateScrollToPage(1)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(resource = Res.drawable.onboarding_first),
            contentDescription = "",
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            OnBoardingButton(
                modifier = Modifier,
                text = "Hero",
            ) {
                navigateToNextScreen = true
            }

            OnBoardingButton(
                modifier = Modifier,
                text = "Impactor",
            ) {
                navigateToNextScreen = true
            }

            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SecondScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(resource = Res.drawable.onboarding_second),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            LoginButton(
                modifier = Modifier.padding(bottom = 26.dp),
                text = "Login",
                color = Color(0xFF353537)
            ) {
                navController.navigate(NavigationScreen.Login.route)
            }

            LoginButton(
                modifier = Modifier.padding(bottom = 38.dp),
                text = "Sign up",
                color = Color(0xFF000000)
            ) {
                navController.navigate(NavigationScreen.SignUp.route)
            }
        }
    }
}

@Composable
fun OnBoardingButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(50.dp)
            .padding(horizontal = 20.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = 12.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = { onClick.invoke() }) {
        Text(
            text = text, style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            )
        )
    }
}