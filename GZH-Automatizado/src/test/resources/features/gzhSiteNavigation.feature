# language: pt
# encoding: utf-8

Funcionalidade: Navegação no Site GZH
  Esse documento testa todos os cenários de navegação do site GZH

  # Cenarios de Navegação Geral do GZH
  @navegacao @editoria @tdc
#Entra em cada editoria que esta no menu do site e verificar se todas estão abrindo
  Cenario: Acessar todas as editorias que estao no menu e validar se estão abrindo
  - Cenario abri cada editoria que esta no menu do site e verificar se todas estao abrindo
    Dado Que estou no site GZH
    Quando Clico no menu do site
    Entao Clico e valido cada editoria
    E Finalizo a minha navegacao


#Clicar no exibir mais dos editorias que estão na tabela da linha 38
  @navegacao @editoria @tdc
  Esquema do Cenário: Clicar no "exibir mais"
  -Clicar no exibir mais dos editorias que estão na tabela
    Dado Que estou no site GZH
    Quando Clico no menu do site
    E Seleciono uma "<editoria>" para navegar
    Entao Finalizo a minha navegacao
    Exemplos:
    -Nomes das editorias que vai ser testada
      | editoria |
      | esportes |


  @navegacao @home @tdc
#Acessar o site e clicar no botao de busca e realizar busca pelo home
  Esquema do Cenario: Realizar busca pelo home
  -Acessar o site e clicar no botao de busca e realizar busca pelo home
    Dado Que estou no site GZH
    Quando Clico no botao buscar
    E Preencho o campo da busca com o "<termo>"
    Entao valido ser realizo a busca
    E Finalizo a minha navegacao
    Exemplos:
    -Usuario e senha que vai ser usado para logar
      | termo                     |
      | teste automatizado6656565 |
      | teste automatizado        |

