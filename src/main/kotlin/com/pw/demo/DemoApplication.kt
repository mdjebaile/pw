package com.pw.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Controller
@ResponseBody
class CustomerController(private val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun customers(): Iterable<Customer> = customerRepository.findAll()

    @GetMapping("/customers/{name}")
    fun customersByName(@PathVariable name: String): Iterable<Customer> = customerRepository.findByName(name)
}

interface CustomerRepository : CrudRepository<Customer, Int> {
    fun findByName(name: String): Iterable<Customer>
}

data class Customer(
    @Id
    val id: Int,
    val name: String,
)
