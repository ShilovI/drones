package com.shilovi.drones.utils.tag;

import com.shilovi.drones.DroneServiceApplication;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = DroneServiceApplication.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DroneServiceApplication.class)
@AutoConfigureMockMvc
@Tag("integration")
@ActiveProfiles("integration-test")
public @interface SpringIntegrationTest {
}