package signup

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import components.LoginButton
import components.OutLineTextField
import components.PasswordTextField
import mpowa.composeapp.generated.resources.Res
import mpowa.composeapp.generated.resources.ic_email
import mpowa.composeapp.generated.resources.ic_mpowa_full
import mpowa.composeapp.generated.resources.ic_person
import navigation.NavigationScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isTermsAccepted by remember { mutableStateOf(false) }

    var newPassword by remember { mutableStateOf("") }
    var verifyPassword by remember { mutableStateOf("") }
    var passwordMismatchError by remember { mutableStateOf(newPassword != verifyPassword) }

    val tabs = listOf("Hero", "Impactor")

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(painter = painterResource(resource = Res.drawable.ic_mpowa_full), contentDescription = "")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Welcome", style = MaterialTheme.typography.headlineMedium.copy(
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
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
            OutLineTextField(
                text = firstName,
                onTextChange = { firstName = it },
                labelText = "First Name",
                resource = Res.drawable.ic_person
            )

            OutLineTextField(
                text = firstName,
                onTextChange = { firstName = it },
                labelText = "Last Name",
                resource = Res.drawable.ic_person
            )

            OutLineTextField(
                text = email,
                onTextChange = { email = it },
                labelText = "Email",
                resource = Res.drawable.ic_email
            )

            PasswordTextField(
                labelText = "Enter Password",
                password = newPassword,
                onPasswordChange = { newPassword = it },
            )

            PasswordTextField(
                labelText = "Re-Enter Password",
                password = verifyPassword,
                onPasswordChange = {
                    verifyPassword = it
                    passwordMismatchError = newPassword != verifyPassword
                },
                isError = passwordMismatchError,
                errorMessage = if (passwordMismatchError) "Password don't match" else null,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .minimumInteractiveComponentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF000000),
                        uncheckedColor = Color(0xFF69718A)
                    ),
                    checked = isTermsAccepted,
                    onCheckedChange = { isTermsAccepted = it },
                )
                TermsAndPrivacyText(
                    modifier = Modifier
                        .weight(1f),
                    tncUrl = "", privacyUrl = ""
                ){
                    //navController.navigate(NavigationScreen.PdfWebView.route)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            LoginButton(
                text = "Create Account",
                color = Color(0xFF353537)
            ) {
                navController.navigate(NavigationScreen.Details.route)
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TermsAndPrivacyText(
    tncUrl: String,
    privacyUrl: String,
    modifier: Modifier = Modifier,
    onTnCAndPrivacyClick: (String) -> Unit = {},
) {
    val clickTerms = "term "
    val clickPrivacy = "privacy"
    val termsAndConditions = "Terms of Service"
    val privacyPolicy = "Privacy Policy"
    val partBetweenTermsAndPrivacy = "and"
    val termsAgree = "I agree to your"

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(color = Color(0xFF9898B1)),
        ) {
            append("$termsAgree ")
        }
        pushStringAnnotation(tag = clickTerms, annotation = termsAndConditions)
        withStyle(
            style = SpanStyle(
                color = Color(0xFF615FCD),
                textDecoration = TextDecoration.Underline,
            ),
        ) {
            append(termsAndConditions)
        }
        pop()
        withStyle(
            style = SpanStyle(color = Color(0xFF9898B1)),
        ) {
            append(" $partBetweenTermsAndPrivacy ")
        }
        pushStringAnnotation(tag = clickPrivacy, annotation = privacyPolicy)
        withStyle(
            style = SpanStyle(
                color = Color(0xFF615FCD),
                textDecoration = TextDecoration.Underline,
            ),
        ) {
            append(privacyPolicy)
        }
        pop()
    }

    ClickableText(
        modifier = modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(tag = clickTerms, start = offset, end = offset)
                .firstOrNull()?.let {
                    onTnCAndPrivacyClick(tncUrl)
                }
            annotatedString
                .getStringAnnotations(tag = clickPrivacy, start = offset, end = offset)
                .firstOrNull()?.let {
                    onTnCAndPrivacyClick(privacyUrl)
                }
        },
    )
}

@Composable
fun RowScope.UserType(
    text: String,
    isSelected: Boolean,
    index: Int, onClick: (Int) -> Unit
) {

    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Color.Black
        } else {
            Color(0xFF4F4F4F)
        },
        animationSpec = tween(
            500, 500, easing = LinearEasing
        ),
        label = "",
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .noRippleClickable { onClick.invoke(index) },
        contentAlignment = Alignment.Center
    ) {

        if (isSelected) {
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp, horizontal = 4.dp),
                elevation = CardDefaults.elevatedCardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                content = {})
        }

        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            ),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = tabTextColor,
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent)
        )
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
//    SignUpScreen()
}