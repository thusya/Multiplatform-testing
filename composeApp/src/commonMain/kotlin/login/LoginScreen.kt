package login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import components.LoginButton
import components.OutLineTextField
import mpowa.composeapp.generated.resources.Res
import mpowa.composeapp.generated.resources.ic_email
import mpowa.composeapp.generated.resources.ic_mpowa_full
import mpowa.composeapp.generated.resources.lock_circle
import navigation.NavigationScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = LoginViewModel()

    val loginState = viewModel.loginState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            Image(painter = painterResource(resource = Res.drawable.ic_mpowa_full), contentDescription = "")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Welcome back", style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Empowering people everywhere to engineer abundance",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9898B1),
                )
            )

            Spacer(modifier = Modifier.height(40.dp))
            var emailId by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            OutLineTextField(
                text = emailId,
                onTextChange = { changeText ->
                    emailId = changeText
                    viewModel.loginDataChanged(emailId, password)
                },
                labelText = "Email",
                resource = Res.drawable.ic_email,
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutLineTextField(
                text = password,
                onTextChange = { changeText ->
                    password = changeText
                    viewModel.loginDataChanged(emailId, password)
                },
                labelText = "Password",
                resource = Res.drawable.lock_circle,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(24.dp))

            LoginButton(
                text = "Sign in",
                color = Color(0xFF353537)
            ) {
                //handle the login click
                navController.navigate(NavigationScreen.Details.route)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            val annotatedText = buildAnnotatedString {
                append("Don't have an account? ")

                withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                    )
                ) {
                    append("Sign up")
                }
            }

            ClickableText(
                text = annotatedText, onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        navController.navigate(NavigationScreen.SignUp.route)
                    }
                },
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
//    LoginScreen()
}