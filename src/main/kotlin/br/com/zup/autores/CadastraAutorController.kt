package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
@Transactional
class CadastraAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastra(@Body @Valid request: CadastraAutorRequest) {
        println("Requisicao -> ${request}")

        val autor: Autor = request.paraAutor()
        autorRepository.save(autor)

        println("Autor -> ${autor.nome}")
    }

}