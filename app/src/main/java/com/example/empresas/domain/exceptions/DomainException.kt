package com.example.empresas.domain.exceptions

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))

class MissingParamsException : DomainException("Params must not be null.")

class EmptyFieldException : DomainException("Campo obrigatório.")