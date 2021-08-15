package com.github.terry2001.codebuilder.services

import com.github.terry2001.codebuilder.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
