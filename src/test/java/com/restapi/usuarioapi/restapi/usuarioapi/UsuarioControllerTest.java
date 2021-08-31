package com.restapi.usuarioapi.restapi.usuarioapi;
import static org.assertj.core.api.Assertions.assertThat;
import com.restapi.usuarioapi.controller.UsuarioController;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioControllerTest extends TestCase {

    @Autowired
    private UsuarioController usuarioController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(usuarioController).isNotNull();
    }
}
