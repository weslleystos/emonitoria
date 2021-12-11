package com.github.weslleystos.emonitoria.shared.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorStringTest {
    @Test
    fun `should be valid a incorrect email`() {
        val (_, isValid) = ValidatorString("test@gmail.com")
            .isEmail()

        assertThat(isValid).isTrue()
    }

    @Test
    fun `shouldn't be valid a correct email`() {
        val (_, isValid) = ValidatorString("test@gmailcom")
            .isEmail()

        assertThat(isValid).isFalse()
    }

    @Test
    fun `should be valid a length more then parameter`() {
        val (_, isValid) = ValidatorString("12345678")
            .isGreatThan(7)

        assertThat(isValid).isTrue()
    }

    @Test
    fun `shouldn't be valid a length less then parameter`() {
        val (_, isValid) = ValidatorString("1234567")
            .isGreatThan(7)

        assertThat(isValid).isFalse()
    }

    @Test
    fun `should be valid a length less then parameter`() {
        val (_, isValid) = ValidatorString("12345678901234")
            .isLessThan(15)

        assertThat(isValid).isTrue()
    }

    @Test
    fun `shouldn't be valid a length more then parameter`() {
        val (_, isValid) = ValidatorString("123456789012345")
            .isLessThan(15)

        assertThat(isValid).isFalse()
    }
}