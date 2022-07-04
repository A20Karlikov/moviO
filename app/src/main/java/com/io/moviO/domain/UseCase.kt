package com.io.moviO.domain

interface UseCase<P, D> {

    suspend fun execute(param: P): D

}