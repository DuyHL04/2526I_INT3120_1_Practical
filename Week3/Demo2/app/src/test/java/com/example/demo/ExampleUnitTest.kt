package com.example.demo

import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class Calculator {
    fun multiply(a: Int, b: Int): Int = a * b
    fun divide(a: Int, b: Int): Int = a / b
}

class CalculatorTest {
    private lateinit var calculator: Calculator
    @Before
    fun setUp() {
        calculator = Calculator()
    }
    @Test
    fun testMultiply() {
        assertEquals(20, calculator.multiply(4, 5))
    }
    @Test
    fun testDivide() {
        assertEquals(2, calculator.divide(10, 5))
    }
    @After
    fun tearDown() {
        // cleanup nếu cần
    }
}