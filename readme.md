<h1 align="center"> Desafio DSMovie RestAssured </h1>

## Sobre o projeto:
<div align= "justify">

Este é um projeto de filmes e avaliações de filmes. A regra de negócio diz que a  visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count). 

###

## Modelo conceitual:

<div align= "center">

![ra](https://github.com/maririb749/desafio_dsmovie_restassured/assets/85500087/840cc2e7-02c9-4d01-b1b4-059d03ad717a)

</div> 

###

<div align= "justify">
  
O desafio consiste em implementar os testes de API  utilizando o RestAssured. Os testes estão logo abaixo;

</div> 

###


## MovieContollerRA:

 <ul>
  <ul>
    <li>findAllShouldReturnOkWhenMovieNoArgumentsGiven</li>
    <li>findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty</li>
    <li>findByIdShouldReturnMovieWhenIdExists</li>
    <li>findByIdShouldReturnNotFoundWhenIdDoesNotExist</li>
    <li>insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle</li>
    <li>insertShouldReturnForbiddenWhenClientLogged</li>
    <li>insertShouldReturnUnauthorizedWhenInvalidToken</li>
    </ul>
</ul>

###

## ScoreContollerRA:

 <ul>
  <ul>
    <li>saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist</li>
    <li>saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId</li>
    <li>saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero</li>
 </ul>
</ul>


