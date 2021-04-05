package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
@Transactional
class CadastraAutorController(val autorRepository: AutorRepository,
                              val enderecoClient: EndrecoClient) {

    @Post
    fun cadastra(@Body @Valid request: CadastraAutorRequest) {
        println("Requisicao -> ${request}")

        // fazer uma req para um servico externo

        val enderecoResponse: HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)

        val autor: Autor = request.paraAutor(enderecoResponse.body()!!)
        autorRepository.save(autor)

        println("Autor -> ${autor.nome}")
    }

}