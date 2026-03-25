package BeatrizRosa05.com.github.navigation_trabalho_554805

import BeatrizRosa05.com.github.navigation_trabalho_554805.screens.LoginScreen
import BeatrizRosa05.com.github.navigation_trabalho_554805.screens.MenuScreen
import BeatrizRosa05.com.github.navigation_trabalho_554805.screens.PedidosScreen
import BeatrizRosa05.com.github.navigation_trabalho_554805.screens.PerfilScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import BeatrizRosa05.com.github.navigation_trabalho_554805.ui.theme.Navigation_Android_TrabalhoTheme
import android.R.attr.defaultValue
import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation_Android_TrabalhoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        //EXPLICAÇÃO:
                        composable(
                            //Argumento opcional na rota
                            route = "pedidos?cliente={cliente}",
                            //Define o valor padrão do argumento caso nenhum seja passado
                            arguments = listOf(navArgument("cliente") {
                                //Captura o valor do argumento
                                defaultValue = "Cliente Genérico"
                            })
                        ) {
                            //Captura o valor do argumento (it.arguments?.getString("Cliente")
                            PedidosScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                it.arguments?.getString("cliente")
                            )

                        }
                        //A rota de navegação não é mais fixa. o {nome} é um argumento dinamico passado pela navegação
                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome") { type = NavType.StringType },
                                navArgument("idade") { type = NavType.IntType }
                            )
                        ) {
                            //Captura o "nome" passado, "?" evita erro caso seja nulo, "Usuário Genérico" é o valor padrão
                            val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
                            //Passa também a idade, mesma coisa do nome
                            val idade: Int? = it.arguments?.getInt("idade", 0)
                            //Chama a tela de perifl passando o nome. "!!" garante que o vaor não é nulo
                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                nome!!,
                                idade!!
                            )

                            ///EXPLICAÇÃO GERAL: a tela de perfil deixou de ser genérica e passou a receber um nome via rota, tornando-se reutilizável para qualquer usuário.
                        }

                    }
                }
            }
        }
    }
}