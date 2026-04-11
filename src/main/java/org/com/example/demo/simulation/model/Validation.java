package org.com.example.demo.simulation.model;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    private Integer id;
    private boolean valid;
    private String name;
    private String reference;
    private LocalDateTime dateCreated;
}