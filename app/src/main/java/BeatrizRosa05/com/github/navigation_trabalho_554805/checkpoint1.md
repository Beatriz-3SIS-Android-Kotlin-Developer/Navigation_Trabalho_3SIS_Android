DESCRIÇÃO DO PROJETO:
Aplicativo Android desenvolvido com Jetpack Compose que demonstra a navegação entre telas utilizando Navigation Compose. 
O projeto foi recriado passo a passo a partir do exercício feito em sala, com o objetivo de compreender o fluxo de navegação com NavController e, 
nesta etapa, evoluído para incluir a passagem de parâmetros entre telas.
O app possui 4 telas: LoginScreen, MenuScreen, PerfilScreen e PedidosScreen.

OBJETIVO DA PROVA:
Demonstrar na prática como passar dados entre telas usando Navigation Compose, explorando duas abordagens: 
parâmetros obrigatórios via rota dinâmica e parâmetros opcionais via query string. 
A partir do exercício base feito em sala, onde as telas eram estáticas e genéricas. O projeto foi evoluído para que cada tela receba e 
exiba dados dinâmicos, tornando-se reutilizável para qualquer contexto.

EXPLICAÇÃO DE CADA EVOLUÇÃO IMPLEMENTADA:
**MainActivity — Configuração do Grafo de Navegação
O que era antes: As rotas "pedidos" e "perfil" eram fixas e simples, sem nenhum argumento declarado:
O que mudou: O NavHost passou a ser o ponto central de toda a configuração de parâmetros. Cada rota que recebe dados precisa declarar seus argumentos aqui antes de repassá-los para a tela.

As mudanças foram:

**PedidosScreen - O que era antes: A rota era "pedidos" e a tela não recebia informação alguma sobre qual cliente estava sendo consultado.
O que mudou: A rota passou a aceitar um parâmetro opcional via query string ("pedidos?cliente={cliente}"). Se nenhum valor for passado, o app usa o valor padrão "Cliente Genérico" automaticamente — sem quebrar a navegação.
As mudanças foram:

A rota usa o formato ?param={param} em vez de /{param}, o que torna o argumento opcional
Foi declarado defaultValue = "Cliente Genérico" no navArgument, usado quando nenhum valor é fornecido
O valor é capturado com it.arguments?.getString("cliente") e repassado como String? (nullable)
A navegação no MenuScreen passou a ser navigate("pedidos?cliente=Cliente XPTO")
O texto exibido passou a mostrar "PEDIDOS - $cliente" com o nome dinâmico

**PerfilScreen - A rota "perfil" virou "perfil/{nome}/{idade}" com uma lista de navArgument definindo o tipo de cada um
A rota "pedidos" virou "pedidos?cliente={cliente}" com defaultValue declarado no navArgument
Os valores capturados com it.arguments são extraídos dentro do bloco do composable e repassados como parâmetros nas chamadas de PerfilScreen e PedidosScreen
O LoginScreen e o MenuScreen não precisaram de argumentos declarados pois não recebem dados via rota

**MenuScreen — Ponto de Partida da Navegação com Parâmetros
O que era antes: Os botões navegavam para rotas fixas sem passar nenhum dado:
kotlinonClick = { navController.navigate("perfil") }
onClick = { navController.navigate("pedidos") }
O que mudou: O MenuScreen passou a ser responsável por montar a URL com os valores concretos embutidos. É aqui que os dados entram na rota antes de chegarem na tela de destino.
As mudanças foram:

O botão Perfil passou para navigate("perfil/Fulano de Tal/27") — o nome e a idade são inseridos diretamente na URL, correspondendo aos segmentos {nome} e {idade} definidos na rota do NavHost
O botão Pedidos passou para navigate("pedidos?cliente=Cliente XPTO") — o nome do cliente é passado como query string, correspondendo ao {cliente} definido na rota
O botão Sair não teve alteração, pois navega para "login" sem parâmetros

**LoginScreen — Sem Alterações
A LoginScreen não sofreu nenhuma mudança. Ela não recebe nem envia dados via rota — sua única responsabilidade é navegar para o Menu ao clicar em "ENTRAR" com navController.navigate("menu"), exatamente como estava no exercício de sala.