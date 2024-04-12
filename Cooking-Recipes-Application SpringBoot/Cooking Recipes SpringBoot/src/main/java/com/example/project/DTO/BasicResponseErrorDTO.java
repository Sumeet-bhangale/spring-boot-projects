package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponseErrorDTO<T> {
    public String timestamp;
    public String status;
    public String error;
    public String message;
    public String path;
}
