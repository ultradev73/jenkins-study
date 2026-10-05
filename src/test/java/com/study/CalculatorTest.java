package com.study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void addTest() {
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
	//assertEquals(10, calculator.add(2, 3));
    }
}
