package org.logic;

import org.domain.Category;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;


class CategoryServiceIT {

    private static final Path DATA_FILE = Path.of("data.xml");
    private static final Path BACKUP_FILE = Path.of("data.xml.bak");

    private final CategoryService service = new CategoryService();

    @BeforeAll
    static void respaldarDataXml() throws IOException {
        if (Files.exists(DATA_FILE)) {
            Files.copy(DATA_FILE, BACKUP_FILE, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterAll
    static void restaurarDataXml() throws IOException {
        if (Files.exists(BACKUP_FILE)) {
            Files.copy(BACKUP_FILE, DATA_FILE, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(BACKUP_FILE);
        }
    }

    @Test
    void guardarYLuegoBuscarCategoriaPersisteEnElXml() throws Exception {
        Category nueva = new Category();
        nueva.setDescription("Categoria de prueba IT");

        service.save(nueva);

        Category encontrada = service.findByDesc("Categoria de prueba IT");
        assertNotNull(encontrada, "La categoría debería existir tras guardarla");
        assertNotNull(encontrada.getId());

        service.delete(encontrada.getId());
        assertNull(service.findById(encontrada.getId()));
    }

    @Test
    void findAllLeeCategoriasDesdeElArchivoXmlReal() throws Exception {
        // No verificamos un tamaño exacto (los datos reales pueden cambiar),
        // solo que la lectura del archivo funciona sin lanzar excepción.
        assertNotNull(service.findAll());
    }
}