sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object ForgotPassword : Screen("forgot_password_screen")
    object MainApp : Screen("main_app_route") // Yahan aapka BottomBarNavigation aayega
}