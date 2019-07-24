package com.example.demo.view

import com.example.demo.app.Styles
import AesFunctions.aesMainFunctions
import AesFunctions.aesMainFunctions.CipherMode
import AesFunctions.aesMainFunctions.TextMode
import AesFunctions.AesMain
import javafx.geometry.Orientation
import javafx.scene.control.*
import javafx.scene.input.Clipboard.getSystemClipboard
import tornadofx.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.lang.StringBuilder
import java.util.*
import javax.swing.JOptionPane





class AesMainView : View("AES Encryptor/Decryptor") {
    var inputTextTextField: TextArea? = null
    var keyTextField: TextField? = null
    var outputTextHexTextField: TextField? = null
    var outputTextStrTextField: TextArea? = null
    var outputTextHexCopyButton: Button? = null
    var outputTextStringCopyButton: Button? = null
    var inputTextStrRadioButton: RadioButton? = null
    var inputTextHexRadioButton: RadioButton? = null
    var keyStrRadioButton: RadioButton? = null
    var keyHexRadioButton: RadioButton? = null
    var encryptRadioButton: RadioButton? = null
    var decryptRadioButton: RadioButton? = null
    var debugTextArea: TextArea? = null

    val inputTextModeToggleGroup = ToggleGroup()
    val KeyTextModeToggleGroup = ToggleGroup()
    val encryptDecryptToggleGroup = ToggleGroup()


    override val root = vbox {
        hbox {
            label("$title using Kotlin") {
                addClass(Styles.heading)
            }
            addClass(Styles.headingBox)
        }
        form {
            fieldset("Inputs", labelPosition = Orientation.HORIZONTAL) {
                field("Input:") {
                    inputTextTextField = textarea {
                        prefHeight = 15.0
                    }
                    inputTextStrRadioButton = radiobutton("String", inputTextModeToggleGroup) { isSelected = true }
                    inputTextHexRadioButton = radiobutton("Hex", inputTextModeToggleGroup) {  }
                }
                field("Key:") {
                    keyTextField = textfield { }
                    keyStrRadioButton = radiobutton("String", KeyTextModeToggleGroup) { isSelected = true }
                    keyHexRadioButton = radiobutton("Hex", KeyTextModeToggleGroup) {  }
                }
                field("Encrypt or Decrypt?") {
                    encryptRadioButton = radiobutton("Encrypt", encryptDecryptToggleGroup) { isSelected = true }
                    decryptRadioButton = radiobutton("Decrypt", encryptDecryptToggleGroup) {  }
                }
            }
            hbox {
                button("Execute") {
                    action { executeBtnOnClick() }
                }
                addClass(Styles.executeBtnBox)
            }
            fieldset("Outputs", labelPosition = Orientation.HORIZONTAL) {
                field("Output Text (Hex):") {
                    outputTextHexTextField = textfield { }
                    outputTextHexTextField!!.isEditable = false
                    outputTextHexCopyButton = button("Copy") {
                        action { outputHexCopyBtnOnClick() }
                    }
                }
                field("Output Text (String):") {
                    outputTextStrTextField = textarea { prefHeight = 15.0 }
                    outputTextHexTextField!!.isEditable = false
                    outputTextStringCopyButton = button("Copy") {
                        action { outputStringCopyBtnOnClick() }
                    }
                }
            }
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Debug:") {
                    debugTextArea = textarea {  }
                    debugTextArea!!.isEditable = false
                }
            }
        }
    }



    private fun executeBtnOnClick() {
        try {
            val inputText= inputTextTextField!!.text
            val inputKey = keyTextField!!.text
            val textModeInput = if (inputTextStrRadioButton!!.isSelected) TextMode.STRING else TextMode.HEX
            val textModeKey = if (keyStrRadioButton!!.isSelected) TextMode.STRING else TextMode.HEX
            val cipherMode = if (encryptRadioButton!!.isSelected) CipherMode.ENCRYPT else CipherMode.DECRYPT

            val startTime = Date()

            val inputTextHex = AesMain.encryptDecryptInputTextPrep(inputText, cipherMode, textModeInput)
            val keyTextHex = AesMain.keyTextPrep(inputKey, textModeKey)

            var completionMessage = ""
            var outputTextHexStringPair: Pair<String, String>? = null
            when (cipherMode) {
                CipherMode.ENCRYPT -> {
                    outputTextHexStringPair = AesMain.encrypt(inputTextHex, keyTextHex)
                    completionMessage = "The PlainText has been successfully Encrypted!"
                }
                CipherMode.DECRYPT -> {
                    outputTextHexStringPair = AesMain.decrypt(inputTextHex, keyTextHex)
                    completionMessage = "The CipherText has been successfully Decrypted!"
                }
                else -> throw Exception("An unexpected error has occurred!")
            }

            if (outputTextHexStringPair == null || outputTextHexStringPair.first == "")
                throw Exception("Please enter inputs!")

            val endTime = Date()
            val timeDiff = (endTime.time - startTime.time) + 0.0
            aesMainFunctions.debugOutput.append("\nElapsed Time: $timeDiff milliseconds\n\n")


            val outputTextHex = outputTextHexStringPair.first
            val outputTextStr = outputTextHexStringPair.second
            outputTextHexTextField!!.text = outputTextHex
            outputTextStrTextField!!.text = outputTextStr

            var debugStr = aesMainFunctions.debugOutput.toString()
            debugTextArea!!.text = debugStr
            aesMainFunctions.debugOutput = StringBuilder("")

            JOptionPane.showMessageDialog(null, completionMessage, "Complete!", JOptionPane.INFORMATION_MESSAGE)
        } catch (ex: Exception) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE)
        }

    }

    private fun outputHexCopyBtnOnClick() {
        val outputHexStr = outputTextHexTextField!!.text
        val stringSelection = StringSelection(outputHexStr)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)

        JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE)
    }

    private fun outputStringCopyBtnOnClick() {
        val outputStr = outputTextStrTextField!!.text
        val stringSelection = StringSelection(outputStr)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(stringSelection, null)

        JOptionPane.showMessageDialog(null, "Text copied!", "Copied!", JOptionPane.INFORMATION_MESSAGE)
    }
}