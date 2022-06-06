package id.co.octopus.library.core

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CommonUtilsTest {

    @Test
    fun `phone number is valid`() {
        val result = CommonUtils.isValidPhoneNumber(
                "082347218036"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `phone number start with char return false`() {
        val result = CommonUtils.isValidPhoneNumber(
                "abc082347218036"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `phone number start with zero return true`() {
        val result = CommonUtils.isValidPhoneNumber(
                "082347218036"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `phone number length is below 9 return false`() {
        val result = CommonUtils.isValidPhoneNumber(
                "08234721"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `phone number is length over 14 return false`() {
        val result = CommonUtils.isValidPhoneNumber(
                "0823472180361416"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `phone number length in range 9 until 14 return true`() {
        val result = CommonUtils.isValidPhoneNumber(
                "082347218036"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `pln token number or customer id length below 8 return false`() {
        val result = CommonUtils.isValidTokenPln(
                "0123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `pln token number or customer id length over 20 return false`() {
        val result = CommonUtils.isValidTokenPln(
                "01234567890123456789012345"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `pln token number or customer id is length in 8 until 20 return true`() {
        val result = CommonUtils.isValidTokenPln(
                "012345678901"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `decrease quantity`() {
        //input 1.0      //input 0.3    //input 5.2   //input 6.6    //input 7.5
        //output 0.5     //output 5    //output 6.5   //output 7.0
        val inputQty = 1.0
        val expectedResult = 0.5
        val result = CommonUtils.decreaseQuantity(inputQty)
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `increase quantity`() {
        //input 0.3    //input 5.2   //input 6.6    //input 7.5
        //output 0.5     //output 5.5    //output 7.0   //output 8.0
        val inputQty = 6.6
        val expectedResult = 7.0
        val result = CommonUtils.increaseQuantity(inputQty)
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `round decimal`() {
        //input 4.399
        //output 4.40
        val inputQty = 5.100000000000000
        val expectedResult = 5.1
        val result = CommonUtils.roundDecimal(inputQty)
        assertThat(result).isEqualTo(expectedResult)
    }
}