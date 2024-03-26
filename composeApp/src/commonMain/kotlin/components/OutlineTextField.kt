package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpowa.composeapp.generated.resources.Res
import mpowa.composeapp.generated.resources.ic_password
import mpowa.composeapp.generated.resources.password_visibility
import mpowa.composeapp.generated.resources.password_visibility_off
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OutLineTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    resource: DrawableResource,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(
                labelText,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9898B1)
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(resource = resource),
                contentDescription = "leadingIcon",
                tint = Color(0xFF9898B1)
            )
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF9898B1),
            unfocusedBorderColor = Color(0xFF9898B1),
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PasswordTextField(
    password: String,
    labelText: String,
//    passwordPlaceHolderText: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = password,
            label = {
                Text(
                    labelText,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9898B1)
                    )
                )
            },
            onValueChange = onPasswordChange,
//            placeholder = {
//                Text(
//                    text = passwordPlaceHolderText,
//                    style = TextStyle(
//                        color = Color(0xFF69718A),
//                        fontSize = 14.sp,
//                        lineHeight = 21.sp,
//                        fontWeight = FontWeight.Normal,
//                        textAlign = TextAlign.Center
//                    ),
//                )
//            },
            singleLine = true,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (passwordVisibility) {
                        painterResource(resource = Res.drawable.password_visibility)
                    } else {
                        painterResource(resource = Res.drawable.password_visibility_off)
                    }
                val description = if (passwordVisibility) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = image,
                        contentDescription = description,
                        tint = Color(0xFF69718A)
                    )
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(resource = Res.drawable.ic_password),
                    contentDescription = "Password",
                    tint = Color(0xFF69718A)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFDFE2ED),
                focusedBorderColor = Color(0xFFDFE2ED),
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}