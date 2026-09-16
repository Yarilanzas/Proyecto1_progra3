package org.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PRUEBA UNITARIA (la corre Surefire con "mvn test").
 * No toca archivos, base de datos ni nada externo: solo prueba la clase
 * Category aislada. Por eso es "unitaria".
 */
class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category("CAT-000001", "Salas de reunión");
    }

    @Test
    void constructorAsignaIdYDescripcion() {
        assertEquals("CAT-000001", category.getId());
        assertEquals("Salas de reunión", category.getDescription());
    }

    @Test
    void setDescriptionActualizaElValor() {
        category.setDescription("Auditorios");
        assertEquals("Auditorios", category.getDescription());
    }

    @Test
    void toStringDevuelveLaDescripcion() {
        assertEquals(category.getDescription(), category.toString());
    }

    @Test
    void constructorVacioNoDejaCamposNulos() {
        Category vacia = new Category();
        assertNotNull(vacia.getId());
        assertNotNull(vacia.getDescription());
    }
}