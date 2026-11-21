package com.example.app_kotlin

import com.example.appsemestral.validation.CandidataValidator
import com.example.appsemestral.validation.ValidationResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CandidataValidatorTest {
    @Test fun nombreValido_debeSerValido() {
        assertEquals(ValidationResult.Valido, CandidataValidator.validarNombre("Antonia Ávila"))
    }

    @Test fun nombreVacio_debeGenerarError() {
        val resultado = CandidataValidator.validarNombre("")
        assertEquals("El nombre no puede estar vacio", (resultado as ValidationResult.Error).mensaje)
    }

    @Test fun nombreMuyCorto_debeGenerarError() {
        val resultado = CandidataValidator.validarNombre("Ab")
        assertEquals("Debe tener al menos 3 caracteres", (resultado as ValidationResult.Error).mensaje)
    }

    @Test fun nombreConNumeros_debeGenerarError() {
        assertTrue(CandidataValidator.validarNombre("Antonia123") is ValidationResult.Error)
    }

    @Test fun correoValido_debeSerValido() {
        assertEquals(ValidationResult.Valido, CandidataValidator.validarCorreo("antonia.avila@email.com"))
    }

    @Test fun correoVacio_debeGenerarError() {
        val resultado = CandidataValidator.validarCorreo("")
        assertEquals("El correo no puede estar vacío", (resultado as ValidationResult.Error).mensaje)
    }

    @Test fun correoInvalido_debeGenerarError() {
        assertTrue(CandidataValidator.validarCorreo("correo-invalido") is ValidationResult.Error)
    }

    @Test fun telefonoDeOchoDigitos_debeSerValido() {
        assertEquals(ValidationResult.Valido, CandidataValidator.validarTelefono("12345678"))
    }

    @Test fun telefonoFueraDelLimite_debeGenerarError() {
        assertTrue(CandidataValidator.validarTelefono("1234567") is ValidationResult.Error)
        assertTrue(CandidataValidator.validarTelefono("123456789") is ValidationResult.Error)
    }

    @Test fun telefonoConLetras_debeGenerarError() {
        assertTrue(CandidataValidator.validarTelefono("1234ABCD") is ValidationResult.Error)
    }
}
