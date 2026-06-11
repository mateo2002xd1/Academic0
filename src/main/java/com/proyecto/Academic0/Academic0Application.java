package com.proyecto.Academic0;

import com.proyecto.Academic0.controller.CursoController;
import com.proyecto.Academic0.dto.CursoRequest;
import com.proyecto.Academic0.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Academic0Application implements CommandLineRunner {

    @Autowired
    private CursoService cursoService;

    public static void main(String[] args) {
        SpringApplication.run(Academic0Application.class, args);
    }

    @Override
    public void run(String... args) {

        /*CursoRequest curso =
                new CursoRequest(
                        "Curso de prueba prueba",
                        "Curso de prueba prueba",
                        false
                );
        System.out.println(cursoService.buscarCurso(2));
        cursoService.actualizarCurso(2, curso);
        System.out.println(cursoService.eliminarCurso(2));*/
    }
}