package com.example.project.Services;

import com.example.project.Models.ProfileDetail;
import com.example.project.Models.ReceipesDetail;
import com.example.project.Repositories.ReceipesDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ReceipesDetailDAO receipesDetailDAO;

    public String AddReceipesDetail(ReceipesDetail request) {

        try {
            receipesDetailDAO.save(request);
            return "Added Receipes Detail Successfully!";

        }catch (Exception ex)
        {
            return ex.getMessage();
        }

    }

    public String UpdateReceipesDetail(ReceipesDetail request){

        try{

            Optional<ReceipesDetail> response = receipesDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getId()==request.getId())
                    .findFirst();

            if(!response.isPresent()) {
                return "Invalid Receipes Id";
            }

            /*
            private Long userID;
            private String name;
            private String description;
            private String ingredients;
            private String cookingSteps;
            private String category;
            private String imageUrl;
            private boolean isActive;
            */
            response.get().setUserID(request.getUserID());
            response.get().setName(request.getName());
            response.get().setDescription(request.getDescription());
            response.get().setIngredients(request.getIngredients());
            response.get().setCookingSteps(request.getCookingSteps());
            response.get().setCategory(request.getCategory());
            response.get().setImageUrl(request.getImageUrl());
            response.get().setIsActive(request.getIsActive());
            receipesDetailDAO.save(response.get());

            return "Update Receipes Detail successfully";

        }catch (Exception ex){
            return ex.getMessage();
        }

    }

    public List<ReceipesDetail> GetReceipesDetailList(){

        try{
            return receipesDetailDAO
                    .findAll()
                    .stream()
                    .toList();

        }catch (Exception ex){
            return null;
        }
    }

    public String DeleteReceipesDetailL(Long Id){

        try{

            Optional<ReceipesDetail> response = receipesDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getId()==Id)
                    .findFirst();

            if(!response.isPresent())
            {
                return "Invalid Receipes Id";
            }

            receipesDetailDAO.delete(response.get());

            return "Delete Receipes Successfully";

        }catch (Exception ex){
            return null;
        }
    }

}
