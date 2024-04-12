package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetReviewDetailListDAO {
    private Long id;
    private Date createdDate;
    private Long receipeId;
    private Long userId;
    private String comment;
    private String status;
    private String receipeName;
}
