package com.example.amphibians.data

interface AppContainer {
    val  amphibiansRepository: AmphibiansRepository
}
class DefaultAppContainer : AppContainer {
    override val amphibiansRepository: AmphibiansRepository by lazy {

    }
}