# language: pt
# encoding: utf-8

Funcionalidade: Navegação no Site GZH
  Esse documento testa todos os cenários de navegação do site GZH

#Entra na noticia destaque das editorias que estão na tabela da linha 26
  @navegacao @editoria @tdc
  Esquema do Cenário: Entrar na materia destaque dentro da editoria
  -Entra na noticia destaque das editorias que estão na tabela
    Dado Que estou no site GZH
    Quando Clico no menu do site
    E Seleciono uma "<editoria>" para navegar
    Entao Verifico que estou na "<editoria>"
    E Finalizo a minha navegacao
    Exemplos:
    -Nomes das editorias que vai ser testada
      | editoria     |
      | Porto Alegre |
      #| Esportes     |




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
      | teste automatizado        |

