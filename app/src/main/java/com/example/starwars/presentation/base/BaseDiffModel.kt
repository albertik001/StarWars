package com.example.starwars.presentation.base

interface BaseDiffModel<T> {
    val name: T?
    override fun equals(other: Any?): Boolean
}