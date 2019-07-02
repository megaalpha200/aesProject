package com.example.demo.app

import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    val dangerColor = c("#a94442")

    companion object {
        val heading by cssclass()
        val headingBox by cssclass()
        val executeBtn by cssclass()
        val executeBtnBox by cssclass()
        val borderDebug by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        headingBox {
            alignment = Pos.CENTER
        }

        executeBtnBox {
            alignment = Pos.CENTER
        }

        executeBtn {

        }

        borderDebug {
            borderColor += box(dangerColor)
            borderStyle += BorderStrokeStyle(StrokeType.INSIDE, StrokeLineJoin.MITER, StrokeLineCap.BUTT, 10.0, 0.0, listOf(25.0, 5.0))
            borderWidth += box(5.px)
        }
    }
}