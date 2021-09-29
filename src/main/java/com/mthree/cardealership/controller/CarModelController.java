/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.CarModel;
import com.mthree.cardealership.entities.Make;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.CarModelService;
import com.mthree.cardealership.service.DealershipUserDetails;
import com.mthree.cardealership.service.MakeService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ychen
 */
@Controller
public class CarModelController {
    
    @Autowired
    CarModelService carModelService;
    
    @Autowired
    MakeService makeService;
    
    @Autowired
    UserDao userDao;
    
    @GetMapping("admin/models")
    public String getModelPage(Model model) {
        
        List<Make> makes = makeService.getAllMakes();
        
        // create Make name list to show in dropdown 'makeSelection'
        Map<Integer, String> makeIdNames = produceIdMakePair(makes);
        List<String> makeNames = new ArrayList(makeIdNames.values());
        //System.out.println(makeNames.size());
        
        model.addAttribute("makeList", makeNames);   
        
        // create ModelWithMakeEmail list
        List<ModelWithMakeEmail> modelList = new ArrayList<>();
        List<CarModel> modelsFromDao = carModelService.getAllCarModels();
        for (CarModel curModel : modelsFromDao) {
            String curMake = makeIdNames.get(curModel.getMakeID());
            String curModelName = curModel.getModel();
            String dateAdded = curModel.getDateAdded().toString();
            String userEmail = userDao.getUserEmailById(curModel.getUserID());
            
            ModelWithMakeEmail curModelMakeEmail = new ModelWithMakeEmail(
                curModelName, curMake, dateAdded, userEmail);
            modelList.add(curModelMakeEmail);
        }
        
        model.addAttribute("modelList", modelList);
        
        return "models";
    }
    
    @PostMapping("admin/models")
    public String addNewModel(HttpServletRequest request) {
        String modelName = request.getParameter("modelName");
        int makeId = makeService.getMakeByName(request.getParameter("makeSelection")).getId();
        LocalDate dateAdded = LocalDate.now();
        int userId = getUserid();
        
        CarModel newModel = new CarModel();
        newModel.setMakeID(makeId);
        newModel.setModel(modelName);
        newModel.setDateAdded(dateAdded);
        newModel.setUserID(userId);
        
        carModelService.addCarModel(newModel, request.getParameter("makeSelection"));
        
        return "redirect:/admin/models";
    }
    
    
    
    private Map<Integer, String> produceIdMakePair(List<Make> makes) {
        Map<Integer, String> pairs = new HashMap<>();
        for(Make make : makes) {
            pairs.put(make.getId(), make.getMake());
        }
        return pairs;
    }
    
    public Integer getUserid(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ((DealershipUserDetails) auth.getPrincipal()).getUser();
        return Math.toIntExact(user.getId());
    }
    
    public class ModelWithMakeEmail {
        
        private String modelName;
        private String makeName;
        private String dateAdded;
        private String userEmail;

        public ModelWithMakeEmail(String modelName, String makeName, String dateAdded, String userEmail) {
            this.modelName = modelName;
            this.makeName = makeName;
            this.dateAdded = dateAdded;
            this.userEmail = userEmail;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getMakeName() {
            return makeName;
        }

        public void setMakeName(String makeName) {
            this.makeName = makeName;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }
        
        
        
    } 
    
}
