package com.example.lab_3_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var txtDisplay: EditText
    private var currentResult: Double? = null
    private var currentOperation: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtDisplay = findViewById(R.id.txtDisplay)

        findViewById<Button>(R.id.btn1).setOnClickListener { appendToDisplay("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendToDisplay("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendToDisplay("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendToDisplay("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendToDisplay("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendToDisplay("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendToDisplay("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendToDisplay("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendToDisplay("9") }
        findViewById<Button>(R.id.btn0).setOnClickListener { appendToDisplay("0") }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { performOperation('+') }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { performOperation('-') }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { performOperation('*') }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { performOperation('/') }

        findViewById<Button>(R.id.btnSquareRoot).setOnClickListener { performSquareRoot() }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }

        findViewById<Button>(R.id.btnClear).setOnClickListener { clearDisplay() }
        findViewById<Button>(R.id.btnBack).setOnClickListener { backSpace() }
        findViewById<Button>(R.id.btnSignChange).setOnClickListener { changeSign() }
    }

    private fun appendToDisplay(value: String) {
        txtDisplay.append(value)
    }

    private fun performOperation(operation: Char) {
        if (txtDisplay.text.isNotEmpty()) {
            val currentInput = txtDisplay.text.toString().toDouble()

            if (currentResult != null && currentOperation != null) {
                currentResult = calculate(currentResult!!, currentInput, currentOperation!!)
                txtDisplay.setText(currentResult.toString())
            } else {
                currentResult = currentInput
            }
            if (currentResult != null) {
                txtDisplay.setText(currentResult.toString())
            }

            currentOperation = operation
            txtDisplay.text.clear()
        } else {
            currentOperation = operation
        }
    }

    private fun performSquareRoot() {
        if (txtDisplay.text.isNotEmpty()) {
            val value = txtDisplay.text.toString().toDouble()
            currentResult = Math.sqrt(value)
            txtDisplay.setText(currentResult.toString())
            currentOperation = null
        }
    }

    private fun calculateResult() {
        if (currentResult != null && currentOperation != null && txtDisplay.text.isNotEmpty()) {
            val currentInput = txtDisplay.text.toString().toDouble()
            currentResult = calculate(currentResult!!, currentInput, currentOperation!!)
            txtDisplay.setText(currentResult.toString())
            currentOperation = null
        }
    }

    private fun calculate(firstOperand: Double, secondOperand: Double, operation: Char): Double {
        return when (operation) {
            '+' -> firstOperand + secondOperand
            '-' -> firstOperand - secondOperand
            '*' -> firstOperand * secondOperand
            '/' -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
            else -> firstOperand
        }
    }

    private fun clearDisplay() {
        txtDisplay.text.clear()
        currentResult = null
        currentOperation = null
    }

    private fun backSpace() {
        val currentText = txtDisplay.text.toString()
        if (currentText.isNotEmpty()) {
            txtDisplay.setText(currentText.substring(0, currentText.length - 1))
        }
    }

    private fun changeSign() {
        val currentText = txtDisplay.text.toString()
        if (currentText.isNotEmpty()) {
            val value = currentText.toDouble()
            txtDisplay.setText((-value).toString())
        }
    }
}
