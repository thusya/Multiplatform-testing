package login

data class LoginState(
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isDataValid: Boolean = false
)