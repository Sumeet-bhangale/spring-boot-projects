package com.example.project.Services;

import com.example.project.DTO.GetReviewDetailListDAO;
import com.example.project.Models.ProfileDetail;
import com.example.project.Models.ReceipesDetail;
import com.example.project.Models.ReviewDetail;
import com.example.project.Repositories.ProfileDetailDAO;
import com.example.project.Repositories.ReceipesDetailDAO;
import com.example.project.Repositories.ReviewDetailDAO;
import com.example.project.Repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    ReceipesDetailDAO receipesDetailDAO;

    @Autowired
    ProfileDetailDAO profileDetailDAO;

    @Autowired
    ReviewDetailDAO reviewDetailDAO;

    // User Profile

    public String AddProfile(ProfileDetail request){
        try{

            profileDetailDAO.save(request);
            return "Add Profile Detail Successfully";

        }catch (Exception ex){
            return null;
        }
    }

    public String UpdateProfile(ProfileDetail request){
        try{

            Optional<ProfileDetail> response = profileDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getId()== request.getId())
                    .findFirst();

            if(!response.isPresent()){
                return "Profile Detail Not Found";
            }

            response.get().setCreatedDate(new Date());
            response.get().setUserID(request.getUserID());
            response.get().setFullName(request.getFullName());
            response.get().setEmail(request.getEmail());
            response.get().setAddress(request.getAddress());
            response.get().setContact(request.getContact());
            profileDetailDAO.save(response.get());
            return "Update Profile Detail Successfully";

        }catch (Exception ex){
            return null;
        }
    }

    public Optional<ProfileDetail> GetProfile(Long userID){
        try{
            return profileDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getUserID()==userID)
                    .findFirst();

        }catch (Exception ex){
            return null;
        }
    }

    //
    public List<ReceipesDetail> GetReceipesDetailList(){
        try{
            return receipesDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getIsActive())
                    .toList();

        }catch (Exception ex){
            return null;
        }
    }

    public List<ReceipesDetail> GetReceipesByCategory(String keyword){
        try{
            return receipesDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getCategory().equalsIgnoreCase(keyword))
                    .toList();

        }catch (Exception ex){
            return null;
        }
    }

    public List<ReceipesDetail> GetReceipesByKeyword(String keyword){
        try{
            return receipesDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getName().equalsIgnoreCase(keyword) || x.getDescription().equalsIgnoreCase(keyword))
                    .distinct()
                    .toList();

        }catch (Exception ex){
            return null;
        }
    }

    //Review
    public String AddUserReview(ReviewDetail request){
        try{

            reviewDetailDAO.save(request);
            return "Add Review Detail Successfully";

        }catch (Exception ex){
            return null;
        }
    }

    public String UpdateUserReview(ReviewDetail request){
        try{

            Optional<ReviewDetail> response = reviewDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getId()== request.getId())
                    .findFirst();

            if(!response.isPresent()){
                return "Profile Detail Not Found";
            }

            /*
            private Date createdDate;
            private Long receipeId;
            private Long userId;
            private String comment;
            private String status;
            */

            response.get().setCreatedDate(new Date());
            response.get().setReceipeId(request.getReceipeId());
            response.get().setUserId(request.getUserId());
            response.get().setComment(request.getComment());
            response.get().setStatus(request.getStatus());
            reviewDetailDAO.save(response.get());
            return "Update Review Detail Successfully";

        }catch (Exception ex){
            return null;
        }
    }

    public List<GetReviewDetailListDAO> GetUserReviewList(){
        try{
            List<ReviewDetail> r = reviewDetailDAO
                    .findAll()
                    .stream()
                    .toList();

            /*
            private Long id;
            private Date createdDate;
            private Long receipeId;
            private Long userId;
            private String comment;
            private String status;
            private String receipeName;
            */
            List<GetReviewDetailListDAO> getList = new ArrayList<>();
            r.forEach(x->{
                GetReviewDetailListDAO getData = new GetReviewDetailListDAO();
                getData.setId(x.getId());
                getData.setCreatedDate(x.getCreatedDate());
                getData.setReceipeId(x.getReceipeId());
                getData.setUserId(x.getUserId());
                getData.setComment(x.getComment());
                getData.setStatus(x.getStatus());
                Optional<ReceipesDetail> data = receipesDetailDAO.findAll().stream().filter(x1->x1.getId()==x.getReceipeId()).findFirst();
                String Name = "";
                if(data.isEmpty()){
                    Name = "";
                }else{
                    Name = data.get().getName();
                }
                getData.setReceipeName(Name);
                getList.add(getData);
            });
            return getList;

        }catch (Exception ex){
            return null;
        }
    }

    public String DeleteUserReview(Long Id){

        try{

            Optional<ReviewDetail> response = reviewDetailDAO
                    .findAll()
                    .stream()
                    .filter(x->x.getId()==Id)
                    .findFirst();

            if(!response.isPresent())
            {
                return "Invalid Review Id";
            }

            reviewDetailDAO.delete(response.get());

            return "Delete Review Successfully";

        }catch (Exception ex){
            return null;
        }
    }

}
