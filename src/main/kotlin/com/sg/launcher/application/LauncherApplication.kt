package com.sg.launcher.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("com.sg.launcher.repository")
@EntityScan("com.sg.launcher.repository.entity")
@ComponentScan("com.sg.launcher")
class LauncherApplication

fun main(args: Array<String>) {
    runApplication<LauncherApplication>(*args)
}
